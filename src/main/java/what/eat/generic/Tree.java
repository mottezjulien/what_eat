package what.eat.generic;

import what.eat.generic.type.TriFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Tree<EltNode, EltLeaf> {

    interface Element {

    }

    public class Node implements Element {

        private final Node parent;
        private final EltNode obj;
        private final List<Element> children = new ArrayList<>();

        public Node(Node parent, EltNode obj) {
            this.parent = parent;
            this.obj = obj;
        }

        public Node createChild(EltNode obj) {
            Node child = new Node(this, obj);
            children.add(child);
            return child;
        }

        public Node add(EltLeaf obj) {
            children.add(new Leaf(obj));
            return this;
        }

        public Node up() {
            return parent;
        }

        public <Result> Result reduce(Function<EltLeaf, Result> toResult, Result _default, TriFunction<EltNode, Result, Result, Result> accumulator) {
            return children.stream()
                    .map(elt -> {
                        if (elt instanceof Tree.Node) {
                            return ((Node) elt).reduce(toResult, _default, accumulator);
                        }
                        return toResult.apply(((Leaf) elt).value());
                    })
                    .reduce(_default, ((result1, result2) -> accumulator.apply(obj, result1, result2)));
        }
    }

    public class Leaf implements Element {
        private final EltLeaf value;

        public Leaf(EltLeaf value) {
            this.value = value;
        }

        public EltLeaf value() {
            return this.value;
        }
    }

    private final Node root;

    public Tree(EltNode obj) {
        this.root = new Node(null, obj);
    }

    public Node createChild(EltNode obj) {
        return root.createChild(obj);
    }

    public Node add(EltLeaf obj) {
        return root.add(obj);
    }

    public <Result> Result reduce(Function<EltLeaf, Result> toResult, Result _default, TriFunction<EltNode, Result, Result, Result> accumulator) {
        return root.reduce(toResult, _default, accumulator);
    }

}
