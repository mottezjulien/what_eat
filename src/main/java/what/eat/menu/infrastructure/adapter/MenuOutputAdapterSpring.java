package what.eat.menu.infrastructure.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import what.eat.menu.domain.model.DefinedMenu;
import what.eat.menu.domain.model.Menu;
import what.eat.recipe.domain.model.RecipeDish;
import what.eat.menu.domain.model.MenuSchedule;
import what.eat.menu.domain.port.MenuOutput;
import what.eat.menu.infrastructure.persistence.MenuEntity;
import what.eat.menu.infrastructure.persistence.MenuRepository;
import what.eat.menu.infrastructure.persistence.MenuWeekEntity;
import what.eat.menu.infrastructure.persistence.MenuWeekRepository;
import what.eat.recipe.domain.model.RecipeDishFinal;
import what.eat.recipe.infrastructure.adapter.RecipeDishEntityWrapper;
import what.eat.recipe.infrastructure.persistence.entity.RecipeDishEntity;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class MenuOutputAdapterSpring implements MenuOutput {

    @Autowired
    private MenuWeekRepository menuWeekRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RecipeDishEntityWrapper dishWrapper;

    @Override
    public Optional<MenuSchedule> findByDate(LocalDate date) {
        return menuWeekRepository
                .findByDateFetchMenuFetchDish(date)
                .map(entity -> toModel(entity));
    }


    @Override
    public MenuSchedule persist(MenuSchedule menuSchedule) {
        MenuWeekEntity weekEntity = new MenuWeekEntity();
        weekEntity.setId(menuSchedule.internalId());
        weekEntity.setBegin(menuSchedule.begin());
        weekEntity.setEnd(menuSchedule.end());
        return toModel(menuWeekRepository.save(weekEntity));
    }


    @Override
    public DefinedMenu persistMenu(DefinedMenu menu, MenuSchedule menuSchedule) {
        MenuEntity entity = new MenuEntity();
        entity.setId(menu.internalId());
        //entity.setExternalId(menu.externalId());
        entity.setDate(menu.date());
        MenuWeekEntity weekEntity = new MenuWeekEntity();
        weekEntity.setId(menuSchedule.internalId());
        entity.setWeek(weekEntity);
        RecipeDishEntity dishEntity = new RecipeDishEntity();
        dishEntity.setId(menu.dishInternalId());
        entity.setDish(dishEntity);
        return toModel(menuRepository.save(entity));
    }

    private MenuSchedule toModel(MenuWeekEntity entity) {
        MenuSchedule menuSchedule = new MenuSchedule(entity.getId(), entity.getBegin(), entity.getEnd());
        entity.getMenus().forEach(menuEntity -> menuSchedule.insert(toModel(menuEntity)));
        return menuSchedule;
    }

    private DefinedMenu toModel(MenuEntity menuEntity) {
        return new DefinedMenu(menuEntity.getId(), dishWrapper.toFinalModel(menuEntity.getDish()), menuEntity.getDate());
    }

}
