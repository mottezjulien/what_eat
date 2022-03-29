package what.eat.data.domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class DataIngredient {

    private final DataId id;
    private final List<DataTag> tags = new ArrayList<>();

    public DataIngredient(String id, String frLabel) {
        this.id = new DataId(id, frLabel);
    }

    public void addAll(Collection<DataTag> tags) {
        this.tags.addAll(tags);
    }

    public Stream<DataTag> extendedTags() {
        return tags.stream().flatMap(tag -> tag._extends());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataIngredient that = (DataIngredient) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
