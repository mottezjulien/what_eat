package what.eat.data.domain.model;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class DataTag  {

    private final DataId id;

    private final Optional<DataTag> optParent;

    public DataTag(String id, String frLabel) {
        this(new DataId(id, frLabel));
    }

    public DataTag(DataId id) {
        this.id = id;
        this.optParent = Optional.empty();
    }

    public DataTag(DataId id, DataId parentId) {
        this.id = id;
        this.optParent = Optional.of(new DataTag(parentId));
    }

    public DataId id() {
        return id;
    }

    public Stream<DataTag> _extends() {
        return optParent.map(parent -> Stream.of(this, parent))
                .orElseGet(() -> Stream.of(this));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataTag tag = (DataTag) o;
        return Objects.equals(id, tag.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public DataTag withParent(DataId parentId) {
        return new DataTag(id, parentId);
    }


}
