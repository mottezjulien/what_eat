package what.eat.recipe.infrastructure.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import what.eat.recipe.domain.RecipeOutput;
import what.eat.recipe.domain.model.RecipeDish;
import what.eat.recipe.domain.model.RecipeDishType;
import what.eat.recipe.infrastructure.persistence.entity.RecipeDishEntity;
import what.eat.recipe.infrastructure.persistence.repository.RecipeDishRepository;
import what.eat.utils.ListUtils;
import what.eat.utils.StreamUtils;

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
    public Stream<RecipeDish> allSelectables() {
        return dishRepository.findAllSelectable()
                .stream().map(dishWrapper::toModel);
    }

    @Override
    public Optional<RecipeDish> anySelectable() {
        return ListUtils.getRandom(dishRepository.findAllSelectable())
                .map(dishWrapper::toModel);
    }

    @Override
    public Stream<RecipeDish> selectableFlatChildren(RecipeDish parent) {
        RecipeDishEntity entity = dishRepository
                .findById_FetchAllChildren(parent.internalId())
                .orElseGet(RecipeDishEntity::new);
        return recursivelyFlat(entity)
                .stream()
                .filter(isSelectable())
                .map(dishWrapper::toModel);
    }

    @Override
    public Stream<RecipeDish> flatChildren(RecipeDish parent) {
        RecipeDishEntity entity = dishRepository
                .findById_FetchAllChildren(parent.internalId())
                .orElseGet(RecipeDishEntity::new);
        return recursivelyFlat(entity)
                .stream()
                .map(dishWrapper::toModel);
    }

    private List<RecipeDishEntity> recursivelyFlat(RecipeDishEntity entity) {
        List<RecipeDishEntity> result = new ArrayList<>();
        result.add(entity);
        entity.getChildren().forEach(child -> result.addAll(recursivelyFlat(child)));
        return result;
    }

    private Predicate<RecipeDishEntity> isSelectable() {
        return dish -> dish.getType() == RecipeDishType.SELECTABLE_SIMPLE || dish.getType() == RecipeDishType.SELECTABLE_COMPOSITE;
    }

}
