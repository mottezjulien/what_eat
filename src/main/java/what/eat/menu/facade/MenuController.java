package what.eat.menu.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import what.eat.generic.facade.LinkDTO;
import what.eat.menu.domain.model.DefinedMenu;
import what.eat.menu.domain.model.Menu;
import what.eat.menu.domain.model.MenuSchedule;
import what.eat.menu.domain.model.MenuWeekException;
import what.eat.menu.infrastructure.persistence.MenuRepository;
import what.eat.recipe.infrastructure.persistence.repository.RecipeDishRepository;

import java.time.LocalDate;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/menus")
public class MenuController {

    //'HAOST' - URL
    //response default (links default, lang, version ...)

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RecipeDishRepository dishRepository;

    @GetMapping("/week/current/")
    @ResponseStatus(value = HttpStatus.CREATED)
    private MenuWeekDTO current() {
        return toDTO(MenuSchedule.current());
    }


    @PostMapping("/week/current/menu/now/")
    @ResponseStatus(value = HttpStatus.CREATED)
    private MenuDTO now() throws MenuWeekException {
        return toDTO(MenuSchedule.current().of(LocalDate.now()));
    }

    private MenuWeekDTO toDTO(MenuSchedule model) {
        MenuWeekDTO dto = new MenuWeekDTO();
        dto.setMenus(model.stream()
                .map(this::toDTO)
                .collect(Collectors.toList()));
        return dto;
    }

    private MenuDTO toDTO(Menu model) {
        MenuDTO dto = new MenuDTO();
        dto.setDate(model.date());
        if (model instanceof DefinedMenu) {
            DefinedMenu definedMenu = (DefinedMenu) model;
            dto.setDishLabel(definedMenu.dishLabel());
            dto.getLinks()
                    .add(new LinkDTO("menu_details", "http://localhost:8080/api/menu/" + definedMenu.internalId() + "/", "GET"));
        }
        return dto;
    }


    /*
    @PostMapping("/{menuId}/dish/{dishId}/")
    private MenuDTO updateDish(@PathParam("menuId") String menuId, String dishId) throws NotFoundFacadeException {
        MenuEntity menuEntity = menuRepository
                .findById(menuId)
                .orElseThrow(() -> new NotFoundFacadeException("Menu with Id " + menuId + " not found"));

        RecipeDishEntity dishEntity = dishRepository
                .findById(dishId)
                .orElseThrow(() -> new NotFoundFacadeException("Dish with Id " + dishId + " not found"));

        menuEntity.setDish(dishEntity);
        menuRepository.save(menuEntity);

        return toDTO(menuEntity);
    }

    @DeleteMapping("/{menuId}/")
    @ResponseStatus(value = HttpStatus.OK)
    private void delete(@PathParam("menuId") String menuId) throws NotFoundFacadeException {
        MenuEntity menuEntity = menuRepository
                .findById(menuId)
                .orElseThrow(() -> new NotFoundFacadeException("Menu with Id " + menuId + " not found"));
        menuRepository.delete(menuEntity);
    }*/

    /*@PostMapping("/{menuId}/days/{dayId}/")
    private MenuDetailsDTO editDay(@PathParam("menuId") String menuId, @PathParam("dayId") String dayId, @RequestBody DishDTO dish) {
        MenuEntity entity = new MenuEntity();
        entity.setId(UUID.randomUUID().toString());
        //days
        menuRepository.save(entity);
        return menuDetailsDTO
    }*/

    /*private DishDTO toDTO(RecipeDishEntity entity) {
        DishDTO dto = new DishDTO();
        dto.setId(entity.getId());
        dto.setLabel(entity.getLabel());
        return dto;
    }*/

}
