package what.eat.data.facade;

import what.eat.generic.type.dto.IdLabelDTO;

public class DishRelationResponseDTO {

    private String type;
    private IdLabelDTO value;

    public void setValue(IdLabelDTO value) {
        this.value = value;
    }

    public IdLabelDTO getValue() {
        return value;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
