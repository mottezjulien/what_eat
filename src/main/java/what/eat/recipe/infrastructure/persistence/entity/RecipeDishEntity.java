package what.eat.recipe.infrastructure.persistence.entity;


import what.eat.recipe.domain.model.RecipeDishState;
import what.eat.recipe.domain.model.RecipeDishType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "WHAT_RECIPE_DISH")
@DiscriminatorValue("DISH")
public class RecipeDishEntity extends RecipeIndicatorAbstractEntity {

    /*private String desc;*/

    @ManyToMany(mappedBy = "children")
    private Set<RecipeDishEntity> parents = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "WHAT_RECIPE_DISH_PARENT_CHILD",
            joinColumns = @JoinColumn(name = "child_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id"))
    private Set<RecipeDishEntity> children = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "WHAT_RECIPE_DISH_INGREDIENT",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<RecipeIngredientEntity> ingredients = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "WHAT_RECIPE_DISH_TAG",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<RecipeTagEntity> tags = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private RecipeDishType type;

    @Enumerated(EnumType.STRING)
    private RecipeDishState state;

    /*public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }*/

    public Set<RecipeDishEntity> getParents() {
        return parents;
    }

    public void setParents(Set<RecipeDishEntity> parents) {
        this.parents = parents;
    }

    public Set<RecipeDishEntity> getChildren() {
        return children;
    }

    public void setChildren(Set<RecipeDishEntity> children) {
        this.children = children;
    }

    public Set<RecipeIngredientEntity> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<RecipeIngredientEntity> ingredients) {
        this.ingredients = ingredients;
    }

    public Set<RecipeTagEntity> getTags() {
        return tags;
    }

    public void setTags(Set<RecipeTagEntity> tags) {
        this.tags = tags;
    }

    public void setType(RecipeDishType type) {
        this.type = type;
    }

    public RecipeDishType getType() {
        return type;
    }

    public void setState(RecipeDishState state) {
        this.state = state;
    }

    public RecipeDishState getState() {
        return state;
    }
}
