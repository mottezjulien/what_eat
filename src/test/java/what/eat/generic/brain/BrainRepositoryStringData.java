package what.eat.generic.brain;

import what.eat.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BrainRepositoryStringData implements BrainRepository<StringData, StringDataIndicator> {

    private final List<StringData> list = new ArrayList<>();

    public BrainRepositoryStringData() {
        list.add(new StringData("AAA_001"));
        list.add(new StringData("AAA_002"));
        list.add(new StringData("AAA_003"));

        list.add(new StringData("BBB_001"));
        list.add(new StringData("BBB_002"));

        list.add(new StringData("CCC_001"));
    }

    @Override
    public List<Result<StringData, StringDataIndicator>> findOnly(List<StringDataIndicator> wont) {
        return list
                .stream()
                .filter(data -> isWillNot(data, wont))
                .map(data -> new Result<>(data, List.of(new StringDataIndicator(data.value().substring(0,3)))))
                .collect(Collectors.toList());
    }

    private List<StringDataIndicator> match(List<StringDataIndicator> indicators, StringData data) {
        return indicators
                .stream()
                .filter(filter(data))
                .collect(Collectors.toList());
    }

    private boolean isWillNot(StringData data, List<StringDataIndicator> wont) {
        return wont.stream().noneMatch(filter(data));
    }

    private Predicate<StringDataIndicator> filter(StringData data) {
        return indicator -> data.value().startsWith(indicator.comparator());
    }

    @Override
    public Optional<StringData> findAny() {
        return ListUtils.random(list);
    }

    public List<StringData> backDoor() {
        return list;
    }
}
