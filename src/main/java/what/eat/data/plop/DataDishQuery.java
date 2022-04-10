package what.eat.data.plop;

import what.eat.data.domain.model.DataDish;
import what.eat.generic.Tree;
import what.eat.generic.type.TriFunction;
import what.eat.utils.ListUtils;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DataDishQuery {

    public enum Relation {
        AND, OR
    }

    public enum Operation {
        CONTAINS, CONTAINS_ONLY, DONT_CONTAINS;
    }

    public enum Field {
        ID, FR_LABEL, TAG_ID, RELATION_FR_LABEL
    }

    public class QueryNode {

        private final Relation relation;

        public QueryNode() {
            this.relation = Relation.AND;
        }

        public QueryNode(Relation relation) {
            this.relation = relation;
        }
    }

    public class QueryLeaf {

        private Field field;
        private Operation operation;
        private List<String> comparators;

        public Predicate<DataDish> predicate() {
            return switch (this.operation) {
                case CONTAINS -> dataDish -> values(dataDish).containsAll(comparators);
                case CONTAINS_ONLY -> dataDish -> ListUtils.equalsIgnoreOrder(values(dataDish), comparators);
                case DONT_CONTAINS -> dataDish -> {
                    List<String> values = values(dataDish);
                    return comparators.stream().allMatch(comparator -> !values.contains(comparator));
                };
            };
        }

        private List<String> values(DataDish dataDish) {
            return switch (this.field) {
                case ID -> List.of(dataDish.id().value());
                case FR_LABEL -> List.of(dataDish.id().frLabel());
                case TAG_ID -> dataDish.tags().map(tag -> tag.id().value()).collect(Collectors.toList());
                case RELATION_FR_LABEL -> dataDish.relations().map(relation -> relation.with().id().frLabel()).collect(Collectors.toList());
            };
        }
    }

    private Tree<QueryNode, QueryLeaf> value = new Tree<>(new QueryNode());


    public Tree<QueryNode, QueryLeaf>.Node add(Field field, Operation operation, List<String> comparators) {
        final QueryLeaf leaf = new QueryLeaf();
        leaf.field = field;
        leaf.operation = operation;
        leaf.comparators = comparators;
        return value.add(leaf);
    }


    public Tree<QueryNode, QueryLeaf>.Node add(Field field, Operation operation, String... comparators) {
        final QueryLeaf leaf = new QueryLeaf();
        leaf.field = field;
        leaf.operation = operation;
        leaf.comparators = List.of(comparators);
        return value.add(leaf);
    }

    public Predicate<DataDish> toPredicate() {
        final TriFunction<QueryNode, Predicate<DataDish>, Predicate<DataDish>, Predicate<DataDish>> accumulator = (leaf, predicate1, predicate2) -> switch (leaf.relation) {
            case AND -> predicate1.and(predicate2);
            case OR -> predicate1.or(predicate2);
        };
        return value.reduce(QueryLeaf::predicate, dataDish -> true, accumulator);
    }

}

/*
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

 */