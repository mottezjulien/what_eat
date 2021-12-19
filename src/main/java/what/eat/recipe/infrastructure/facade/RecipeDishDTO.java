package what.eat.recipe.infrastructure.facade;

import com.fasterxml.jackson.annotation.JsonProperty;
import what.eat.generic.facade.LinkDTO;

import java.util.ArrayList;
import java.util.List;

public class RecipeDishDTO {

    private String id;

    private String label;

    @JsonProperty("_links")
    private List<LinkDTO> links = new ArrayList<>();

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<LinkDTO> getLinks() {
        return links;
    }

    public void setLinks(List<LinkDTO> links) {
        this.links = links;
    }


}
