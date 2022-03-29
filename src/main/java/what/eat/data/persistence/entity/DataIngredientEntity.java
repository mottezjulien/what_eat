package what.eat.data.persistence.entity;

import org.hibernate.Hibernate;
import what.eat.data.domain.model.DataId;
import what.eat.data.domain.model.DataIngredient;
import what.eat.data.domain.model.DataTag;
import what.eat.utils.ListUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "DATA_INGREDIENT")
public class DataIngredientEntity {

    public DataIngredient toModel() {
        DataIngredient ingredient = new DataIngredient(getId(), getFrLabel());
        if(Hibernate.isInitialized(getTags())) {
            ingredient.addAll(ListUtils.map(getTags(), DataTagEntity::toModel));
        }
        return ingredient;
    }

    @Id
    private String id;

    @Column(name = "fr_label")
    private String frLabel;

    @ManyToMany
    @JoinTable(name = "DATA_LINK_INGREDIENT_TAG",
            joinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private Set<DataTagEntity> tags = new HashSet<>();

    public DataIngredientEntity() {
        this(UUID.randomUUID().toString(), "");
    }

    public DataIngredientEntity(String frLabel) {
        this(UUID.randomUUID().toString(), frLabel);
    }

    public DataIngredientEntity(String id, String frLabel) {
        this.id = id;
        this.frLabel = frLabel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrLabel() {
        return frLabel;
    }

    public void setFrLabel(String frLabel) {
        this.frLabel = frLabel;
    }

    public Set<DataTagEntity> getTags() {
        return tags;
    }

    public void setTags(Set<DataTagEntity> tags) {
        this.tags = tags;
    }


}
