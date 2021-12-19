package what.eat.recipe.infrastructure.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "WHAT_RECIPE_INDICATOR")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
public abstract class RecipeIndicatorAbstractEntity {

    @Id
    private String id;

    private String label;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
