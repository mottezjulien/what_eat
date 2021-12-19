package what.eat.rule.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import what.eat.Ports;
import what.eat.menu.domain.model.DefinedMenu;
import what.eat.menu.domain.model.MenuSchedule;
import what.eat.menu.domain.port.MenuOutput;
import what.eat.recipe.domain.RecipeOutput;
import what.eat.recipe.domain.model.RecipeDishAbstract;
import what.eat.recipe.domain.model.RecipeDishFinal;
import what.eat.recipe.domain.model.RecipeDishFinalSimple;
import what.eat.rule.domain.port.RuleOutput;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RuleEngine_generateSimple_Test {

    private MenuOutput menuOutput = mock(MenuOutput.class);
    private RuleOutput ruleOutput = mock(RuleOutput.class);
    private RecipeOutput recipeOutput = mock(RecipeOutput.class);

    private RecipeDishFinal any = new RecipeDishFinalSimple("any", "any");

    @BeforeEach
    void setup() {
        Ports ports = new Ports(menuOutput, ruleOutput, recipeOutput);
        Ports.setInstance(ports);

        when(recipeOutput.anyFinal()).thenReturn(Optional.of(any));
    }

    @Test
    public void shouldFindAnyIfAnyRule() throws RuleEngineException {
        RuleEngine ruleEngine = new RuleEngine();
        assertThat(ruleEngine.generate(new MenuSchedule(), LocalDate.now()))
                .isEqualTo(any);
    }

    @Test
    public void shouldPrioriseSelectedElementIfNotAvailable_minusWay() throws RuleEngineException {
        RecipeDishFinalSimple boeufCarotte = new RecipeDishFinalSimple("idBoeufCarotte", "Boeuf Carotte");

        RuleEngine ruleEngine = new RuleEngine();
        Rule rule = new Rule("", RulePriority.MUST, RuleComparator.MIN, 1, boeufCarotte);
        ruleEngine.insert(rule);
        assertThat(ruleEngine.generate(new MenuSchedule(), LocalDate.now()))
                .isEqualTo(boeufCarotte);
    }

    @Test
    public void shouldAnyIfEnough_minusWay() throws RuleEngineException {
        RecipeDishFinalSimple boeufCarotte = new RecipeDishFinalSimple("idBoeufCarotte", "Boeuf Carotte");

        RuleEngine ruleEngine = new RuleEngine();
        Rule rule = new Rule("", RulePriority.MUST, RuleComparator.MIN, 1, boeufCarotte);
        ruleEngine.insert(rule);

        MenuSchedule menuSchedule = new MenuSchedule();
        menuSchedule.insert(new DefinedMenu(boeufCarotte, LocalDate.now()));

        assertThat(ruleEngine.generate(menuSchedule, LocalDate.now()))
                .isEqualTo(any);
    }


}