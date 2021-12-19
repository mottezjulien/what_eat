package what.eat.recipe.infrastructure.adapter;

import org.springframework.stereotype.Component;
import what.eat.recipe.domain.model.*;
import what.eat.recipe.infrastructure.persistence.entity.RecipeDishEntity;

@Component
public class RecipeDishEntityWrapper {

    public RecipeDish toModel(RecipeDishEntity entity) {
        switch (entity.getType()) {
            case ABSTRACT -> new RecipeDishAbstract(entity.getId(), entity.getLabel());
            case FINAL_SIMPLE, FINAL_COMPOSITE -> toFinalModel(entity);
        }
        throw new IllegalArgumentException();
    }

    public RecipeDishFinal toFinalModel(RecipeDishEntity entity) {
        switch (entity.getType()) {
            case FINAL_SIMPLE -> {
                return new RecipeDishFinalSimple(entity.getId(), entity.getLabel());
            }
            case FINAL_COMPOSITE -> {
                return new RecipeDishFinalComposite(entity.getId(), entity.getLabel());
            }
        }
        throw new IllegalArgumentException();
    }
}