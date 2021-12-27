package what.eat.recipe.domain.model;

public interface RecipeIndicator {

    enum RecipeIndicatorType {
        DISH, INGREDIENT, TAG
    }

    RecipeIndicatorType type();

}
