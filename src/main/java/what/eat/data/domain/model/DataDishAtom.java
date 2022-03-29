package what.eat.data.domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class DataDishAtom {

    private final DataId id;
    private final List<DataTag> tags = new ArrayList<>();
    private final List<DataIngredient> ingredients = new ArrayList<>();

    public DataDishAtom(DataId id) {
        this.id = id;
    }

    public DataDishAtom(String id, String frLabel) {
        this.id = new DataId(id, frLabel);
    }

    public void addTag(DataTag tag) {
        this.tags.add(tag);
    }

    public void addAllTags(Collection<DataTag> tags) {
        this.tags.addAll(tags);
    }

    public void addIngredient(DataIngredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public void addAllIngredients(Collection<DataIngredient> ingredients) {
        this.ingredients.addAll(ingredients);
    }

    public DataId id() {
        return id;
    }

    public Stream<DataTag> tags() {
        return tags.stream();
    }

    public Stream<DataTag> extendedTags() {
        return tags.stream().flatMap(DataTag::_extends);
    }

    public Stream<DataIngredient> ingredients() {
        return ingredients.stream();
    }

    public DataDishAtom updateIngredients(List<DataIngredient> ingredients) {
        final DataDishAtom dataDishAtom = new DataDishAtom(id);
        dataDishAtom.addAllTags(this.tags);
        dataDishAtom.addAllIngredients(ingredients);
        return dataDishAtom;
    }

    public DataDishAtom rename(String frLabel) {
        final DataDishAtom dataDishAtom = new DataDishAtom(id.rename(frLabel));
        dataDishAtom.addAllTags(this.tags);
        dataDishAtom.addAllIngredients(this.ingredients);
        return dataDishAtom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataDishAtom dataDish = (DataDishAtom) o;
        return Objects.equals(id, dataDish.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
