package what.eat.demo;

import what.eat.data.domain.model.DataTag;
import what.eat.generic.brain.BrainQuery;

import java.util.ArrayList;
import java.util.List;

public class DemoRuleGroup {

    private String id;
    private String label;
    private String desc;
    private List<DemoRule> rules = new ArrayList<>();

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public List<DemoRule> getRules() {
        return rules;
    }

    public void setRules(List<DemoRule> rules) {
        this.rules = rules;
    }


    public BrainQuery<DataTag> toQuery() {
        BrainQuery<DataTag> query = new BrainQuery<>();
        rules.forEach(rule ->
        {
            switch (rule.type()) {
                case AT_LEAST -> query.addAsk(rule.tag(), rule.number());
                case MAX -> query.addNoMore(rule.tag(), rule.number());
                case EQUALS -> {
                    query.addAsk(rule.tag(), rule.number());
                    query.addNoMore(rule.tag(), rule.number());
                }
            }
        });
        return query;
    }
}
