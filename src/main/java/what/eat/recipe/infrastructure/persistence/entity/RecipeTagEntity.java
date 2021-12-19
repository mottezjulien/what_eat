package what.eat.recipe.infrastructure.persistence.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "WHAT_RECIPE_TAG")
@DiscriminatorValue("TAG")
public class RecipeTagEntity extends RecipeIndicatorAbstractEntity {

}
