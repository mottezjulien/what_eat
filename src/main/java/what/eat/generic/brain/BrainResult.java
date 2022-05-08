package what.eat.generic.brain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class BrainResult<Obj> implements Iterable<Obj> {

    private List<Obj> list = new ArrayList<>();

    public void add(Obj obj) {
        list.add(obj);
    }

    public boolean isComplete(BrainOption option) {
        return missing(option) == 0;
    }

    public int missing(BrainOption option) {
        return Integer.max(option.getMax() - list.size(), 0);
    }

    @Override
    public Iterator<Obj> iterator() {
        return list.iterator();
    }

    public Stream<Obj> stream() {
        return list.stream();
    }

    public boolean contains(Obj value) {
        return list.contains(value);
    }
}
