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
public class DataDishInitService {

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

        fillTags();

        fillIngredients();

        fillPastaDishes();

        fillFeculentAndVeggySides();

        fillChickenAndSteakAndFishesDishes();

        fillHamburgerDishes();

        fillGratinDishes();

        fillSaucisseLentilleDishes();

        ///

        // Tarte

        // Pizza

        /////

        // 101 Risotto petits point jambon
    }


    private void fillTags() {

        ///// Général

        DataTagEntity veggyTag = new DataTagEntity("Végétarien");
        tagRepository.save(veggyTag);

        DataTagEntity familyTag = new DataTagEntity("Familial");
        tagRepository.save(familyTag);

        DataTagEntity fishTag = new DataTagEntity("Poisson");
        tagRepository.save(fishTag);

        DataTagEntity laitageTag = new DataTagEntity("Laitage");
        tagRepository.save(laitageTag);

        DataTagEntity fromageTag = new DataTagEntity("Fromage");
        fromageTag.setParent(laitageTag);
        tagRepository.save(fromageTag);

        DataTagEntity legumineuxTag = new DataTagEntity("Légumineux");
        tagRepository.save(legumineuxTag);

        DataTagEntity legumeTag = new DataTagEntity("Légume");
        tagRepository.save(legumeTag);

        DataTagEntity champignonTag = new DataTagEntity("Champignon");
        tagRepository.save(champignonTag);


        ///// Famille Féculent

        DataTagEntity feculentTag = new DataTagEntity("Féculent");
        tagRepository.save(feculentTag);

        DataTagEntity pateTag = new DataTagEntity("Pâte");
        pateTag.setParent(feculentTag);
        tagRepository.save(pateTag);

        ///// Famille Meat

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

    }



    private void fillIngredients() {

        ///// VIANDES

        DataIngredientEntity saucisseIngredient = new DataIngredientEntity("Saucisse");
        saucisseIngredient.getTags().add(tagByLabel("Porc"));
        ingredientRepository.save(saucisseIngredient);

        DataIngredientEntity lardonIngredient = new DataIngredientEntity("Lardon");
        lardonIngredient.getTags().add(tagByLabel("Porc"));
        ingredientRepository.save(lardonIngredient);

        DataIngredientEntity steakHacheIngredient = new DataIngredientEntity("Steak haché");
        steakHacheIngredient.getTags().add(tagByLabel("Boeuf"));
        ingredientRepository.save(steakHacheIngredient);



        /*
        INGREDIENT -> MORE SPE (not tag, laitage existe)
        DataIngredientEntity fromageIngredient = new DataIngredientEntity("Fromage");
        fromageIngredient.getTags().add(laitageTag);
        ingredientRepository.save(fromageIngredient);*/


        ///// LEGUMES

        DataIngredientEntity chouFleurIngredient = new DataIngredientEntity("Choux-fleur");
        chouFleurIngredient.getTags().add(tagByLabel("Légume"));
        ingredientRepository.save(chouFleurIngredient);


        //// LAITAGE

        DataIngredientEntity cremeFraicheIngredient = new DataIngredientEntity("Crème fraiche");
        cremeFraicheIngredient.getTags().add(tagByLabel("Laitage"));
        ingredientRepository.save(cremeFraicheIngredient);

        DataIngredientEntity gruyereIngredient = new DataIngredientEntity("Gruyère");
        gruyereIngredient.getTags().add(tagByLabel("Fromage"));
        ingredientRepository.save(gruyereIngredient);


         /*
        INGREDIENT -> MORE SPE
        DataIngredientEntity champignonIngredient = new DataIngredientEntity("Champignon");
        ingredientRepository.save(champignonIngredient);*/

        ///// FECULENTS

        DataIngredientEntity tagliatelleIngredient = new DataIngredientEntity("Tagliatelle");
        tagliatelleIngredient.getTags().add(tagByLabel("Pâte"));
        ingredientRepository.save(tagliatelleIngredient);

        DataIngredientEntity spagnettiIngredient = new DataIngredientEntity("Spagnetti");
        spagnettiIngredient.getTags().add(tagByLabel("Pâte"));
        ingredientRepository.save(spagnettiIngredient);

        DataIngredientEntity riceIngredient = new DataIngredientEntity("Riz");
        riceIngredient.getTags().add(tagByLabel("Féculent"));
        ingredientRepository.save(riceIngredient);

        DataIngredientEntity potatoIngredient = new DataIngredientEntity("Pomme de terre");
        potatoIngredient.getTags().add(tagByLabel("Féculent"));
        ingredientRepository.save(potatoIngredient);
    }



    private void fillPastaDishes() {
        DataDishEntity pate = new DataDishEntity(DataDishEntity.Type.NODE_GENERIC, "Pate (plat)");
        pate.getTags().add(tagByLabel("Pâte"));
        dishRepository.save(pate);

        DataDishEntity tagliatelle_carbo = new DataDishEntity(DataDishEntity.Type.DISH_READY, "Tagliatelle Carbonara");
        tagliatelle_carbo.getIngredients().add(ingredientByLabel("Tagliatelle"));
        tagliatelle_carbo.getIngredients().add(ingredientByLabel("Crème fraiche"));
        tagliatelle_carbo.getIngredients().add(ingredientByLabel("Lardon"));
        dishRepository.save(tagliatelle_carbo);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.FULL, pate, tagliatelle_carbo));

        DataDishEntity spaghetti_bolognaise = new DataDishEntity(DataDishEntity.Type.DISH_READY, "Spaghetti bolognaise");
        spaghetti_bolognaise.getIngredients().add(ingredientByLabel("Spagnetti"));
        spaghetti_bolognaise.getTags().add(tagByLabel("Boeuf"));
        dishRepository.save(spaghetti_bolognaise);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.FULL, pate, spaghetti_bolognaise));
    }



    private void fillFeculentAndVeggySides() {

        DataDishEntity feculentNode = new DataDishEntity(DataDishEntity.Type.NODE_GENERIC, "Accompagnement Féculent");
        feculentNode.getTags().add(tagByLabel("Féculent"));
        dishRepository.save(feculentNode);

        DataDishEntity riceItem = new DataDishEntity(DataDishEntity.Type.NODE_ITEM, "Riz (accompagnement)");
        riceItem.getIngredients().add(ingredientByLabel("Riz"));
        dishRepository.save(riceItem);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.FULL, feculentNode, riceItem));

        DataDishEntity pastaItem = new DataDishEntity(DataDishEntity.Type.NODE_ITEM, "Pâte (accompagnement)");
        pastaItem.getTags().add(tagByLabel("Pâte"));
        dishRepository.save(pastaItem);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.FULL, feculentNode, pastaItem));

        DataDishEntity pureeItem = new DataDishEntity(DataDishEntity.Type.NODE_ITEM, "Purée (accompagnement)");
        pureeItem.getIngredients().add(ingredientByLabel("Pomme de terre"));
        dishRepository.save(pureeItem);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.FULL, feculentNode, pureeItem));

        DataDishEntity friteItem = new DataDishEntity(DataDishEntity.Type.NODE_ITEM, "Frite (accompagnement)");
        friteItem.getIngredients().add(ingredientByLabel("Pomme de terre"));
        dishRepository.save(friteItem);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.FULL, feculentNode, friteItem));
  
        ////

        DataDishEntity vegetableNode = new DataDishEntity(DataDishEntity.Type.NODE_GENERIC, "Accompagnement Légume");
        vegetableNode.getTags().add(tagByLabel("Légume"));
        dishRepository.save(vegetableNode);

        DataDishEntity carrotItem = new DataDishEntity(DataDishEntity.Type.NODE_ITEM, "Carotte (Accompagnement)");
        dishRepository.save(carrotItem);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.FULL, vegetableNode, carrotItem));

        DataDishEntity petitPoisItem = new DataDishEntity(DataDishEntity.Type.NODE_ITEM, "Petit Pois (Accompagnement)");
        dishRepository.save(petitPoisItem);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.FULL, vegetableNode, petitPoisItem));

        DataDishEntity petitPoisCarotteItem = new DataDishEntity(DataDishEntity.Type.NODE_ITEM, "Petit Pois Carotte (Accompagnement)");
        dishRepository.save(petitPoisCarotteItem);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.FULL, vegetableNode, petitPoisCarotteItem));

        DataDishEntity greenBeanItem = new DataDishEntity(DataDishEntity.Type.NODE_ITEM, "Haricot vert (Accompagnement)");
        dishRepository.save(greenBeanItem);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.FULL, vegetableNode, greenBeanItem));

        DataDishEntity ratatouilleItem = new DataDishEntity(DataDishEntity.Type.NODE_ITEM, "Ratatouille (Accompagnement)");
        dishRepository.save(ratatouilleItem);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.FULL, vegetableNode, ratatouilleItem));

        DataDishEntity chouxBruxelleItem = new DataDishEntity(DataDishEntity.Type.NODE_ITEM, "Choux de Bruxelle (Accompagnement)");
        dishRepository.save(chouxBruxelleItem);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.FULL, vegetableNode, chouxBruxelleItem));

        DataDishEntity epinardItem = new DataDishEntity(DataDishEntity.Type.NODE_ITEM, "Épinard (Accompagnement)");
        dishRepository.save(epinardItem);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.FULL, vegetableNode, epinardItem));

        DataDishEntity chouxFleurItem = new DataDishEntity(DataDishEntity.Type.NODE_ITEM, "Choux-Fleur (Accompagnement)");
        chouxFleurItem.getIngredients().add(ingredientByLabel("Choux-fleur"));
        dishRepository.save(chouxFleurItem);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.FULL, vegetableNode, chouxFleurItem));

    }

    private void fillChickenAndSteakAndFishesDishes() {

        DataDishEntity chickenItem = new DataDishEntity(DataDishEntity.Type.NODE_ITEM, "Plat à base de Poulet");
        chickenItem.getTags().add(tagByLabel("Poulet"));
        dishRepository.save(chickenItem);

        //Poulet + féculent
        DataDishEntity chickenFeculent = new DataDishEntity(DataDishEntity.Type.DISH_TEMPLATE, "Poulet & Féculent");
        dishRepository.save(chickenFeculent);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.MEAT, chickenItem, chickenFeculent));
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.SIDE, dishByLabel("Accompagnement Féculent"), chickenFeculent));

        //Poulet + légume
        DataDishEntity chickenVegetable = new DataDishEntity(DataDishEntity.Type.DISH_TEMPLATE, "Poulet & légume");
        dishRepository.save(chickenVegetable);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.MEAT, chickenItem, chickenVegetable));
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.SIDE, dishByLabel("Accompagnement Légume"), chickenVegetable));

        //Poulet + féculent + légume
        DataDishEntity chickenFeculentVegetable = new DataDishEntity(DataDishEntity.Type.DISH_TEMPLATE, "Poulet & Féculent & légume");
        dishRepository.save(chickenFeculentVegetable);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.MEAT, chickenItem, chickenFeculentVegetable));
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.SIDE, dishByLabel("Accompagnement Féculent"), chickenFeculentVegetable));
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.SIDE, dishByLabel("Accompagnement Légume"), chickenFeculentVegetable));

        ////

        DataDishEntity steakItem = new DataDishEntity(DataDishEntity.Type.NODE_ITEM, "Steak haché");
        steakItem.getIngredients().add(ingredientByLabel("Steak haché"));
        dishRepository.save(steakItem);

        //Steak haché + féculent
        DataDishEntity steakFeculent = new DataDishEntity(DataDishEntity.Type.DISH_TEMPLATE, "Steak haché & Féculent");
        dishRepository.save(steakFeculent);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.MEAT, steakItem, steakFeculent));
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.SIDE, dishByLabel("Accompagnement Féculent"), steakFeculent));

        //Steak haché + légume
        DataDishEntity steakVegetable = new DataDishEntity(DataDishEntity.Type.DISH_TEMPLATE, "Steak haché & légume");
        dishRepository.save(steakVegetable);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.MEAT, steakItem, steakVegetable));
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.SIDE, dishByLabel("Accompagnement Légume"), steakVegetable));

        //Steak haché + féculent + légume
        DataDishEntity steakFeculentVegetable = new DataDishEntity(DataDishEntity.Type.DISH_TEMPLATE, "Steak haché & Féculent & légume");
        dishRepository.save(steakFeculentVegetable);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.MEAT, steakItem, steakFeculentVegetable));
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.SIDE, dishByLabel("Accompagnement Féculent"), steakFeculentVegetable));
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.SIDE, dishByLabel("Accompagnement Légume"), steakFeculentVegetable));

        ////

        DataDishEntity breadedFishItem = new DataDishEntity(DataDishEntity.Type.NODE_ITEM, "Poisson pané");
        breadedFishItem.getTags().add(tagByLabel("Poisson"));
        dishRepository.save(breadedFishItem);

        //Poisson pané + féculent
        DataDishEntity breadedFishFeculent = new DataDishEntity(DataDishEntity.Type.DISH_TEMPLATE, "Poisson pané & Féculent");
        dishRepository.save(breadedFishFeculent);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.MEAT, breadedFishItem, breadedFishFeculent));
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.SIDE, dishByLabel("Accompagnement Féculent"), breadedFishFeculent));

        //Poisson pané + légume
        DataDishEntity breadedFishVegetable = new DataDishEntity(DataDishEntity.Type.DISH_TEMPLATE, "Poisson pané & légume");
        dishRepository.save(breadedFishVegetable);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.MEAT, breadedFishItem, breadedFishVegetable));
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.SIDE, dishByLabel("Accompagnement Légume"), breadedFishVegetable));

        //Poisson pané + féculent + légume
        DataDishEntity breadedFishFeculentVegetable = new DataDishEntity(DataDishEntity.Type.DISH_TEMPLATE, "Poisson pané & Féculent & légume");
        dishRepository.save(breadedFishFeculentVegetable);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.MEAT, breadedFishItem, breadedFishFeculentVegetable));
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.SIDE, dishByLabel("Accompagnement Féculent"), breadedFishFeculentVegetable));
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.SIDE, dishByLabel("Accompagnement Légume"), breadedFishFeculentVegetable));

        ////

        DataDishEntity saucisseItem = new DataDishEntity(DataDishEntity.Type.NODE_ITEM, "Saucisse");
        saucisseItem.getTags().add(tagByLabel("Porc"));
        dishRepository.save(saucisseItem);

        //Saucisse + féculent
        DataDishEntity saucisseFeculent = new DataDishEntity(DataDishEntity.Type.DISH_TEMPLATE, "Saucisse & Féculent");
        dishRepository.save(saucisseFeculent);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.MEAT, saucisseItem, saucisseFeculent));
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.SIDE, dishByLabel("Accompagnement Féculent"), saucisseFeculent));

        //Saucisse + légume
        DataDishEntity saucisseVegetable = new DataDishEntity(DataDishEntity.Type.DISH_TEMPLATE, "Saucisse & légume");
        dishRepository.save(saucisseVegetable);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.MEAT, saucisseItem, saucisseVegetable));
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.SIDE, dishByLabel("Accompagnement Légume"), saucisseVegetable));

        //Saucisse + féculent + légume
        DataDishEntity saucisseFeculentVegetable = new DataDishEntity(DataDishEntity.Type.DISH_TEMPLATE, "Saucisse & Féculent & légume");
        dishRepository.save(saucisseFeculentVegetable);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.MEAT, saucisseItem, saucisseFeculentVegetable));
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.SIDE, dishByLabel("Accompagnement Féculent"), saucisseFeculentVegetable));
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.SIDE, dishByLabel("Accompagnement Légume"), saucisseFeculentVegetable));

    }


    private void fillHamburgerDishes() {

        DataDishEntity hamburgerNode = new DataDishEntity(DataDishEntity.Type.NODE_GENERIC, "Hamburger (Générique)");
        dishRepository.save(hamburgerNode);

        DataDishEntity steakBurgerDish = new DataDishEntity(DataDishEntity.Type.NODE_ITEM, "Hamburger Steak");
        steakBurgerDish.getIngredients().add(ingredientByLabel("Steak haché"));
        dishRepository.save(steakBurgerDish);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.FULL, hamburgerNode, steakBurgerDish));

        DataDishEntity pouletBurgerDish = new DataDishEntity(DataDishEntity.Type.NODE_ITEM, "Hamburger Poulet");
        pouletBurgerDish.getTags().add(tagByLabel("Poulet"));
        dishRepository.save(pouletBurgerDish);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.FULL, hamburgerNode, pouletBurgerDish));

        DataDishEntity veggyBurgerDish = new DataDishEntity(DataDishEntity.Type.NODE_ITEM, "Hamburger Végétarien");
        veggyBurgerDish.getTags().add(tagByLabel("Végétarien"));
        dishRepository.save(veggyBurgerDish);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.FULL, hamburgerNode, veggyBurgerDish));

        //Hamburger & frites
        DataDishEntity hamburgerFriteDish = new DataDishEntity(DataDishEntity.Type.DISH_TEMPLATE, "Hamburger & frites");
        dishRepository.save(hamburgerFriteDish);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.MEAT, hamburgerNode, hamburgerFriteDish));
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.SIDE, dishByLabel("Frite (accompagnement)") , hamburgerFriteDish));

    }

    private void fillGratinDishes() {

        DataDishEntity gratin = new DataDishEntity(DataDishEntity.Type.NODE_GENERIC, "Gratin (Plat)");
        gratin.getTags().add(tagByLabel("Familial"));
        dishRepository.save(gratin);

        DataDishEntity gratinChouFleurDish = new DataDishEntity(DataDishEntity.Type.DISH_READY, "Gratin de choux-fleur");
        gratinChouFleurDish.getTags().add(tagByLabel("Végétarien"));
        gratinChouFleurDish.getIngredients().add(ingredientByLabel("Choux-fleur"));
        gratinChouFleurDish.getIngredients().add(ingredientByLabel("Crème fraiche"));
        gratinChouFleurDish.getIngredients().add(ingredientByLabel("Gruyère"));
        dishRepository.save(gratinChouFleurDish);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.FULL, gratin, gratinChouFleurDish));

        DataDishEntity gratinPateDish = new DataDishEntity(DataDishEntity.Type.DISH_READY, "Gratin de pate");
        gratinPateDish.getTags().add(tagByLabel("Végétarien"));
        gratinPateDish.getIngredients().add(ingredientByLabel("Crème fraiche"));
        gratinPateDish.getIngredients().add(ingredientByLabel("Gruyère"));
        dishRepository.save(gratinPateDish);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.FULL, gratin, gratinPateDish));
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.FULL, dishByLabel("Pate (plat)"), gratinPateDish));

    }

    private void fillSaucisseLentilleDishes() {

        DataDishEntity lentille = new DataDishEntity(DataDishEntity.Type.NODE_ITEM, "Lentilles (accompagnement)");
        lentille.getTags().add(tagByLabel("Légumineux"));
        dishRepository.save(lentille);

        DataDishEntity saucisseLentille = new DataDishEntity(DataDishEntity.Type.DISH_READY, "Saucisse Lentille");
        dishRepository.save(saucisseLentille);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.MEAT, dishByLabel("Saucisse"), saucisseLentille));
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.SIDE, lentille, saucisseLentille));

        DataDishEntity saucisseCarotteLentille = new DataDishEntity(DataDishEntity.Type.DISH_READY, "Saucisse Lentille Carotte");
        dishRepository.save(saucisseCarotteLentille);
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.MEAT, dishByLabel("Saucisse"), saucisseCarotteLentille));
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.SIDE, lentille, saucisseCarotteLentille));
        dishRelationRepository.save(relation(DataDishRelationEntity.Type.SIDE, dishByLabel("Carotte (Accompagnement)"), saucisseCarotteLentille));

    }


    private DataTagEntity tagByLabel(String label) {
        return tagRepository.finByFrLabel(label).orElseThrow(RuntimeException::new);
    }

    private DataIngredientEntity ingredientByLabel(String label) {
        return ingredientRepository.finByFrLabel(label).orElseThrow(RuntimeException::new);
    }

    private DataDishEntity dishByLabel(String label) {
        return dishRepository.finByFrLabel(label).orElseThrow(RuntimeException::new);
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
