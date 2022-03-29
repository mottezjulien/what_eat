package what.eat.core.brain;

import java.util.Objects;

public class StringDataIndicator {

    private String comparator;

    public StringDataIndicator(String comparator) {
        this.comparator = comparator;
    }

    public String comparator() {
        return comparator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringDataIndicator that = (StringDataIndicator) o;
        return Objects.equals(comparator, that.comparator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comparator);
    }
}
