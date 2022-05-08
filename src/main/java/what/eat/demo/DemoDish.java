package what.eat.demo;

public class DemoDish {

    private final String id;
    private final String frLabel;

    public DemoDish(String id, String frLabel) {
        this.id = id;
        this.frLabel = frLabel;
    }

    public String id() {
        return id;
    }

    public String frLabel() {
        return frLabel;
    }
}
