package what.eat.generic.brain;

import java.util.List;
import java.util.Optional;

public interface BrainRepository<Obj, Indicator> {

    List<Result<Obj, Indicator>> findOnly(List<Indicator> wont);

    Optional<Obj> findAny();

    class Result<Obj, Indicator> {

        private final Obj value;

        private final List<Indicator> indicators;

        public Result(Obj value, List<Indicator> indicators) {
            this.value = value;
            this.indicators = indicators;
        }

        public Obj value() {
            return value;
        }

        public List<Indicator> indicators() {
            return indicators;
        }

    }

}
