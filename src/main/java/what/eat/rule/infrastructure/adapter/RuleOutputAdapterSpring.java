package what.eat.rule.infrastructure.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import what.eat.recipe.domain.model.RecipeDish;
import what.eat.recipe.domain.model.RecipeDishFinal;
import what.eat.recipe.domain.model.RecipeIndicator;
import what.eat.recipe.infrastructure.adapter.RecipeDishEntityWrapper;
import what.eat.recipe.infrastructure.persistence.entity.RecipeDishEntity;
import what.eat.recipe.infrastructure.persistence.entity.RecipeIndicatorAbstractEntity;
import what.eat.rule.domain.model.*;
import what.eat.rule.domain.port.RuleOutput;
import what.eat.rule.persistence.entity.RuleEngineEntity;
import what.eat.rule.persistence.entity.RuleEntity;
import what.eat.rule.persistence.repository.RuleEngineRepository;

import java.util.Optional;

@Component
public class RuleOutputAdapterSpring implements RuleOutput {

    @Autowired
    private RuleEngineRepository ruleEngineRepository;

    @Autowired
    private RecipeDishEntityWrapper dishWrapper;

    @Override
    public Optional<RuleEngine> findAny() {
        return ruleEngineRepository
                .findAllFetchRulesFetchRuleIndicator()
                .stream()
                .findAny()
                .map(entity -> toModel(entity));
    }

    private RuleEngine toModel(RuleEngineEntity entity) {
        RuleEngine ruleEngineRoot = new RuleEngine(entity.getId());
        entity.getRules().forEach(rule -> ruleEngineRoot.insert(toModel(rule)));
        return ruleEngineRoot;
    }

    private Rule toModel(RuleEntity entity) {
        return new Rule(entity.getId(), entity.getPriority(), entity.getComparator(), entity.getValue(), toModel(entity.getComparedTo()));
    }

    private RecipeIndicator toModel(RecipeIndicatorAbstractEntity comparedTo) {
        if(comparedTo instanceof RecipeDishEntity) {
            return dishWrapper.toModel((RecipeDishEntity) comparedTo);
        }
        /*if(comparedTo instanceof RecipeTagEntity) {
            return toTagModel((RecipeTagEntity) comparedTo);
        }
        if(comparedTo instanceof RecipeIngredientEntity) {

        }*/
        throw new IllegalArgumentException();
    }

}
