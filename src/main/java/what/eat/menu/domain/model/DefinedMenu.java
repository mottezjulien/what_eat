package what.eat.menu.domain.model;

import what.eat.recipe.domain.model.RecipeDish;
import what.eat.recipe.domain.model.RecipeIndicator;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class DefinedMenu implements Menu {

    private final String internalId;
    private final RecipeDish dish;
    private final LocalDate date;

    public DefinedMenu(RecipeDish dish, LocalDate date) {
        this(UUID.randomUUID().toString(), dish, date);
    }

    public DefinedMenu(String internalId, RecipeDish dish, LocalDate date) {
        this.internalId = internalId;
        this.dish = dish;
        this.date = date;
    }

    public String internalId() {
        return internalId;
    }

    public LocalDate date() {
        return date;
    }

    public String dishInternalId() {
        return dish.internalId();
    }

    public String dishLabel() {
        return dish.label();
    }

    public boolean has(RecipeIndicator compareTo) {
        return dish.has(compareTo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefinedMenu menu = (DefinedMenu) o;
        return Objects.equals(internalId, menu.internalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(internalId);
    }

}
