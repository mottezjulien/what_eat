package what.eat.demo;

import what.eat.data.domain.model.DataTag;

public class DemoRule {

    public enum Type {
        AT_LEAST, MAX, EQUALS;
    }

    private Type type;
    private DataTag tag;
    private int number;
    private String label;

    public DemoRule(Type type, DataTag tag, int number, String label) {
        this.type = type;
        this.tag = tag;
        this.number = number;
        this.label = label;
    }

    public Type type() {
        return type;
    }

    public DataTag tag() {
        return tag;
    }

    public int number() {
        return number;
    }

    public String label() {
        return label;
    }
}
