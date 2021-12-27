package what.eat.recipe.domain.model;

import what.eat.Ports;

import java.util.Objects;
import java.util.stream.Stream;

public class RecipeDish implements RecipeIndicator {

    public enum RecipeDishType {
        MISSING, SIMPLE, COMPOSITE
    }

    private final String internalId;
    private final String label;
    private final RecipeDishType type;

    public RecipeDish(String internalId, String label) {
        this(internalId, label, RecipeDishType.SIMPLE);
    }

    public RecipeDish(String internalId, String label, RecipeDishType type) {
        this.internalId = internalId;
        this.label = label;
        this.type = type;
    }

    public String internalId() {
        return internalId;
    }

    public String label() {
        return label;
    }

    @Override
    public RecipeIndicatorType type() {
        return RecipeIndicatorType.DISH;
    }

    public boolean has(RecipeIndicator toCompare) {
        if (toCompare.type() == RecipeIndicatorType.DISH) {
            RecipeDish parent = (RecipeDish) toCompare;
            if (this.equals(parent)) {
                return true;
            }
            return Ports.getInstance().recipeOutput()
                    .flatChildren(parent)
                    .anyMatch(each -> each.equals(this));
        }
        return false;
    }

    public boolean isSelectable() {
        return type == RecipeDishType.SIMPLE || type == RecipeDishType.COMPOSITE;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeDish that = (RecipeDish) o;
        return Objects.equals(internalId, that.internalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(internalId);
    }
}
