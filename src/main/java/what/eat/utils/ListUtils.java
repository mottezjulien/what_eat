package what.eat.utils;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListUtils {

    public static <T> Optional<T> getRandom(List<T> list) {
        if(!list.isEmpty()) {
            Random rand = new Random();
            return Optional.of(list.get(rand.nextInt(list.size())));
        }
        return Optional.empty();
    }

}
