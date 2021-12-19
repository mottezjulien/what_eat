package what.eat.recipe.domain;

import what.eat.recipe.domain.model.RecipeDish;
import what.eat.recipe.domain.model.RecipeDishFinal;

import java.util.Optional;
import java.util.stream.Stream;

public interface RecipeOutput {

    Optional<RecipeDishFinal> anyFinal();

    Stream<RecipeDishFinal> finalChildren(RecipeDish parent);

}

