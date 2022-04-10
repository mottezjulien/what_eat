package what.eat.data.persistence.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import what.eat.data.domain.model.DataBookDish;
import what.eat.data.domain.model.DataDish;
import what.eat.data.domain.model.DataDishAtom;
import what.eat.data.domain.model.DataDishElement;
import what.eat.data.domain.model.DataDishRelation;
import what.eat.data.domain.model.DataDishRelationAtom;
import what.eat.data.domain.model.DataDishRelationType;
import what.eat.data.domain.model.DataId;
import what.eat.data.domain.model.DataIngredient;
import what.eat.data.persistence.entity.DataDishEntity;
import what.eat.data.persistence.entity.DataIngredientEntity;
import what.eat.data.plop.DataDishQuery;
import what.eat.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class DataDishCacheRepository {

    @Autowired
    private DataIngredientRepository ingredientRepository;

    @Autowired
    private DataDishJpaRepository dishRepository;

    private DataBookDish currentBook = new DataBookDish();

    public Stream<DataDish> find(DataDishQuery query) {
        return currentBook.stream()
                .filter(query.toPredicate());
    }

    public Stream<DataDish> findAll() {
        return currentBook.stream();
    }

    public Optional<DataDish> findAny() {
        return currentBook.random();
    }

    public void reload() {

        List<DataDishEntity> dishEntities = dishRepository.findAllFullFetch();
        Map<DataDishElement, List<DataDishRelationAtom>> elementWithRelationAtoms = dishEntities.stream()
                .collect(Collectors.toMap(DataDishEntity::toDishElementModel, DataDishEntity::toRelationModels));
        List<DataDishElement> elementList = new ArrayList<>(elementWithRelationAtoms.keySet());

        // Update Element: Add tags in ingredient
        List<DataIngredient> ingredients = ListUtils.map(ingredientRepository.findAllFetchTag(), DataIngredientEntity::toModel);
        elementWithRelationAtoms = elementWithRelationAtoms.entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey().updateIngredients(findIngredients(entry.getKey().atom(), ingredients)), Map.Entry::getValue));

        // Update Relation: Upgrade from DataDishId to DataDishAtom
        Map<DataDishElement, List<DataDishRelation>> elementWithRelations = elementWithRelationAtoms.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> upgradeRelations(entry.getValue(), elementList)));

        // Update Relation: add Tags for her "Full" Relations (Relation of Relation)
        elementWithRelations
                .forEach((key, value) -> value.forEach(relation -> addTagsFromFullRelation(relation.with(), elementWithRelations)));

        DataBookDish newBook = new DataBookDish();
        newBook.init(elementWithRelations);
        this.currentBook = newBook;
    }

    private List<DataIngredient> findIngredients(DataDishAtom atom, List<DataIngredient> ingredients) {
        return atom.ingredients()
                .flatMap(ingredient -> ListUtils.findOne(ingredients, ingredient::equals).stream())
                .collect(Collectors.toList());
    }

    private List<DataDishRelation> upgradeRelations(List<DataDishRelationAtom> atoms, List<DataDishElement> flatElements) {
        return atoms.stream()
                .flatMap(relation -> findOneDishElement(flatElements, relation.with())
                        .map(details -> new DataDishRelation(relation.type(), details.atom()))
                        .stream())
                .collect(Collectors.toList());
    }

    private Optional<DataDishElement> findOneDishElement(List<DataDishElement> elements, DataId id) {
        return ListUtils.findOne(elements, element -> element.id().equals(id));
    }

    private void addTagsFromFullRelation(DataDishAtom atom, Map<DataDishElement, List<DataDishRelation>> elementWithRelations) {
        List<DataDishRelation> relations = findAllRelations(elementWithRelations, atom);
        relations.stream()
                .filter(relation -> relation.type() == DataDishRelationType.FULL)
                .forEach(fullRelation -> atom.addAllTags(fullRelation.with().tags().collect(Collectors.toList())));
    }

    private List<DataDishRelation> findAllRelations(Map<DataDishElement, List<DataDishRelation>> elementWithRelations, DataDishAtom atom) {
        return elementWithRelations.getOrDefault(new DataDishElement(null, atom), List.of());
    }

}
