package what.eat.recipe.domain;

import what.eat.recipe.domain.model.RecipeDish;

import java.util.Optional;
import java.util.stream.Stream;

public interface RecipeOutput {

    Stream<RecipeDish> allSelectables();

    Optional<RecipeDish> anySelectable();

    Stream<RecipeDish> selectableFlatChildren(RecipeDish parent);

    Stream<RecipeDish> flatChildren(RecipeDish parent);


}

