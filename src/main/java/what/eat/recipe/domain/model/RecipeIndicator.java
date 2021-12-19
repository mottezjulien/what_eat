package what.eat.recipe.domain.model;

public interface RecipeIndicator {

    public enum RecipeIndicatorType {
        DISH, INGREDIENT, TAG
    }

    RecipeIndicatorType type();
}
