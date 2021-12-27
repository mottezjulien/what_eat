package what.eat.rule.domain.model;

import what.eat.Ports;
import what.eat.recipe.domain.RecipeOutput;
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

    public boolean is(Action action) {
        return this.action.equals(action);
    }

    public Stream<RecipeDish> selectables() {
        Stream<RecipeDish> selectableRootDishes = root().filter(RecipeDish::isSelectable);

        RecipeOutput recipeOutput = Ports.getInstance().recipeOutput();
        Stream<RecipeDish> allSelectableChildren = root().flatMap(recipeOutput::selectableFlatChildren);

        return Stream.concat(selectableRootDishes, allSelectableChildren);
    }

    private Stream<RecipeDish> root() {
        if (recipeIndicator.type() == RecipeIndicator.RecipeIndicatorType.DISH) {
            return Stream.of((RecipeDish) recipeIndicator);
        }
        return Stream.empty();
    }



    /*
    public boolean isDish() {
        return recipeIndicator instanceof RecipeDish;
    }*/

}
