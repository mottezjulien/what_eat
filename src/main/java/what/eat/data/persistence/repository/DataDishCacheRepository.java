package what.eat.data.persistence.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import what.eat.data.domain.model.DataBookDish;
import what.eat.data.domain.model.DataDish;
import what.eat.data.domain.model.DataDishAtom;
import what.eat.data.domain.model.DataDishElement;
import what.eat.data.domain.model.DataDishRelation;
import what.eat.data.domain.model.DataDishRelationAtom;
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
                .filter(query.predicate());
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

        // Update Element: add tags in ingredient
        List<DataIngredient> ingredients = ListUtils.map(ingredientRepository.findAllFetchTag(), DataIngredientEntity::toModel);
        elementWithRelationAtoms = elementWithRelationAtoms.entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey().updateIngredients(find(entry.getKey().atom(), ingredients)), Map.Entry::getValue));

        // Update Relation: with new Element && transform from id to atom
        List<DataDishElement> elements = new ArrayList<>(elementWithRelationAtoms.keySet());
        Map<DataDishElement, List<DataDishRelation>> elementWithRelations = elementWithRelationAtoms.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> toDetails(entry.getValue(), elements)));

        DataBookDish newBook = new DataBookDish();
        newBook.init(elementWithRelations);
        this.currentBook = newBook;
    }

    private List<DataIngredient> find(DataDishAtom atom, List<DataIngredient> ingredients) {
        return atom.ingredients()
                .flatMap(ingredient -> ListUtils.findOne(ingredients, ingredient::equals).stream())
                .collect(Collectors.toList());
    }

    private List<DataDishRelation> toDetails(List<DataDishRelationAtom> atoms, List<DataDishElement> flatElements) {
        return atoms.stream()
                .flatMap(relation -> ListUtils.findOne(flatElements, element -> element.id().equals(relation.with()))
                        .map(details -> relation.toDetails(details.atom()))
                        .stream())
                .collect(Collectors.toList());

    }

}
