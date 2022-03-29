package what.eat.demo;

public class DemoRule {

    public enum Type {
        AT_LEAST, MAX, EQUALS;
    }

    private Type type;
    private String tagId;
    private int number;
    private String label;

    public DemoRule(Type type, String tagId, int number, String label) {
        this.type = type;
        this.tagId = tagId;
        this.number = number;
        this.label = label;
    }

    public Type type() {
        return type;
    }

    public String tagId() {
        return tagId;
    }

    public int number() {
        return number;
    }

    public String label() {
        return label;
    }
}
