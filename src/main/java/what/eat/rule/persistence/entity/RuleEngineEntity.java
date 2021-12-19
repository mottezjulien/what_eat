package what.eat.rule.persistence.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "WHAT_RECIPE_RULE_ENGINE")
public class RuleEngineEntity {

    @Id
    private String id;

    @OneToMany(mappedBy = "ruleEngine")
    private Set<RuleEntity> rules = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<RuleEntity> getRules() {
        return rules;
    }

    public void setRules(Set<RuleEntity> rules) {
        this.rules = rules;
    }
}
