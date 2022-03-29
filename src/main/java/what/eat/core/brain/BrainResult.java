package what.eat.core.brain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class BrainResult<Obj> implements Iterable<Obj> {

    private List<Obj> list = new ArrayList<>();

    public boolean isComplete() {
        int max = 5;
        return list.size() >= max;
    }

    public void add(Obj obj) {
        list.add(obj);
    }

    @Override
    public Iterator<Obj> iterator() {
        return list.iterator();
    }

    public Stream<Obj> stream() {
        return list.stream();
    }
}
