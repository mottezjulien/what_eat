package what.eat.data.persistence.entity;

import what.eat.data.domain.model.DataDishAtom;
import what.eat.data.domain.model.DataDishElement;
import what.eat.data.domain.model.DataDishRelationAtom;
import what.eat.data.domain.model.DataId;
import what.eat.utils.ListUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "DATA_DISH")
public class DataDishEntity {

    public enum Type {
        DISH_READY, DISH_TEMPLATE, NODE_ITEM, NODE_GENERIC;

        public DataDishElement.Type toModel() {
            return DataDishElement.Type.valueOf(this.name());
        }
    }

    public DataDishElement toDishElementModel() {
        return new DataDishElement(getType().toModel(), toAtomicModel());
    }

    public DataDishAtom toAtomicModel() {
        DataDishAtom dishAtom = new DataDishAtom(getId(), getFrLabel());
        dishAtom.addAllTags(ListUtils.map(tags, DataTagEntity::toModel));
        dishAtom.addAllIngredients(ListUtils.map(ingredients, DataIngredientEntity::toModel));
        return dishAtom;
    }

    public List<DataDishRelationAtom> toRelationModels() {
        return getParentRelations().stream()
                .map(relationEntity -> new DataDishRelationAtom(relationEntity.getType().toModel(), new DataId(relationEntity.getParent().getId(), relationEntity.getParent().getFrLabel())))
                .collect(Collectors.toList());
    }

    @Id
    private String id;

    @Column(name = "fr_label")
    private String frLabel;

    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToMany
    @JoinTable(name = "DATA_LINK_DISH_TAG",
            joinColumns = @JoinColumn(name = "dish_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private Set<DataTagEntity> tags = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "DATA_LINK_DISH_INGREDIENT",
            joinColumns = @JoinColumn(name = "dish_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"))
    private Set<DataIngredientEntity> ingredients = new HashSet<>();

    @OneToMany(mappedBy = "child")
    private Set<DataDishRelationEntity> parentRelations = new HashSet<>();

    public DataDishEntity() {
        this(Type.NODE_GENERIC, UUID.randomUUID().toString(), "");
    }

    public DataDishEntity(Type type, String frLabel) {
        this(type, UUID.randomUUID().toString(), frLabel);
    }

    public DataDishEntity(Type type, String id, String frLabel) {
        this.type = type;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Set<DataTagEntity> getTags() {
        return tags;
    }

    public void setTags(Set<DataTagEntity> tags) {
        this.tags = tags;
    }

    public Set<DataIngredientEntity> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<DataIngredientEntity> ingredients) {
        this.ingredients = ingredients;
    }

    public Set<DataDishRelationEntity> getParentRelations() {
        return parentRelations;
    }

    public void setParentRelations(Set<DataDishRelationEntity> parent) {
        this.parentRelations = parent;
    }
}
