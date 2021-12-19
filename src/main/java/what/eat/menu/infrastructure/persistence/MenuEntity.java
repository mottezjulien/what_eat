package what.eat.menu.infrastructure.persistence;

import what.eat.recipe.infrastructure.persistence.entity.RecipeDishEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "WHAT_MENU")
public class MenuEntity {

    @Id
    private String id;

    //private String externalId;


    @JoinColumn(name = "dish_id")
    @ManyToOne
    private RecipeDishEntity dish;

    @JoinColumn(name = "week_id")
    @ManyToOne
    private MenuWeekEntity week;

    private LocalDate date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RecipeDishEntity getDish() {
        return dish;
    }

    public void setDish(RecipeDishEntity dish) {
        this.dish = dish;
    }

    public MenuWeekEntity getWeek() {
        return week;
    }

    public void setWeek(MenuWeekEntity week) {
        this.week = week;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
