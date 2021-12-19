package what.eat.rule.domain.model;

import what.eat.recipe.domain.model.RecipeDish;
import what.eat.recipe.domain.model.RecipeIndicator;

import java.util.stream.Stream;

public class RuleStrategyChoice {

    public enum Action {
        STOP, INCREASE;
    }

    private final Action action;

    private final RecipeIndicator recipeIndicator;

    public RuleStrategyChoice(Action action, RecipeIndicator recipeIndicator) {
        this.action = action;
        this.recipeIndicator = recipeIndicator;
    }

    public RecipeIndicator indicator() {
        return recipeIndicator;
    }

    public boolean is(Action action) {
        return this.action.equals(action);
    }

    public Stream<RecipeDish> selectedDishes() {
        switch (recipeIndicator.type()) {
            case DISH:
                return Stream.of((RecipeDish)recipeIndicator);
            default:
                return Stream.empty();
        }
    }



    /*
    public boolean isDish() {
        return recipeIndicator instanceof RecipeDish;
    }*/

}
