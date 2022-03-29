package what.eat.data.plop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;


public class TreeQuery<Result> implements Predicate<Result> {

    public enum Type {
        AND, OR, NONE;
    }

    abstract class Element implements Predicate<Result> {

    }

    public class Node extends Element {

        private Type type;
        private List<Element> elements = new ArrayList<>();

        public Node() {
            this.type = Type.AND;
        }

        public Node(Type type) {
            this.type = type;
        }

        void add(Element element) {
            elements.add(element);
        }

        @Override
        public boolean test(Result result) {
            Stream<Element> stream = elements.stream();
            Predicate<Element> predicate = node -> node.test(result);
            return switch (type) {
                case AND -> stream.allMatch(predicate);
                case OR -> stream.anyMatch(predicate);
                case NONE -> stream.noneMatch(predicate);
            };
        }
    }

    public class Leaf extends Element {

        private final Predicate<Result> predicate;

        public Leaf(Predicate<Result> predicate) {
            this.predicate = predicate;
        }

        @Override
        public boolean test(Result result) {
            return predicate.test(result);
        }
    }

    private Node root;

    public TreeQuery(Type type) {
        this.root = new Node(type);
    }

    public TreeQuery() {
        this.root = new Node();
    }

    public void addLeaf(Node parent, Predicate<Result> predicate) {
        parent.add(new Leaf(predicate));
    }

    public void addLeafFromRoot(Predicate<Result> predicate) {
        addLeaf(root, predicate);
    }

    public Node addNode(Node parent, Type type) {
        final Node node = new Node(type);
        parent.add(node);
        return node;
    }

    public Node addNode(Node parent) {
        final Node node = new Node();
        parent.add(node);
        return node;
    }

    public Node addNodeFromRoot(Type type) {
        return addNode(root, type);
    }

    public Node addNodeFromRoot() {
        return addNode(root);
    }

    @Override
    public boolean test(Result result) {
        return root.test(result);
    }
}
