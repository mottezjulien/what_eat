package what.eat;

import what.eat.menu.domain.port.MenuOutput;
import what.eat.recipe.domain.RecipeOutput;
import what.eat.rule.domain.port.RuleOutput;

public class Ports {

    private static Ports instance;

    public static Ports getInstance() {
        return instance;
    }

    public static void setInstance(Ports instance) {
        Ports.instance = instance;
    }

    private final MenuOutput menuOutput;
    private final RuleOutput ruleOutput;
    private final RecipeOutput recipeOutput;

    public Ports(MenuOutput menuOutput, RuleOutput ruleOutput, RecipeOutput recipeOutput) {
        this.menuOutput = menuOutput;
        this.ruleOutput = ruleOutput;
        this.recipeOutput = recipeOutput;
    }

    public MenuOutput menuOutput() {
        return menuOutput;
    }

    public RuleOutput ruleOutput() {
        return ruleOutput;
    }

    public RecipeOutput recipeOutput() {
        return recipeOutput;
    }
}
