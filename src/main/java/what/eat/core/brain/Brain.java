package what.eat.core.brain;

import what.eat.utils.ListUtils;

import java.util.List;
import java.util.stream.Collectors;

public class Brain<Obj, Indicator> {

    /*public static class Option {
        Max, Repeat
    }*/

    private final BrainRepository<Obj, Indicator> repository;

    public Brain(BrainRepository<Obj, Indicator> repository) {
        this.repository = repository;
    }

    public BrainResult<Obj> generate(BrainQuery<Indicator> query/*, Option option*/) throws BrainException {
        BrainQuery<Indicator> current = query.copy();
        BrainResult<Obj> result = new BrainResult<>();
        while(!result.isComplete()) {
            List<BrainRepository.Result<Obj, Indicator>> found = repository.findAll(toRepository(current));
            if(found.isEmpty()) {
                result.add(repository.findAny().orElseThrow(BrainException::new));
            } else {
                BrainRepository.Result<Obj, Indicator> selected = ListUtils.random(found).orElseThrow(BrainException::new);
                current = current.reduce(selected.indicators());
                result.add(selected.value());
            }
        }
        return result;
    }

    private BrainRepository.Query<Indicator> toRepository(BrainQuery<Indicator> query) {
        List<Indicator> must = query.stream()
                .filter(item -> item.type() == BrainQuery.Type.ASK)
                .filter(item -> item.number() > 0)
                .map(item -> item.indicator())
                .distinct()
                .collect(Collectors.toList());
        List<Indicator> wont = query.stream()
                .filter(item -> item.type() == BrainQuery.Type.NO_MORE)
                .filter(item -> item.number() == 0)
                .map(item -> item.indicator())
                .distinct()
                .collect(Collectors.toList());
        return new BrainRepository.Query<>(must, wont);
    }

}
