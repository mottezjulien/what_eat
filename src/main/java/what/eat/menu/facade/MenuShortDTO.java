package what.eat.menu.facade;

import com.fasterxml.jackson.annotation.JsonProperty;
import what.eat.generic.facade.LinkDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MenuShortDTO {

    private LocalDate date;

    private DishShortDTO dish;

    @JsonProperty("_links")
    private List<LinkDTO> links = new ArrayList<>();

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public DishShortDTO getDish() {
        return dish;
    }

    public void setDish(DishShortDTO dish) {
        this.dish = dish;
    }

    public List<LinkDTO> getLinks() {
        return links;
    }

    public void setLinks(List<LinkDTO> links) {
        this.links = links;
    }
}
