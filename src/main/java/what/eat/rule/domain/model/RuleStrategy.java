package what.eat.rule.domain.model;

import what.eat.Ports;
import what.eat.recipe.domain.RecipeOutput;
import what.eat.recipe.domain.model.RecipeDish;
import what.eat.recipe.domain.model.RecipeDishFinal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RuleStrategy {

    private final List<RuleStrategyChoice> choices = new ArrayList<>();

    public void insert(RuleStrategyChoice choice) {
        choices.add(choice);
    }

    public Optional<RecipeDishFinal> findOne() {
        RecipeOutput recipeOutput = Ports.getInstance().recipeOutput();
        if (choices.isEmpty()) {
            return recipeOutput.anyFinal();
        }
        List<RecipeDishFinal> found = choices.stream()
                .filter(choice -> choice.is(RuleStrategyChoice.Action.INCREASE))
                .flatMap(this::withChildren)
                .distinct()
                .collect(Collectors.toList());
        if (found.isEmpty()) {
            return recipeOutput.anyFinal();
        }
        return found.stream().findAny();
    }

    private Stream<RecipeDishFinal> withChildren(RuleStrategyChoice choice) {
        RecipeOutput recipeOutput = Ports.getInstance().recipeOutput();
        Stream<RecipeDish> selectedDishes = choice.selectedDishes();
        return selectedDishes.flatMap(recipeDish -> {
            Stream<RecipeDishFinal> children = recipeOutput.finalChildren(recipeDish);
            if(recipeDish.isFinal()) {
                return Stream.concat(Stream.of((RecipeDishFinal)recipeDish), children);
            }
            return children;
        });
    }


}
