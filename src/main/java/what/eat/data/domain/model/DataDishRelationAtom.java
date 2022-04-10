package what.eat.data.domain.model;

public class DataDishRelationAtom {

    private final DataDishRelationType type;
    private final DataId with;

    public DataDishRelationAtom(DataDishRelationType type, DataId with) {
        this.type = type;
        this.with = with;
    }

    public DataDishRelationType type() {
        return type;
    }

    public DataId with() {
        return with;
    }

}
