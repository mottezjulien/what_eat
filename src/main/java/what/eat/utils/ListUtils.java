package what.eat.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListUtils {

    public static <T> Optional<T> random(List<T> list) {
        if (!list.isEmpty()) {
            Random rand = new Random();
            return Optional.of(list.get(rand.nextInt(list.size())));
        }
        return Optional.empty();
    }

    public static <T> Optional<T> findOne(List<T> list, Predicate<T> predicate) {
        return list.stream().filter(predicate).findAny();
    }

    public static <T, U> List<U> map(Collection<T> from, Function<T, U> mapper) {
        return from.stream().map(mapper).collect(Collectors.toList());
    }

    public static <T> List<T> mutableCopyOf(List<T> from) {
        List<T> copy = new ArrayList<>();
        copy.addAll(from);
        return copy;
    }

    public static <T> List<T> concat(List<T> a, List<T> b) {
        return Stream.concat(a.stream(), b.stream())
                .collect(Collectors.toList());
    }
}
