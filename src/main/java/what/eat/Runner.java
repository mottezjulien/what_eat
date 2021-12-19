package what.eat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import what.eat.menu.domain.model.Menu;
import what.eat.menu.domain.model.MenuSchedule;
import what.eat.menu.domain.model.MenuWeekException;
import what.eat.menu.infrastructure.adapter.MenuOutputAdapterSpring;
import what.eat.recipe.domain.model.RecipeDishState;
import what.eat.recipe.domain.model.RecipeDishType;
import what.eat.recipe.infrastructure.adapter.RecipeOutputAdapterSpring;
import what.eat.recipe.infrastructure.persistence.entity.RecipeDishEntity;
import what.eat.recipe.infrastructure.persistence.repository.RecipeDishRepository;
import what.eat.rule.domain.model.RuleComparator;
import what.eat.rule.domain.model.RulePriority;
import what.eat.rule.infrastructure.adapter.RuleOutputAdapterSpring;
import what.eat.rule.persistence.entity.RuleEngineEntity;
import what.eat.rule.persistence.entity.RuleEntity;
import what.eat.rule.persistence.repository.RuleEngineRepository;
import what.eat.rule.persistence.repository.RuleRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

@SpringBootApplication
public class Runner {

    public static void main(String[] args) {
        SpringApplication.run(Runner.class, args);
    }

    @Autowired
    private RecipeDishRepository dishRepository;

    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private RuleEngineRepository ruleEngineRepository;

    @Autowired
    private MenuOutputAdapterSpring weekOutput;

    @Autowired
    private RuleOutputAdapterSpring ruleOutput;

    @Autowired
    private RecipeOutputAdapterSpring dishOutput;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {

        Ports ports = new Ports(weekOutput, ruleOutput, dishOutput);
        Ports.setInstance(ports);

        dishRepository.deleteAll();
        ruleEngineRepository.deleteAll();
        ruleRepository.deleteAll();

        initDefaultDishes();


    }

    private void initDefaultDishes() {
        RecipeDishEntity saucisseLentilleCarotte = createFinalDoneDish("Saucisse lentille carotte");
        RecipeDishEntity saucisseLentille = createFinalDoneDish("Saucisse lentille", saucisseLentilleCarotte);
        createAbstractDoneDish("Legumineux", saucisseLentille);

        RecipeDishEntity spaghettiBolo = createFinalDoneDish("Spaghetti bolognaise");
        RecipeDishEntity pate = createAbstractDoneDish("Pate", spaghettiBolo);

        RecipeDishEntity rizPoulet = createFinalDoneDish("Riz Poulet");
        RecipeDishEntity riz = createAbstractDoneDish("Riz", rizPoulet);

        createAbstractDoneDish("Féculent", pate, riz);


        RecipeDishEntity boeuf = createAbstractDoneDish("Boeuf", spaghettiBolo);
        RecipeDishEntity chicken = createAbstractDoneDish("Poulet", rizPoulet);
        RecipeDishEntity porc = createAbstractDoneDish("Porc", saucisseLentille);
        RecipeDishEntity meat = createAbstractDoneDish("Viande", boeuf, chicken, porc);

        RecipeDishEntity carotte = createAbstractDoneDish("Legume", saucisseLentilleCarotte);
        createAbstractDoneDish("Legume", carotte);

        RuleEntity mustMinOneChickenRule = new RuleEntity();
        mustMinOneChickenRule.setId(UUID.randomUUID().toString());
        mustMinOneChickenRule.setComparator(RuleComparator.MIN);
        mustMinOneChickenRule.setPriority(RulePriority.MUST);
        mustMinOneChickenRule.setValue(1);
        mustMinOneChickenRule.setComparedTo(chicken);
        ruleRepository.save(mustMinOneChickenRule);

        RuleEntity shouldMaxThreeMeatsRule = new RuleEntity();
        shouldMaxThreeMeatsRule.setId(UUID.randomUUID().toString());
        shouldMaxThreeMeatsRule.setComparator(RuleComparator.MAX);
        shouldMaxThreeMeatsRule.setPriority(RulePriority.SHOULD);
        shouldMaxThreeMeatsRule.setValue(3);
        shouldMaxThreeMeatsRule.setComparedTo(meat);
        ruleRepository.save(shouldMaxThreeMeatsRule);

        RuleEngineEntity userRule = new RuleEngineEntity();
        userRule.setId(UUID.randomUUID().toString());
        userRule.getRules().add(shouldMaxThreeMeatsRule);
        userRule.getRules().add(mustMinOneChickenRule);
        ruleEngineRepository.save(userRule);


        MenuSchedule current = MenuSchedule.current();
        try {
            Menu today = current.of(LocalDate.now());
        } catch (MenuWeekException e) {
            e.printStackTrace();
        }

    }

    private RecipeDishEntity createAbstractDoneDish(String label, RecipeDishEntity... children) {
        return createDish(label, RecipeDishType.ABSTRACT, children);
    }

    private RecipeDishEntity createFinalDoneDish(String label, RecipeDishEntity... children) {
        return createDish(label, RecipeDishType.FINAL_SIMPLE, children);
    }

    private RecipeDishEntity createDish(String label, RecipeDishType type, RecipeDishEntity... children) {
        RecipeDishEntity dishEntity = new RecipeDishEntity();
        dishEntity.setId(UUID.randomUUID().toString());
        dishEntity.setLabel(label);
        dishEntity.setType(type);
        dishEntity.setState(RecipeDishState.DONE);
        dishEntity.getChildren().addAll(Arrays.asList(children.clone()));
        dishRepository.save(dishEntity);
        return dishEntity;
    }

}
