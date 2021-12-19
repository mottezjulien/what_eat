package what.eat.rule.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import what.eat.Ports;
import what.eat.menu.domain.model.MenuSchedule;
import what.eat.menu.domain.port.MenuOutput;
import what.eat.recipe.domain.RecipeOutput;
import what.eat.recipe.domain.model.RecipeDishAbstract;
import what.eat.recipe.domain.model.RecipeDishFinal;
import what.eat.recipe.domain.model.RecipeDishFinalSimple;
import what.eat.rule.domain.port.RuleOutput;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RuleEngine_generateWithChildren_Test {

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
    public void shouldSelectedChildrenToo_equalsWay() throws RuleEngineException {

        RecipeDishAbstract absPate = new RecipeDishAbstract("idPate", "Pate");

        RecipeDishFinalSimple pateBolo = new RecipeDishFinalSimple("idPateBolo", "Pate Bolo");
        RecipeDishFinalSimple pateCarbo = new RecipeDishFinalSimple("idPateCarbo", "Pate Carbo");

        when(recipeOutput.finalChildren(absPate)).thenReturn(Stream.of(pateBolo, pateCarbo));

        RuleEngine ruleEngine = new RuleEngine();
        Rule rule = new Rule("", RulePriority.MUST, RuleComparator.EQUALS, 2, absPate);
        ruleEngine.insert(rule);
        assertThat(ruleEngine.generate(new MenuSchedule(), LocalDate.now()))
                .satisfiesAnyOf(satisfy1 -> assertThat(satisfy1).isEqualTo(pateBolo), satisfy2 -> assertThat(satisfy2).isEqualTo(pateCarbo));
    }

}