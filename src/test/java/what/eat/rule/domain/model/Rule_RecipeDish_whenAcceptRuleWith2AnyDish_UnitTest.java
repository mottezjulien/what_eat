package what.eat.rule.domain.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import what.eat.Ports;
import what.eat.menu.domain.model.DefinedMenu;
import what.eat.menu.domain.model.MenuSchedule;
import what.eat.recipe.domain.RecipeOutput;
import what.eat.recipe.domain.model.RecipeDish;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class Rule_RecipeDish_whenAcceptRuleWith2AnyDish_UnitTest {

    private final RecipeDish anyDish = new RecipeDish("any", "any");

    @BeforeEach
    void setup_givenRuleWithEqualsParentA() {
        Ports ports = new Ports(null, null, mock(RecipeOutput.class));
        Ports.setInstance(ports);
    }

    @Test
    public void giverEquals2AbsentDish_thenNotAccept() {
        thenNotAcceptExempleSchedule(absentDishRule(RuleComparator.EQUALS, 2));
    }

    @Test
    public void giverEquals0AbsentDish_thenAccept() {
        thenAcceptExempleSchedule(absentDishRule(RuleComparator.EQUALS, 0));
    }

    @Test
    public void giverEquals2AnyDish_thenAccept() {
        thenAcceptExempleSchedule(anyDishRule(RuleComparator.EQUALS, 2));
    }

    @Test
    public void giverEquals3AnyDish_thenNotAccept() {
        thenNotAcceptExempleSchedule(anyDishRule(RuleComparator.EQUALS, 3));
    }

    @Test
    public void giverMin2AbsentDish_thenNotAccept() {
        thenNotAcceptExempleSchedule(absentDishRule(RuleComparator.MIN, 0));
    }

    @Test
    public void giverMinMinus1AbsentDish_thenAccept() {
        thenAcceptExempleSchedule(absentDishRule(RuleComparator.MIN, -1));
    }

    @Test
    public void giverMin1AnyDish_thenAccept() {
        thenAcceptExempleSchedule(anyDishRule(RuleComparator.MIN, 1));
    }

    @Test
    public void giverMin2AnyDish_thenNotAccept() {
        thenNotAcceptExempleSchedule(anyDishRule(RuleComparator.MIN, 2));
    }

    @Test
    public void giverMax0AbsentDish_thenNotAccept() {
        thenNotAcceptExempleSchedule(absentDishRule(RuleComparator.MAX, 0));
    }

    @Test
    public void giverMax1AbsentDish_thenAccept() {
        thenAcceptExempleSchedule(absentDishRule(RuleComparator.MAX, 1));
    }

    @Test
    public void giverMax3AnyDish_thenAccept() {
        thenAcceptExempleSchedule(anyDishRule(RuleComparator.MAX, 3));
    }

    @Test
    public void giverMax2AnyDish_thenNotAccept() {
        thenNotAcceptExempleSchedule(anyDishRule(RuleComparator.MAX, 2));
    }

    private Rule absentDishRule(RuleComparator comparator, int value) {
        return new Rule("", RulePriority.MUST, comparator, value, new RecipeDish("absent", ""));
    }

    private Rule anyDishRule(RuleComparator comparator, int value) {
        return new Rule("", RulePriority.MUST, comparator, value, anyDish);
    }

    private void thenAcceptExempleSchedule(Rule rule) {
        assertTrue(rule.accept(exampleSchedule()));
    }

    private void thenNotAcceptExempleSchedule(Rule rule) {
        assertFalse(rule.accept(exampleSchedule()));
    }

    private MenuSchedule exampleSchedule() {
        MenuSchedule menuSchedule = new MenuSchedule();
        menuSchedule.insert(new DefinedMenu(anyDish, LocalDate.now().plusDays(1)));
        menuSchedule.insert(new DefinedMenu(new RecipeDish("other", ""), LocalDate.now().plusDays(2)));
        menuSchedule.insert(new DefinedMenu(anyDish, LocalDate.now().plusDays(3)));
        return menuSchedule;
    }

}