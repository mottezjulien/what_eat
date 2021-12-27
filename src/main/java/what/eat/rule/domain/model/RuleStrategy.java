package what.eat.rule.domain.model;

import what.eat.Ports;
import what.eat.recipe.domain.RecipeOutput;
import what.eat.recipe.domain.model.RecipeDish;
import what.eat.utils.ListUtils;

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

    public Optional<RecipeDish> findOne() {


        RecipeOutput recipeOutput = Ports.getInstance().recipeOutput();
        if (choices.isEmpty()) {
            return recipeOutput.anySelectable();
        }

        List<RecipeDish> reduces = choices.stream()
                .filter(choice -> choice.is(RuleStrategyChoice.Action.STOP))
                .flatMap(RuleStrategyChoice::selectables)
                .distinct()
                .collect(Collectors.toList());

        List<RecipeDish> found = choices.stream()
                .filter(choice -> choice.is(RuleStrategyChoice.Action.INCREASE))
                .flatMap(RuleStrategyChoice::selectables)
                .distinct()
                .filter(eachFound -> !reduces.contains(eachFound))
                .collect(Collectors.toList());

        if (found.isEmpty()) {
            found = recipeOutput.allSelectables()
                    .filter(eachFound -> !reduces.contains(eachFound))
                    .collect(Collectors.toList());
        }
        if (found.isEmpty()) {
            return recipeOutput.anySelectable();
        }
        return ListUtils.getRandom(found);
    }
}
