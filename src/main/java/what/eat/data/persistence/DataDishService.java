package what.eat.data.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import what.eat.data.persistence.entity.DataDishEntity;
import what.eat.data.persistence.entity.DataDishRelationEntity;
import what.eat.data.persistence.entity.DataIngredientEntity;
import what.eat.data.persistence.entity.DataTagEntity;
import what.eat.data.persistence.repository.DataDishCacheRepository;
import what.eat.data.persistence.repository.DataDishJpaRepository;
import what.eat.data.persistence.repository.DataDishRelationRepository;
import what.eat.data.persistence.repository.DataIngredientRepository;
import what.eat.data.persistence.repository.DataTagRepository;

import java.util.UUID;

@Service
public class DataDishService {

    @Autowired
    private DataDishJpaRepository dishRepository;

    @Autowired
    private DataTagRepository tagRepository;

    @Autowired
    private DataIngredientRepository ingredientRepository;

    @Autowired
    private DataDishRelationRepository dishRelationRepository;

    @Autowired
    private DataDishCacheRepository cacheRepository;

    public void init() {
        if (dishRepository.count() == 0) {
            fill();
        }
        cacheRepository.reload();
    }

    private void fill() {
        DataTagEntity veggyTag = new DataTagEntity("Végétarien");
        tagRepository.save(veggyTag);

        DataTagEntity familyTag = new DataTagEntity("Familial");
        tagRepository.save(familyTag);

        DataTagEntity feculentTag = new DataTagEntity("Féculent");
        tagRepository.save(feculentTag);

        DataTagEntity pateTag = new DataTagEntity("Pate");
        pateTag.setParent(feculentTag);
        tagRepository.save(pateTag);

        DataTagEntity laitageTag = new DataTagEntity("Laitage");
        tagRepository.save(laitageTag);

        DataTagEntity viandeTag = new DataTagEntity("Viande");
        tagRepository.save(viandeTag);

        DataTagEntity porcTag = new DataTagEntity("Porc");
        porcTag.setParent(viandeTag);
        tagRepository.save(porcTag);

        DataTagEntity boeufTag = new DataTagEntity("Boeuf");
        boeufTag.setParent(viandeTag);
        tagRepository.save(boeufTag);

        DataTagEntity chickenTag = new DataTagEntity("Poulet");
        chickenTag.setParent(viandeTag);
        tagRepository.save(chickenTag);

        DataTagEntity legumineuxTag = new DataTagEntity("Légumineux");
        tagRepository.save(legumineuxTag);

        DataTagEntity legumeTag = new DataTagEntity("Légume");
        tagRepository.save(legumeTag);

        DataIngredientEntity saucisseIngredient = new DataIngredientEntity("Saucisse");
        saucisseIngredient.getTags().add(porcTag);
        ingredientRepository.save(saucisseIngredient);

        DataIngredientEntity potatoIngredient = new DataIngredientEntity("Pomme de terre");
        potatoIngredient.getTags().add(feculentTag);
        ingredientRepository.save(potatoIngredient);

        DataIngredientEntity tagliatelleIngredient = new DataIngredientEntity("Tagliatelle");
        tagliatelleIngredient.getTags().add(pateTag);
        ingredientRepository.save(tagliatelleIngredient);

        DataIngredientEntity cremeIngredient = new DataIngredientEntity("Crême");
        cremeIngredient.getTags().add(laitageTag);
        ingredientRepository.save(cremeIngredient);

        DataIngredientEntity lardonIngredient = new DataIngredientEntity("Lardon");
        lardonIngredient.getTags().add(porcTag);
        ingredientRepository.save(lardonIngredient);

        DataIngredientEntity spagnettiIngredient = new DataIngredientEntity("Spagnetti");
        spagnettiIngredient.getTags().add(pateTag);
        ingredientRepository.save(spagnettiIngredient);

        DataIngredientEntity steakIngredient = new DataIngredientEntity("Steak");
        steakIngredient.getTags().add(boeufTag);
        ingredientRepository.save(steakIngredient);

        DataIngredientEntity champignonIngredient = new DataIngredientEntity("Champignon");
        ingredientRepository.save(champignonIngredient);

        DataIngredientEntity chouFleurIngredient = new DataIngredientEntity("Choux-fleur");
        chouFleurIngredient.getTags().add(legumeTag);
        ingredientRepository.save(chouFleurIngredient);

        DataIngredientEntity bechamelIngredient = new DataIngredientEntity("Béchamel");
        ingredientRepository.save(bechamelIngredient);

        DataIngredientEntity fromageIngredient = new DataIngredientEntity("Fromage");
        fromageIngredient.getTags().add(laitageTag);
        ingredientRepository.save(fromageIngredient);

        DataDishEntity puree = new DataDishEntity(DataDishEntity.Type.NODE_ITEM, "Purée");
        puree.getIngredients().add(potatoIngredient);
        dishRepository.save(puree);

        DataDishEntity lentille = new DataDishEntity(DataDishEntity.Type.NODE_ITEM, "Accompagnement de lentilles");
        lentille.getTags().add(legumineuxTag);
        dishRepository.save(lentille);

        DataDishEntity carotte = new DataDishEntity(DataDishEntity.Type.NODE_ITEM, "Accompagnement de carottes");
        carotte.getTags().add(legumeTag);
        dishRepository.save(carotte);

        DataDishEntity saucisse = new DataDishEntity(DataDishEntity.Type.NODE_ITEM, "Plat à base de Saucisse");
        saucisse.getTags().add(porcTag);
        dishRepository.save(saucisse);

        DataDishEntity saucissePuree = new DataDishEntity(DataDishEntity.Type.DISH_READY, "Saucisse purée");
        dishRepository.save(saucissePuree);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.MEAT, saucisse, saucissePuree));
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.SIDE, puree, saucissePuree));


        DataDishEntity saucisseLentille = new DataDishEntity(DataDishEntity.Type.DISH_READY, "Saucisse lentille");
        dishRepository.save(saucisseLentille);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.MEAT, saucisse, saucisseLentille));
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.SIDE, lentille, saucisseLentille));


        //TODO add VARIANT de Saucisse lentille (add Carotte dans l'item Side) ???
        DataDishEntity saucisseCarotteLentille = new DataDishEntity(DataDishEntity.Type.DISH_READY, "Saucisse lentille carotte");
        dishRepository.save(saucisseCarotteLentille);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.MEAT, saucisse, saucisseCarotteLentille));
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.SIDE, lentille, saucisseCarotteLentille));
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.SIDE, carotte, saucisseCarotteLentille));

        DataDishEntity pate = new DataDishEntity(DataDishEntity.Type.NODE_ITEM, "Plat à base de pate");
        pate.getTags().add(pateTag);
        dishRepository.save(pate);

        DataDishEntity tagliatelle_carbo = new DataDishEntity(DataDishEntity.Type.DISH_READY, "Tagliatelle Carbonara");
        tagliatelle_carbo.getIngredients().add(tagliatelleIngredient);
        tagliatelle_carbo.getIngredients().add(cremeIngredient);
        tagliatelle_carbo.getIngredients().add(lardonIngredient);
        dishRepository.save(tagliatelle_carbo);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.FULL, pate, tagliatelle_carbo));

        DataDishEntity spaghetti_bolognaise = new DataDishEntity(DataDishEntity.Type.DISH_READY, "Spaghetti bolognaise");
        spaghetti_bolognaise.getIngredients().add(spagnettiIngredient);
        spaghetti_bolognaise.getTags().add(boeufTag);
        dishRepository.save(spaghetti_bolognaise);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.FULL, pate, spaghetti_bolognaise));

        DataDishEntity hamburgerNode = new DataDishEntity(DataDishEntity.Type.NODE_GENERIC, "Hamburger");
        dishRepository.save(hamburgerNode);

        DataDishEntity friteSide = new DataDishEntity(DataDishEntity.Type.NODE_ITEM, "Frite");
        friteSide.getIngredients().add(potatoIngredient);
        dishRepository.save(friteSide);

        DataDishEntity hamburgerFriteDish = new DataDishEntity(DataDishEntity.Type.DISH_TEMPLATE, "Hamburger & frites");
        dishRepository.save(hamburgerFriteDish);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.MEAT, hamburgerNode, hamburgerFriteDish));
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.SIDE, friteSide, hamburgerFriteDish));


        DataDishEntity steakBurgerDish = new DataDishEntity(DataDishEntity.Type.NODE_ITEM, "Hamburger Steak");
        steakBurgerDish.getIngredients().add(steakIngredient);
        dishRepository.save(steakBurgerDish);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.FULL, hamburgerNode, steakBurgerDish));

        DataDishEntity pouletBurgerDish = new DataDishEntity(DataDishEntity.Type.NODE_ITEM, "Hamburger Poulet");
        pouletBurgerDish.getTags().add(chickenTag);
        dishRepository.save(pouletBurgerDish);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.FULL, hamburgerNode, pouletBurgerDish));


        DataDishEntity champyVeggyBurgerDish = new DataDishEntity(DataDishEntity.Type.NODE_ITEM, "Hamburger végétarien au champignon");
        champyVeggyBurgerDish.getTags().add(veggyTag);
        champyVeggyBurgerDish.getIngredients().add(champignonIngredient);
        dishRepository.save(champyVeggyBurgerDish);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.FULL, hamburgerNode, champyVeggyBurgerDish));

        DataDishEntity gratin = new DataDishEntity(DataDishEntity.Type.NODE_GENERIC, "Gratin");
        gratin.getTags().add(familyTag);
        gratin.getIngredients().add(bechamelIngredient);
        gratin.getIngredients().add(fromageIngredient);
        dishRepository.save(gratin);

        DataDishEntity gratinChouFleurDish = new DataDishEntity(DataDishEntity.Type.DISH_READY, "Gratin de choux-fleur");
        gratinChouFleurDish.getIngredients().add(chouFleurIngredient);
        gratinChouFleurDish.getTags().add(veggyTag);
        dishRepository.save(gratinChouFleurDish);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.FULL, gratin, gratinChouFleurDish));

        DataDishEntity gratinPateDish = new DataDishEntity(DataDishEntity.Type.DISH_READY, "Gratin de pate");
        gratinPateDish.getTags().add(veggyTag);

        dishRepository.save(gratinPateDish);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.FULL, gratin, gratinPateDish));
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.FULL, pate, gratinPateDish));

        /////

        // 101 Risotto petits point jambon
    }

    private DataDishRelationEntity relation(DataDishRelationEntity.Type type, DataDishEntity parent, DataDishEntity child) {
        DataDishRelationEntity relation = new DataDishRelationEntity();
        relation.setId(UUID.randomUUID().toString());
        relation.setType(type);
        relation.setParent(parent);
        relation.setChild(child);
        return relation;
    }

    public void clear() {
        dishRelationRepository.deleteAll();
        dishRepository.deleteAll();
        ingredientRepository.deleteAll();
        tagRepository.deleteAll();
    }
}
