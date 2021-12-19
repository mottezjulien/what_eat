package what.eat.rule.persistence.entity;

import what.eat.recipe.infrastructure.persistence.entity.RecipeIndicatorAbstractEntity;
import what.eat.rule.domain.model.RuleComparator;
import what.eat.rule.domain.model.RulePriority;

import javax.persistence.*;
import java.time.DayOfWeek;

@Entity
@Table(name = "WHAT_RECIPE_RULE")
public class RuleEntity {

    //day of week ??

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private RuleComparator comparator;

    @ManyToOne
    @JoinColumn(name = "compared_to_indicator_id")
    private RecipeIndicatorAbstractEntity comparedTo;

    @ManyToOne
    @JoinColumn(name = "rule_engine_id")
    private RuleEngineEntity ruleEngine;

    private int value;

    @Enumerated(EnumType.STRING)
    private RulePriority priority;

    @Enumerated(EnumType.STRING)
    @Column(name = "optional_day_of_week")
    private DayOfWeek optionalDayOfWeek;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RuleComparator getComparator() {
        return comparator;
    }

    public void setComparator(RuleComparator comparator) {
        this.comparator = comparator;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public RulePriority getPriority() {
        return priority;
    }

    public void setPriority(RulePriority priority) {
        this.priority = priority;
    }

    public RecipeIndicatorAbstractEntity getComparedTo() {
        return comparedTo;
    }

    public void setComparedTo(RecipeIndicatorAbstractEntity comparedTo) {
        this.comparedTo = comparedTo;
    }

    public DayOfWeek getOptionalDayOfWeek() {
        return optionalDayOfWeek;
    }

    public void setOptionalDayOfWeek(DayOfWeek optionalDayOfWeek) {
        this.optionalDayOfWeek = optionalDayOfWeek;
    }

    public RuleEngineEntity getRuleEngine() {
        return ruleEngine;
    }

    public void setRuleEngine(RuleEngineEntity ruleEngine) {
        this.ruleEngine = ruleEngine;
    }
}
