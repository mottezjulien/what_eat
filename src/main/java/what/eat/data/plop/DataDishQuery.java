package what.eat.data.plop;

import what.eat.data.domain.model.DataDish;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class DataDishQuery {

    public enum Operation {
        EQUALS, DIFFERENT;
    }

    public enum Field {
        ID, FR_LABEL, TAG_ID, RELATION_FR_LABEL
    }

    public class Item {
        private Field field;
        private Operation operation;
        private String value;
    }

    private List<Item> items = new ArrayList<>();

    public void add(Field field, Operation operation, String value) {
        Item item = new Item();
        item.field = field;
        item.operation = operation;
        item.value = value;
        this.items.add(item);
    }

    public Predicate<DataDish> predicate() {
        return items.stream().map(this::predicateItem)
                .reduce(item -> true, Predicate::and);
    }

    private Predicate<DataDish> predicateItem(Item item) {
        return withOperation(item, withField(item));
    }

    private Predicate<DataDish> withOperation(Item item, Predicate<DataDish> target) {
        return switch (item.operation) {
            case DIFFERENT -> Predicate.not(target);
            default -> target;
        };
    }

    private Predicate<DataDish> withField(Item item) {
        return switch (item.field) {
            case ID -> dataDish -> dataDish.id().value().equals(item.value);
            case FR_LABEL -> dataDish -> dataDish.id().frLabel().equals(item.value);
            case TAG_ID -> dataDish -> dataDish.tags().anyMatch(tag -> tag.id().value().equals(item.value));
            case RELATION_FR_LABEL -> dataDish -> dataDish.relations().anyMatch(relation -> relation.with().id().frLabel().equals(item.value));
        };
    }

}
