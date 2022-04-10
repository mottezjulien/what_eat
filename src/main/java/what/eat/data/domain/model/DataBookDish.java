package what.eat.data.domain.model;

import what.eat.generic.Combinator;
import what.eat.utils.ListUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataBookDish {

    private final List<DataDishElement> flatElements = new ArrayList<>();
    private final Map<DataDishElement, List<DataDishRelation>> elementWithRelations = new HashMap<>();
    private final List<DataDish> dishes = new ArrayList<>();

    public Stream<DataDish> stream() {
        return dishes.stream();
    }

    public Optional<DataDish> random() {
        return ListUtils.random(dishes);
    }

    public void init(Map<DataDishElement, List<DataDishRelation>> elementWithRelations) {
        this.flatElements.clear();
        this.flatElements.addAll(elementWithRelations.keySet());
        this.elementWithRelations.clear();
        this.elementWithRelations.putAll(elementWithRelations);
        this.dishes.clear();
        this.dishes.addAll(Stream.concat(readyDishes(), templateDishes()).collect(Collectors.toList()));
    }

    private Stream<DataDish> readyDishes() {
        return flatElements.stream()
                .filter(element -> element.type() == DataDishElement.Type.DISH_READY)
                .map(this::dataDish);
    }

    private Stream<DataDish> templateDishes() {
        return flatElements.stream()
                .filter(element -> element.type() == DataDishElement.Type.DISH_TEMPLATE)
                .flatMap(this::templateDishes);
    }

    private Stream<DataDish> templateDishes(DataDishElement element) {
        List<DataDishRelation> relations = elementWithRelations.get(element);

        List<List<DataDishRelation>> possibleItemsByRelation = relations.stream()
                .map(this::possibleItems)
                .collect(Collectors.toList());

        Combinator<DataDishRelation> combinator = new Combinator();
        possibleItemsByRelation.forEach(combinator::add);
        List<List<DataDishRelation>> resultCombinator = combinator.combine();

        return resultCombinator.stream().map(list -> {
            String variantNames = list.stream()
                    .map(relation -> relation.with().id().frLabel())
                    .collect(Collectors.joining(", "));
            return new DataDish(element.atom().rename(variantNames), list);
        });

    }

    private List<DataDishRelation> possibleItems(DataDishRelation relation) {
        return switch (elementType(relation.with())) {
            case NODE_ITEM -> List.of(relation);
            case NODE_GENERIC -> variants(relation.with())
                    .map(variant -> new DataDishRelation(relation.type(), variant))
                    .collect(Collectors.toList());
            default -> List.of();
        };
    }

    private Stream<DataDishAtom> variants(DataDishAtom atom) {
        return children(atom)
                .filter(child -> child.type() == DataDishElement.Type.NODE_ITEM)
                .map(child -> child.atom());
    }

    private DataDishElement.Type elementType(DataDishAtom atom) {
        return optElement(atom)
                .map(DataDishElement::type)
                .orElse(DataDishElement.Type.UNKNOWN);
    }

    private Optional<DataDishElement> optElement(DataDishAtom atom) {
        return ListUtils.findOne(this.flatElements, element -> element.atom().equals(atom));
    }

    private Stream<DataDishElement> children(DataDishAtom atom){
        return elementWithRelations.entrySet().stream()
                .filter(entry -> entry.getValue().stream().map(DataDishRelation::with).anyMatch(with -> with.equals(atom)))
                .map(Map.Entry::getKey);
    }

    private DataDish dataDish(DataDishElement element) {
        return new DataDish(element.atom(), elementWithRelations.get(element));
    }

}
