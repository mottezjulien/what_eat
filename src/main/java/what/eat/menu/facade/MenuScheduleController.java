package what.eat.menu.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
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
@RequestMapping("/api/menus/schedule")
public class MenuScheduleController {

    //'HAOST' - URL
    //response default (links default, lang, version ...)

    @Autowired
    private MenuRepository menuRepository;

    @GetMapping("/current/")
    @ResponseStatus(value = HttpStatus.CREATED)
    private MenuScheduleDTO current() {
        return toDTO(MenuSchedule.current());
    }

    @PostMapping("/{date}/menu/generate/")
    @ResponseStatus(value = HttpStatus.CREATED)
    private MenuShortDTO generate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) throws MenuWeekException {
        MenuSchedule schedule = MenuSchedule.of(date);
        return toShortDTO(schedule.findOrGenerate(date));
    }

    private MenuScheduleDTO toDTO(MenuSchedule model) {
        MenuScheduleDTO dto = new MenuScheduleDTO();
        dto.setMenus(model.stream()
                .map(this::toShortDTO)
                .collect(Collectors.toList()));
        return dto;
    }

    private MenuShortDTO toShortDTO(Menu model) {
        MenuShortDTO dto = new MenuShortDTO();
        dto.setDate(model.date());
        if (model instanceof DefinedMenu) {
            DefinedMenu definedMenu = (DefinedMenu) model;


            DishShortDTO dishDTO = new DishShortDTO();
            dishDTO.setId(definedMenu.dishInternalId());
            dishDTO.setLabel(definedMenu.dishLabel());
            dishDTO.getLinks()
                    .add(new LinkDTO("details", "/api/dishes/" + definedMenu.dishInternalId() + "/", "GET"));
            dto.setDish(dishDTO);

            dto.getLinks()
                    .add(new LinkDTO("details", "/api/menus/" + definedMenu.internalId() + "/", "GET"));


        } else {
            dto.getLinks()
                    .add(new LinkDTO("generate", "/api/menus/schedule/" + model.date() + "/menu/generate/", "POST"));
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
