package what.eat.generic.brain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BrainQuery<Indicator> {

    public Item buildItemNoMore(Indicator indicator, int number) {
        return new Item(Type.ASK, indicator, number);
    }

    public enum Type {
        ASK, NO_MORE
    }

    public class Item {

        private final Type type;
        private final Indicator indicator;
        private final int number;

        public Item(Type type, Indicator indicator, int number) {
            this.type = type;
            this.indicator = indicator;
            this.number = number;
        }

        public Indicator indicator() {
            return indicator;
        }

        public Type type() {
            return type;
        }

        public int number() {
            return number;
        }

        public Item copy() {
            return new Item(type, indicator, number);
        }

        public Item reduce(List<Indicator> reducer) {
            if (reducer.contains(indicator) && number > 0) {
                return new Item(type, indicator, number - 1);
            }
            return copy();
        }
    }

    private List<Item> list = new ArrayList<>();

    public Stream<Item> stream() {
        return list.stream();
    }

    public BrainQuery<Indicator> copy() {
        BrainQuery<Indicator> copy = new BrainQuery<>();
        copy.list = stream().map(Item::copy).collect(Collectors.toList());
        return copy;
    }

    public BrainQuery<Indicator> reduce(List<Indicator> reducer) {
        BrainQuery<Indicator> reduce = new BrainQuery<>();
        reduce.list = stream().map(item -> item.reduce(reducer)).collect(Collectors.toList());
        return reduce;
    }

    public void addAsk(Indicator indic, int number) {
        add(Type.ASK, indic, number);
    }

    public void addNoMore(Indicator indic, int number) {
        add(Type.NO_MORE, indic, number);
    }

    public void add(Type type, Indicator indic, int number) {
        list.add(new Item(type, indic, number));
    }

}
