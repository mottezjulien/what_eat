package what.eat.recipe.infrastructure.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import what.eat.generic.facade.LinkDTO;
import what.eat.recipe.infrastructure.persistence.entity.RecipeDishEntity;
import what.eat.recipe.infrastructure.persistence.repository.RecipeDishRepository;

import java.util.stream.Stream;

//'HAOST' - URL

@RestController
@RequestMapping("/api/dishes")
public class RecipeDishController {

    @Autowired
    private RecipeDishRepository dishRepository;

    /*@GetMapping("/")
    public Stream<DishDTO> findAll() {
        return dishRepository
                .findAll()
                .stream()
                .map(this::toDTO);
    }*/

    @GetMapping("/type/final/")
    public Stream<RecipeDishDTO> findAll() {
        return dishRepository.findFinals()
                .stream()
                .map(this::toDTO);
    }

    @PostMapping("/{dishId}/")
    public RecipeDishDTO update(RecipeDishDTO recipeDishDTO) {
        return recipeDishDTO;
    }


    private RecipeDishDTO toDTO(RecipeDishEntity entity) {
        RecipeDishDTO dto = new RecipeDishDTO();
        dto.setLabel(entity.getLabel());
        dto.getLinks().add(new LinkDTO("dish_update", "http://localhost:8080/api/dishes/" + entity.getId() + "/", "POST"));
        return dto;
    }


}
