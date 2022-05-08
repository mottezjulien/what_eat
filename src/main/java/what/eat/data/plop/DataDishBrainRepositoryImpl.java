package what.eat.data.plop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import what.eat.data.domain.model.DataTag;
import what.eat.generic.brain.BrainRepository;
import what.eat.data.domain.model.DataDish;
import what.eat.data.domain.model.DataId;
import what.eat.data.persistence.repository.DataDishCacheRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class DataDishBrainRepositoryImpl implements BrainRepository<DataId, DataTag> {

    @Autowired
    private DataDishCacheRepository cacheRepository;

    @Override
    public List<Result<DataId, DataTag>> findOnly(List<DataTag> wont) {

        DataDishQuery dataDishQuery = new DataDishQuery();
        dataDishQuery.add(DataDishQuery.Field.TAG_ID, DataDishQuery.Operation.DONT_CONTAINS, wont.stream().map(tag -> tag.id().value()).collect(Collectors.toList()));

        return cacheRepository.find(dataDishQuery).map(each -> new Result<>(each.id(), each.tags().collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DataId> findAny() {
        return cacheRepository.findAny()
                .map(DataDish::id);
    }

}
