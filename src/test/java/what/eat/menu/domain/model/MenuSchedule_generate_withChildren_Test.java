package what.eat.menu.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import what.eat.Ports;
import what.eat.menu.domain.port.MenuOutput;
import what.eat.recipe.domain.RecipeOutput;
import what.eat.recipe.domain.model.RecipeDish;
import what.eat.rule.domain.model.Rule;
import what.eat.rule.domain.model.RuleComparator;
import what.eat.rule.domain.model.RuleEngine;
import what.eat.rule.domain.model.RulePriority;
import what.eat.rule.domain.port.RuleOutput;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MenuSchedule_generate_withChildren_Test {

    private MenuOutput menuOutput = mock(MenuOutput.class);
    private RuleOutput ruleOutput = mock(RuleOutput.class);
    private RecipeOutput recipeOutput = mock(RecipeOutput.class);

    private RecipeDish any = new RecipeDish("any", "any");

    private RecipeDish parentA = new RecipeDish("", "Parent A", RecipeDish.RecipeDishType.MISSING);
    private RecipeDish childA = new RecipeDish("", "Child A");

    @BeforeEach
    void setup_givenRuleWithEqualsParentA() {
        Ports ports = new Ports(menuOutput, ruleOutput, recipeOutput);
        Ports.setInstance(ports);

        when(recipeOutput.anySelectable()).thenReturn(Optional.of(any));

        when(recipeOutput.selectableFlatChildren(parentA)).thenReturn(Stream.of(childA));
        when(recipeOutput.flatChildren(parentA)).thenReturn(Stream.of(childA));

        Rule ruleParentAEquals1 = new Rule("", RulePriority.MUST, RuleComparator.EQUALS, 1, parentA);
        RuleEngine ruleEngine = new RuleEngine();
        ruleEngine.insert(ruleParentAEquals1);
        when(ruleOutput.findAny()).thenReturn(Optional.of(ruleEngine));
    }

    @Test
    public void whenScheduleWithoutParentAFamilyDish_thenGenerateAMenuFromParentADish() throws MenuWeekException {
        MenuSchedule menuSchedule = new MenuSchedule();
        menuSchedule.insert(new DefinedMenu(any, LocalDate.now()));

        DefinedMenu menu = menuSchedule.findOrGenerate(LocalDate.now().plusDays(1));
        assertThat(menu.dishLabel())
                .isEqualTo("Child A");
    }

    @Test
    public void whenScheduleWithAParentAFamilyDish_thenGenerateMenuWithAnyDish() throws MenuWeekException {
        MenuSchedule menuSchedule = new MenuSchedule();
        menuSchedule.insert(new DefinedMenu(childA, LocalDate.now()));

        DefinedMenu menu = menuSchedule.findOrGenerate(LocalDate.now().plusDays(1));
        assertThat(menu.dishLabel()).isEqualTo("any");
    }

}