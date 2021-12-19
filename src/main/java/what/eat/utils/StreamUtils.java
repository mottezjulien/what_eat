package what.eat.utils;

import java.util.function.Predicate;
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

}
