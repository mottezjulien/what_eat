package what.eat.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.stream.Stream;

public class LocalDateUtils {

    private LocalDateUtils() {
    }

    public static Stream<LocalDate> stream(LocalDate begin, LocalDate end) {
        return Stream
                .iterate(begin, d -> d.plusDays(1))
                .limit(ChronoUnit.DAYS.between(begin, end) + 1);
    }



    /*
    //TODO

    public static LocalDate same0rPrevious(LocalDate date, DayOfWeek dayOfWeek) {
        if(date.getDayOfWeek() == dayOfWeek) {
            return date;
        }
        return date.with(TemporalAdjusters.previous(dayOfWeek));
    }

    public static LocalDate sameOrNext(LocalDate date, DayOfWeek dayOfWeek) {
        if(date.getDayOfWeek() == dayOfWeek) {
            return date;
        }
        return date.with(TemporalAdjusters.next(dayOfWeek));
    }


    private static LocalDate first(LocalDate date) {
        DayOfWeek startWith = DayOfWeek.MONDAY;
        if(date.getDayOfWeek() == startWith) {
            return date;
        }
        return date.with(TemporalAdjusters.previous(startWith));
    }*/

}
