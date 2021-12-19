package what.eat.menu.facade;

import com.fasterxml.jackson.annotation.JsonProperty;
import what.eat.generic.facade.LinkDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MenuDTO {

    private String dishLabel;

    private LocalDate date;

    @JsonProperty("_links")
    private List<LinkDTO> links = new ArrayList<>();

    public String getDishLabel() {
        return dishLabel;
    }

    public void setDishLabel(String dishLabel) {
        this.dishLabel = dishLabel;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<LinkDTO> getLinks() {
        return links;
    }

    public void setLinks(List<LinkDTO> links) {
        this.links = links;
    }
}
