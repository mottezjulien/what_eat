package what.eat.data.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import what.eat.data.domain.model.DataIngredient;
import what.eat.data.persistence.entity.DataIngredientEntity;

import java.util.List;

@Repository
public interface DataIngredientRepository extends JpaRepository<DataIngredientEntity, String> {

    @Query("SELECT ingredient FROM DataIngredientEntity ingredient" +
            " LEFT JOIN FETCH ingredient.tags tag_ingredient" +
            " LEFT JOIN FETCH tag_ingredient.parent tag_ingredient_parent")
    List<DataIngredientEntity> findAllFetchTag();

}
