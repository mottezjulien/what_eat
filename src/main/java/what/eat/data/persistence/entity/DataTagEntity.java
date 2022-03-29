package what.eat.data.persistence.entity;

import what.eat.data.domain.model.DataId;
import what.eat.data.domain.model.DataTag;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "DATA_TAG")
public class DataTagEntity {

    public DataTag toModel() {
        DataTag tag = new DataTag(getId(), getFrLabel());
        if (getParent() != null) {
            tag = tag.withParent(new DataId(getParent().getId(), getParent().getFrLabel()));
        }
        return tag;
    }

    @Id
    private String id;

    @Column(name = "fr_label")
    private String frLabel;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private DataTagEntity parent;

    @OneToMany(mappedBy = "parent")
    private Set<DataTagEntity> children = new HashSet<>();

    public DataTagEntity() {
        this(UUID.randomUUID().toString(), "");
    }

    public DataTagEntity(String frLabel) {
        this(UUID.randomUUID().toString(), frLabel);
    }

    public DataTagEntity(String id, String frLabel) {
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

    public DataTagEntity getParent() {
        return parent;
    }

    public void setParent(DataTagEntity parent) {
        this.parent = parent;
    }

    public Set<DataTagEntity> getChildren() {
        return children;
    }

    public void setChildren(Set<DataTagEntity> children) {
        this.children = children;
    }

}
