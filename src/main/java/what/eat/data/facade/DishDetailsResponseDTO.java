package what.eat.data.facade;

import what.eat.generic.type.IdLabel;

import java.util.List;

public class DishDetailsResponseDTO {
    private String id;
    private String label;
    private String type;
    private List<IdLabel> tags;
    private List<IdLabel> ingredients;
    private List<DishRelationResponseDTO> relations;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public List<IdLabel> getTags() {
        return tags;
    }

    public void setTags(List<IdLabel> tags) {
        this.tags = tags;
    }

    public List<IdLabel> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IdLabel> ingredients) {
        this.ingredients = ingredients;
    }

    public List<DishRelationResponseDTO> getRelations() {
        return relations;
    }

    public void setRelations(List<DishRelationResponseDTO> relations) {
        this.relations = relations;
    }
}
