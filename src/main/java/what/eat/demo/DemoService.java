package what.eat.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import what.eat.generic.brain.Brain;
import what.eat.generic.brain.BrainException;
import what.eat.generic.brain.BrainQuery;
import what.eat.generic.brain.BrainResult;
import what.eat.data.persistence.entity.DataTagEntity;
import what.eat.data.persistence.repository.DataTagRepository;
import what.eat.data.plop.DataDishBrainRepositoryImpl;
import what.eat.data.domain.model.DataId;

import java.util.stream.Stream;

@Service
public class DemoService {

    @Autowired
    private DataTagRepository tagRepository;

    @Autowired
    private DataDishBrainRepositoryImpl repository;


    public Stream<Demo> demo() throws BrainException {
        Brain<DataId, String> brain = new Brain<>(repository);
        BrainQuery<String> query = new BrainQuery<>();
        rules().forEach(rule ->
        {
            switch (rule.type()) {
                case AT_LEAST -> query.addAsk(rule.tagId(), rule.number());
                case MAX -> query.addNoMore(rule.tagId(), rule.number());
                case EQUALS -> {
                    query.addAsk(rule.tagId(), rule.number());
                    query.addNoMore(rule.tagId(), rule.number());
                }
            }
        });
        BrainResult<DataId> result = brain.generate(query);
        return result.stream().map(each -> new Demo(each.value(), each.frLabel()));
    }

    private Stream<DemoRule> rules() {
        //DataTagEntity vegeTag = tagRepository.finByFrLabel("Végétarien").orElseThrow(RuntimeException::new);
        //DataTagEntity pouletTag = tagRepository.finByFrLabel("Poulet").orElseThrow(RuntimeException::new);
        DataTagEntity feculentTag = tagRepository.finByFrLabel("Féculent").orElseThrow(RuntimeException::new);
        //DemoRule vegeRule = new DemoRule(DemoRule.Type.AT_LEAST, vegeTag.getId(), 3, "Au moins: 3 fois d'un plat Végétarien");
        //DemoRule pouletRule = new DemoRule(DemoRule.Type.EQUALS, pouletTag.getId(), 2, "Egale: 1 fois d'un plat Poulet");
        DemoRule feculentRule = new DemoRule(DemoRule.Type.MAX, feculentTag.getId(), 0, "Max: 1 fois féculent");
        return Stream.of(feculentRule);
    }
}
