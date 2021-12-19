package what.eat.recipe.domain.model;

public interface RecipeDish extends RecipeIndicator {

    String internalId();

    boolean isFinal();

    @Override
    public default RecipeIndicatorType type() {
        return RecipeIndicatorType.DISH;
    }

}
