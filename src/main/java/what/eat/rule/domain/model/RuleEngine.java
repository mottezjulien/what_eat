package what.eat.rule.domain.model;

import what.eat.menu.domain.model.MenuSchedule;
import what.eat.recipe.domain.model.RecipeDish;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RuleEngine {

    private final String id;
    private final List<Rule> rules = new ArrayList<>();

    public RuleEngine(String id) {
        this.id = id;
    }

    public RuleEngine() {
        this(UUID.randomUUID().toString());
    }

    public void insert(Rule rule) {
        rules.add(rule);
    }

    public RecipeDish generate(MenuSchedule menuSchedule, LocalDate day) throws RuleEngineException {
        //TODO -> Only Dish Rule
        RuleStrategy strategy = strategy(menuSchedule, day); //TODO <- NOT BDD, or NOT ?
        return strategy.findOne().orElseThrow(() -> new RuleEngineException("Any dish generated"));
    }

    private RuleStrategy strategy(MenuSchedule menuSchedule, LocalDate day) {
        RuleStrategy strategy = new RuleStrategy();
        rules.forEach(rule -> rule.strategyChoice(menuSchedule, day).ifPresent(strategy::insert));
        return strategy;
    }

}
