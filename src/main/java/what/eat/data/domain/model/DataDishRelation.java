package what.eat.data.domain.model;

public class DataDishRelation {

    private final DataDishRelationType type;
    private final DataDishAtom with;

    public DataDishRelation(DataDishRelationType type, DataDishAtom with) {
        this.type = type;
        this.with = with;
    }

    public DataDishRelationType type() {
        return type;
    }

    public DataDishAtom with() {
        return with;
    }

}
