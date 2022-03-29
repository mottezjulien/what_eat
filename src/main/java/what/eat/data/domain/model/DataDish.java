package what.eat.data.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class DataDish {

    private final DataDishAtom atom;
    private final List<DataDishRelation> relations = new ArrayList<>();

    public DataDish(DataDishAtom atom, List<DataDishRelation> relations) {
        this.atom = atom;
        this.relations.addAll(relations);
    }

    public void add(DataDishRelation relation) {
        this.relations.add(relation);
    }

    public DataId id() {
        return atom.id();
    }

    public Stream<DataTag> tags() {
        Stream<DataTag> atomTags = atom.extendedTags();
        Stream<DataTag> relationTags = relations.stream().flatMap(relation -> relation.with().extendedTags());
        Stream<DataTag> ingredientTags = atom.ingredients().flatMap(ingredient -> ingredient.extendedTags());
        Stream<DataTag> relationIngredientTags = relations.stream()
                .flatMap(relation -> relation.with().ingredients())
                .flatMap(ingredient -> ingredient.extendedTags());
        return Stream.concat(Stream.concat(Stream.concat(atomTags, relationTags), relationIngredientTags), ingredientTags).distinct();
    }

    public Stream<DataDishRelation> relations() {
        return this.relations.stream();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataDish dataDish = (DataDish) o;
        return Objects.equals(atom, dataDish.atom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(atom);
    }
}
