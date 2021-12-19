package what.eat.recipe.infrastructure.persistence.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "WHAT_RECIPE_INGREDIENT")
@DiscriminatorValue("INGREDIENT")
public class RecipeIngredientEntity extends RecipeIndicatorAbstractEntity {

    @ManyToMany
    @JoinTable(
            name = "WHAT_RECIPE_INGREDIENT_TAG",
            joinColumns = @JoinColumn(name = "ingredient_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<RecipeTagEntity> tags = new HashSet<>();

    public Set<RecipeTagEntity> getTags() {
        return tags;
    }

    public void setTags(Set<RecipeTagEntity> tags) {
        this.tags = tags;
    }
}
