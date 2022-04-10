package what.eat.generic.brain;

public class StringData {

    private String value;

    public StringData(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return "StringData{" +
                "value='" + value + '\'' +
                '}';
    }
}
