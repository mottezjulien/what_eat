package what.eat.data.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import what.eat.data.persistence.entity.DataDishEntity;
import what.eat.data.persistence.repository.DataDishJpaRepository;
import what.eat.generic.type.IdLabel;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/data/dish")
public class DataDishController {

    @Autowired
    private DataDishJpaRepository repository;

    @GetMapping("/")
    public Stream<DishShortResponseDTO> findAll() {
        return repository.findAll().stream().map(toShortDTO());
    }

    @GetMapping("/{id}")
    public Optional<DishDetailsResponseDTO> findOne(@PathVariable("id") String id) {
        return repository.findOneFullFetch(id).map(toDetailsDTO());
    }

    @PostMapping("/")
    public DishShortResponseDTO create(DishCreateQueryRequest request) {
       return toShortDTO().apply(repository.save(fromCreateQuery(request)));
    }

    private DataDishEntity fromCreateQuery(DishCreateQueryRequest request) {
        DataDishEntity.Type type = DataDishEntity.Type.valueOf(request.getType());
        return new DataDishEntity(type, request.getFrLabel());
    }

    private Function<DataDishEntity, DishShortResponseDTO> toShortDTO() {
        return entity -> {
            DishShortResponseDTO dto = new DishShortResponseDTO();
            dto.setId(entity.getId());
            dto.setLabel(entity.getFrLabel());
            dto.setType(entity.getType().name());
            return dto;
        };
    }

    private Function<DataDishEntity, DishDetailsResponseDTO> toDetailsDTO() {
        return entity -> {
            DishDetailsResponseDTO dto = new DishDetailsResponseDTO();
            dto.setId(entity.getId());
            dto.setLabel(entity.getFrLabel());
            dto.setType(entity.getType().name());
            dto.setTags(entity.getTags().stream().map(tag -> {
                IdLabel tagDTO = new IdLabel();
                tagDTO.setId(tag.getId());
                tagDTO.setLabel(tag.getFrLabel());
                return tagDTO;
            }).collect(Collectors.toList()));
            dto.setIngredients(entity.getIngredients().stream().map(ingredient -> {
                IdLabel tagDTO = new IdLabel();
                tagDTO.setId(ingredient.getId());
                tagDTO.setLabel(ingredient.getFrLabel());
                return tagDTO;
            }).collect(Collectors.toList()));
            dto.setRelations(entity.getParentRelations().stream().map(relation -> {
                DishRelationResponseDTO relationResponseDTO = new DishRelationResponseDTO();
                relationResponseDTO.setType(relation.getType().name()); //TODO Parent // Child
                IdLabel value = new IdLabel();
                value.setId(relation.getParent().getId());
                value.setLabel(relation.getParent().getFrLabel());
                relationResponseDTO.setValue(value);
                return relationResponseDTO;
            }).collect(Collectors.toList()));
            return dto;
        };
    }


}
