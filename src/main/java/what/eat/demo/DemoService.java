package what.eat.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import what.eat.data.domain.model.DataId;
import what.eat.data.domain.model.DataTag;
import what.eat.data.persistence.entity.DataTagEntity;
import what.eat.data.persistence.repository.DataTagRepository;
import what.eat.data.plop.DataDishBrainRepositoryImpl;
import what.eat.generic.brain.Brain;
import what.eat.generic.brain.BrainException;
import what.eat.generic.brain.BrainQuery;
import what.eat.generic.brain.BrainResult;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@Scope("singleton")
public class DemoService {

    @Autowired
    private DataTagRepository tagRepository;

    @Autowired
    private DataDishBrainRepositoryImpl repository;

    private List<DemoRuleGroup> groups = new ArrayList<>();

    public Stream<DemoDish> dishes(String groupId) throws BrainException, DemoException {
        Brain<DataId, DataTag> brain = new Brain<>(repository);

        DemoRuleGroup found = ruleGroups()
                .filter(eachGroup -> eachGroup.getId().equals(groupId))
                .findAny()
                .orElseThrow(() -> new DemoException("group not found with id " + groupId));

        BrainResult<DataId> result = brain.generate(found.toQuery());
        return result.stream().map(each -> new DemoDish(each.value(), each.frLabel()));
    }

    public Stream<DemoRuleGroup> ruleGroups() {
        initRuleGroups();
        return groups.stream();
    }

    private void initRuleGroups() {
        if (groups.isEmpty()) {
            DataTagEntity vegeTag = tagRepository.finByFrLabel("Végétarien").orElseThrow(RuntimeException::new);

            DataTagEntity feculentTag = tagRepository.finByFrLabel("Féculent").orElseThrow(RuntimeException::new);
            DataTagEntity pastaTag = tagRepository.finByFrLabel("Pâte").orElseThrow(RuntimeException::new);
            DataTagEntity streetFoodTag = tagRepository.finByFrLabel("Street-food").orElseThrow(RuntimeException::new);

            DataTagEntity viandeTag = tagRepository.finByFrLabel("Viande").orElseThrow(RuntimeException::new);
            DataTagEntity familialTag = tagRepository.finByFrLabel("Familial").orElseThrow(RuntimeException::new);

            DemoRuleGroup vegeGroup = new DemoRuleGroup();
            vegeGroup.setId(UUID.randomUUID().toString());
            vegeGroup.setLabel("Plutôt Végétarien");
            vegeGroup.setDesc("Au moins 2 plats végétarien");
            vegeGroup.getRules().add(new DemoRule(DemoRule.Type.AT_LEAST, vegeTag.toModel(), 2, "Au moins: 2 fois un plat de type Végétarien"));
            groups.add(vegeGroup);

            DemoRuleGroup studientGroup = new DemoRuleGroup();
            studientGroup.setId(UUID.randomUUID().toString());
            studientGroup.setLabel("Etudiant");
            studientGroup.setDesc("Au moins 2 plats féculents, dont au moins un plat de pate. 1 plat street-food");
            studientGroup.getRules().add(new DemoRule(DemoRule.Type.AT_LEAST, feculentTag.toModel(), 2, "Au moins: 2 fois un plat de type Féculent"));
            studientGroup.getRules().add(new DemoRule(DemoRule.Type.AT_LEAST, pastaTag.toModel(), 1, "Au moins: 1 fois un plat de type Pâte"));
            studientGroup.getRules().add(new DemoRule(DemoRule.Type.EQUALS, streetFoodTag.toModel(), 1, "Egals: 1 fois un plat de type Street-Food"));
            groups.add(studientGroup);

            DemoRuleGroup familyGroup = new DemoRuleGroup();
            familyGroup.setId(UUID.randomUUID().toString());
            familyGroup.setLabel("Famille");
            familyGroup.setDesc("Max 3 plats de viande, 2 plats familiaux");
            familyGroup.getRules().add(new DemoRule(DemoRule.Type.MAX, viandeTag.toModel(), 3, "Max: 3 fois un plat de type Viande"));
            familyGroup.getRules().add(new DemoRule(DemoRule.Type.EQUALS, familialTag.toModel(), 2, "Equals: 2 fois un plat de type Viande"));
            groups.add(familyGroup);
        }
    }


}
