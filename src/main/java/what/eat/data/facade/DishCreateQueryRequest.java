package what.eat.data.facade;

public class DishCreateQueryRequest {

    private String type;
    private String frLabel;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFrLabel() {
        return frLabel;
    }

    public void setFrLabel(String frLabel) {
        this.frLabel = frLabel;
    }
}
