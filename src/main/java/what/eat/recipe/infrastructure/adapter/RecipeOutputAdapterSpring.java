package what.eat.recipe.infrastructure.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import what.eat.recipe.domain.RecipeOutput;
import what.eat.recipe.domain.model.RecipeDish;
import what.eat.recipe.domain.model.RecipeDishFinal;
import what.eat.recipe.domain.model.RecipeDishType;
import what.eat.recipe.infrastructure.persistence.entity.RecipeDishEntity;
import what.eat.recipe.infrastructure.persistence.repository.RecipeDishRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Component
public class RecipeOutputAdapterSpring implements RecipeOutput {

    @Autowired
    private RecipeDishRepository dishRepository;

    @Autowired
    private RecipeDishEntityWrapper dishWrapper;

    @Override
    public Optional<RecipeDishFinal> anyFinal() {
        return dishRepository.findFinals()
                .stream().findAny()
                .map(dishWrapper::toFinalModel);
    }

    @Override
    public Stream<RecipeDishFinal> finalChildren(RecipeDish parent) {

        RecipeDishEntity entity = dishRepository
                .findById_FetchAllChildren(parent.internalId())
                .orElseGet(RecipeDishEntity::new);

        return recursivelyFlat(entity)
                .stream()
                .filter(isFinal())
                .map(dishWrapper::toFinalModel);
    }

    private List<RecipeDishEntity> recursivelyFlat(RecipeDishEntity entity) {
        List<RecipeDishEntity> result = new ArrayList<>();
        result.add(entity);
        entity.getChildren().forEach(child -> result.addAll(recursivelyFlat(child)));
        return result;
    }

    private Predicate<RecipeDishEntity> isFinal() {
        return dish -> dish.getType() == RecipeDishType.FINAL_SIMPLE || dish.getType() == RecipeDishType.FINAL_COMPOSITE;
    }

}
