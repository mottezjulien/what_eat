package what.eat.data.persistence.entity;

import what.eat.data.domain.model.DataDishRelationType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "data_dish_relation_entity")
@Entity
public class DataDishRelationEntity {

    public enum Type {
        SIDE, MEAT, FULL;

        public DataDishRelationType toModel() {
            return DataDishRelationType.valueOf(this.name());
        }
    }

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne
    @JoinColumn(name = "data_id_child")
    private DataDishEntity child;

    @ManyToOne
    @JoinColumn(name = "data_id_parent")
    private DataDishEntity parent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public DataDishEntity getChild() {
        return child;
    }

    public void setChild(DataDishEntity child) {
        this.child = child;
    }

    public DataDishEntity getParent() {
        return parent;
    }

    public void setParent(DataDishEntity parent) {
        this.parent = parent;
    }
}