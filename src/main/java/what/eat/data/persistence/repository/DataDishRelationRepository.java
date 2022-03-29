package what.eat.data.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import what.eat.data.persistence.entity.DataDishRelationEntity;
import what.eat.data.persistence.entity.DataIngredientEntity;

@Repository
public interface DataDishRelationRepository extends JpaRepository<DataDishRelationEntity, String> {

}
