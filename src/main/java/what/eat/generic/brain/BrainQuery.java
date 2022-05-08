package what.eat.generic.brain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BrainQuery<Indicator> {


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
    private BrainOption option = new BrainOption();

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public BrainOption option() {
        return option;
    }

    public BrainQuery<Indicator> copy() {
        BrainQuery<Indicator> copy = new BrainQuery<>();
        copy.list = stream().map(Item::copy).collect(Collectors.toList());
        copy.option = this.option;
        return copy;
    }

    public BrainQuery<Indicator> reduce(List<Indicator> reducer) {
        BrainQuery<Indicator> reduce = new BrainQuery<>();
        reduce.list = stream().map(item -> item.reduce(reducer)).collect(Collectors.toList());
        return reduce;
    }

    public List<Indicator> limit() {
        return stream()
                .filter(item -> item.type() == BrainQuery.Type.NO_MORE)
                .filter(item -> item.number() == 0)
                .map(Item::indicator)
                .distinct()
                .collect(Collectors.toList());
    }

    public int nbAsk() {
        return ask().values()
                .stream()
                .reduce(0, Integer::sum);
    }

    public List<Indicator> biggestAsk() {
        Map<Indicator, Integer> ask = ask();
        if(ask.isEmpty()){
            return List.of();
        }
        if(ask.size() == 1){
            return new ArrayList<>(ask.keySet());
        }
        Integer max = Collections.max(ask.values());
        return ask.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), max))
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
    }

    private Map<Indicator, Integer> ask() {
        return stream()
                .filter(item -> item.type() == Type.ASK)
                .filter(item -> item.number() > 0)
                .collect(Collectors.toMap(Item::indicator, Item::number));
    }

    private Stream<Item> stream() {
        return list.stream();
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
