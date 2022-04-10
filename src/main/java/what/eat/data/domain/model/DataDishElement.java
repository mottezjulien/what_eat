package what.eat.data.domain.model;

import java.util.List;
import java.util.Objects;

public class DataDishElement {

    public enum Type {
        DISH_READY, DISH_TEMPLATE, NODE_ITEM, NODE_GENERIC, UNKNOWN;
    }

    private final Type type;
    private final DataDishAtom atom;

    public DataDishElement(Type type, DataDishAtom atom) {
        this.type = type;
        this.atom = atom;
    }

    public Type type() {
        return type;
    }

    public DataDishAtom atom() {
        return atom;
    }

    public DataId id() {
        return atom.id();
    }

    public DataDishElement updateIngredients(List<DataIngredient> ingredients) {
        return new DataDishElement(type, atom.updateIngredients(ingredients));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataDishElement that = (DataDishElement) o;
        return Objects.equals(atom, that.atom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(atom);
    }
}
