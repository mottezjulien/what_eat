package what.eat.utils;

import what.eat.recipe.infrastructure.persistence.entity.RecipeDishEntity;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamUtils {

    public static <T> Integer count(Stream<T> stream, Predicate<T> consumer) {
        return stream.reduce(0, (current, each) -> {
            if (consumer.test(each)) {
                return current + 1;
            }
            return current;
        }, Integer::sum);
    }

    public static <T> Optional<T> findAnyRandom(Stream<T> stream) {
        return ListUtils.getRandom(stream.collect(Collectors.toList()));
    }

}
