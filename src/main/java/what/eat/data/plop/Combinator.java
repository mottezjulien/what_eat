package what.eat.data.plop;

import what.eat.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Combinator<Elt> {

    private class Item {
        private List<Elt> elements = new ArrayList<>();
    }

    private final List<Item> items = new ArrayList<>();

    public void add(List<Elt> elements) {
        Item item = new Item();
        item.elements = elements;
        this.items.add(item);
    }

    public List<List<Elt>> combine() {
        if(items.stream().anyMatch(item -> item.elements.isEmpty())) {
            return List.of();
        }
        List<List<Elt>> result = new ArrayList<>();
        for (int index = 0; index < items.size(); index++) {
            Item current = items.get(index);
            if (index == 0) {
                result.addAll(listOf(current));
            } else {
                result = combineOne(result, current);
            }
        }
        return result;
    }

    private List<List<Elt>> listOf(Item item) {
        return item.elements.stream().map(List::of)
                .collect(Collectors.toList());
    }

    private List<List<Elt>> combineOne(List<List<Elt>> previous, Item item) {
        return item.elements.stream().map(elt -> {
                    List<List<Elt>> oneCombine = new ArrayList<>();
                    previous.forEach(each -> oneCombine.add(ListUtils.mutableCopyOf(each)));
                    oneCombine.forEach(lst -> lst.add(elt));
                    return oneCombine;
                })
                .reduce(new ArrayList<>(), ListUtils::concat);
    }
}
