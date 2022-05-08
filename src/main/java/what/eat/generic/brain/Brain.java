package what.eat.generic.brain;

import what.eat.utils.ListUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Brain<Obj, Indicator> {



    private final BrainRepository<Obj, Indicator> repository;

    public Brain(BrainRepository<Obj, Indicator> repository) {
        this.repository = repository;
    }

    public BrainResult<Obj> generate(BrainQuery<Indicator> query) throws BrainException {
        BrainQuery<Indicator> current = query.copy();
        BrainResult<Obj> result = new BrainResult<>();
        while(!result.isComplete(current.option())) {
            List<BrainRepository.Result<Obj, Indicator>> found = repository.findOnly(current.limit());
            if(found.isEmpty()) {
                result.add(repository.findAny().orElseThrow(BrainException::new));
            } else {
                BrainRepository.Result<Obj, Indicator> selected = select(found, result, current);
                current = current.reduce(selected.indicators());
                result.add(selected.value());
            }
        }
        return result;
    }





    private BrainRepository.Result<Obj, Indicator> select(List<BrainRepository.Result<Obj, Indicator>> found, BrainResult<Obj> currentResult, BrainQuery<Indicator> query) throws BrainException {
        Optional<BrainRepository.Result<Obj, Indicator>> random = ListUtils.random(filter(found, currentResult, query));
        if(random.isEmpty()){
            random = ListUtils.random(found);
        }
        return random.orElseThrow(BrainException::new);
    }

    private List<BrainRepository.Result<Obj,Indicator>> filter(List<BrainRepository.Result<Obj, Indicator>> found, BrainResult<Obj> currentResult, BrainQuery<Indicator> query) {
        List<Indicator> biggestAsk = query.biggestAsk();
        return found.stream()
                .filter(each -> query.option().isRepeat() || !currentResult.contains(each.value()))
                .filter(each ->  !isComplicated(query, currentResult) || each.indicators().stream().anyMatch(biggestAsk::contains))
                //.filter(each -> each.indicators().stream().anyMatch(indicator -> query.ask(indicator)))
                .collect(Collectors.toList());
    }

    private boolean isComplicated(BrainQuery<Indicator> query, BrainResult<Obj> currentResult) {
        return currentResult.missing(query.option()) <= query.nbAsk();
    }

}
