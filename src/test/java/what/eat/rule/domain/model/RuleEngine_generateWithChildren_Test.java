package what.eat.rule.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import what.eat.Ports;
import what.eat.menu.domain.model.MenuSchedule;
import what.eat.menu.domain.port.MenuOutput;
import what.eat.recipe.domain.RecipeOutput;
import what.eat.recipe.domain.model.RecipeDish;
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

    private RecipeDish any = new RecipeDish("any", "any", RecipeDish.RecipeDishType.SIMPLE);

    @BeforeEach
    void setup() {
        Ports ports = new Ports(menuOutput, ruleOutput, recipeOutput);
        Ports.setInstance(ports);

        when(recipeOutput.anySelectable()).thenReturn(Optional.of(any));
    }

    @Test
    public void shouldSelectedChildrenToo_equalsWay() throws RuleEngineException {

        RecipeDish missingPate = new RecipeDish("idPate", "Pate", RecipeDish.RecipeDishType.MISSING);

        RecipeDish pateBolo = new RecipeDish("idPateBolo", "Pate Bolo", RecipeDish.RecipeDishType.SIMPLE);
        RecipeDish pateCarbo = new RecipeDish("idPateCarbo", "Pate Carbo", RecipeDish.RecipeDishType.SIMPLE);

        when(recipeOutput.selectableFlatChildren(missingPate)).thenReturn(Stream.of(pateBolo, pateCarbo));

        RuleEngine ruleEngine = new RuleEngine();
        Rule rule = new Rule("", RulePriority.MUST, RuleComparator.EQUALS, 2, missingPate);
        ruleEngine.insert(rule);
        assertThat(ruleEngine.generate(new MenuSchedule(), LocalDate.now()))
                .satisfiesAnyOf(satisfy1 -> assertThat(satisfy1).isEqualTo(pateBolo), satisfy2 -> assertThat(satisfy2).isEqualTo(pateCarbo));
    }

}