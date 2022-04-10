package what.eat.generic.brain;

import java.util.List;
import java.util.Optional;

public interface BrainRepository<Obj, Indicator> {

    List<Result<Obj, Indicator>> findAll(Query<Indicator> query);

    Optional<Obj> findAny();

    class Query<Indicator> {

        private List<Indicator> must;

        private List<Indicator> wont;

        public Query(List<Indicator> must, List<Indicator> wont) {
            this.must = must;
            this.wont = wont;
        }

        public List<Indicator> must() {
            return must;
        }

        public List<Indicator> wont() {
            return wont;
        }
    }

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
