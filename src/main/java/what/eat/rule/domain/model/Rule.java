package what.eat.rule.domain.model;

import what.eat.menu.domain.model.DefinedMenu;
import what.eat.utils.StreamUtils;
import what.eat.menu.domain.model.MenuSchedule;
import what.eat.recipe.domain.model.RecipeIndicator;

import java.time.LocalDate;
import java.util.Optional;

public class Rule {

    private final String id;
    private final RulePriority priority;
    private final RuleComparator comparator;
    private final int value;
    private final RecipeIndicator compareTo;

    public Rule(String id, RulePriority priority, RuleComparator comparator, int value, RecipeIndicator compareTo) {
        this.id = id;
        this.priority = priority;
        this.comparator = comparator;
        this.value = value;
        this.compareTo = compareTo;
    }

    public Optional<RuleStrategyChoice> strategyChoice(MenuSchedule menuSchedule, LocalDate day) {
        int count = countAccepted(menuSchedule);
        return strategyChoiceWithValidCpt(count);
    }

    private Optional<RuleStrategyChoice> strategyChoiceWithValidCpt(int count) {
        switch (comparator) {
            case MIN -> {
                if(count < value) {
                    return Optional.of(new RuleStrategyChoice(RuleStrategyChoice.Action.INCREASE, compareTo));
                }
                return Optional.empty();
            }
            case MAX -> {
                if(count >= value) {
                    return Optional.of(new RuleStrategyChoice(RuleStrategyChoice.Action.STOP, compareTo));
                }
                return Optional.empty();
            }
            case EQUALS -> {
                if(count < value) {
                    return Optional.of(new RuleStrategyChoice(RuleStrategyChoice.Action.INCREASE, compareTo));
                }
                return Optional.of(new RuleStrategyChoice(RuleStrategyChoice.Action.STOP, compareTo));
            }
        }
        return Optional.empty();
    }

    private int countAccepted(MenuSchedule schedule) {
        return StreamUtils.count(schedule.stream(), menu -> {
            if(menu instanceof DefinedMenu) {
                return ((DefinedMenu)menu).accept(compareTo);
            }
            return false;

        });
    }


    //TODO Useless ?
    public boolean accept(MenuSchedule schedule) {
        return compareWithValidCpt(countAccepted(schedule));
    }

    private boolean compareWithValidCpt(int count) {
        switch (comparator) {
            case MIN -> {
                return count > value;
            }
            case MAX -> {
                return count < value;
            }
            case EQUALS -> {
                return count == value;
            }
        }
        return false;
    }


}
