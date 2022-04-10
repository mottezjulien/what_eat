package what.eat.data.facade;

import what.eat.generic.type.IdLabel;

public class DishRelationResponseDTO {

    private String type;
    private IdLabel value;

    public void setValue(IdLabel value) {
        this.value = value;
    }

    public IdLabel getValue() {
        return value;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
