package what.eat.data.domain.model;

import java.util.Objects;

public class DataId {

    private final String value;
    private final String frLabel;

    public DataId(String value, String frLabel) {
        this.value = value;
        this.frLabel = frLabel;
    }

    public String value() {
        return value;
    }

    public String frLabel() {
        return frLabel;
    }

    public DataId rename(String frLabel) {
        return new DataId(this.value, frLabel);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataId dataId = (DataId) o;
        return Objects.equals(value, dataId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
