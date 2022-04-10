package what.eat.data.plop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import what.eat.generic.brain.BrainRepository;
import what.eat.data.domain.model.DataDish;
import what.eat.data.domain.model.DataId;
import what.eat.data.persistence.repository.DataDishCacheRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class DataDishBrainRepositoryImpl implements BrainRepository<DataId, String> {

    @Autowired
    private DataDishCacheRepository cacheRepository;

    @Override
    public List<Result<DataId, String>> findAll(Query<String> query) {
        //TODO c'est plus compliqué que cela (pas que Dish READY, penser Dish COMPONENT ...)

        DataDishQuery dataDishQuery = new DataDishQuery();
        dataDishQuery.add(DataDishQuery.Field.TAG_ID, DataDishQuery.Operation.DONT_CONTAINS, query.wont());

        Stream<DataDish> dishes = cacheRepository.find(dataDishQuery);

        List<Result<DataId, String>> results = dishes.map(each -> {
            List<String> indicator = each.tags()
                    .map(tag -> tag.id().value()) //TODO
                    .collect(Collectors.toList());
            return new Result<>(each.id(), indicator);
        }).collect(Collectors.toList());

        //TODO Filter: Here ???
        List<Result<DataId, String>> filterResults = results.stream()
                .filter(result -> result.indicators().stream().anyMatch(indicator -> query.must().contains(indicator)))
                .collect(Collectors.toList());
        if (!filterResults.isEmpty()) {
            return filterResults;
        }
        return results;
    }

    @Override
    public Optional<DataId> findAny() {
        return cacheRepository.findAny()
                .map(DataDish::id);
    }

}
