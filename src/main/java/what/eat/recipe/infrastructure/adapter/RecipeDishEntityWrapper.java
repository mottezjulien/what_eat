package what.eat.recipe.infrastructure.adapter;

import org.springframework.stereotype.Component;
import what.eat.recipe.domain.model.*;
import what.eat.recipe.infrastructure.persistence.entity.RecipeDishEntity;

@Component
public class RecipeDishEntityWrapper {

    public RecipeDish toModel(RecipeDishEntity entity) {
        return switch (entity.getType()) {
            case MISSING -> new RecipeDish(entity.getId(), entity.getLabel(), RecipeDish.RecipeDishType.MISSING);
            case SELECTABLE_SIMPLE -> new RecipeDish(entity.getId(), entity.getLabel(), RecipeDish.RecipeDishType.SIMPLE);
            case SELECTABLE_COMPOSITE -> new RecipeDish(entity.getId(), entity.getLabel(), RecipeDish.RecipeDishType.COMPOSITE);
        };
    }

}