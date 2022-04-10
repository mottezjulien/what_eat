package what.eat.data.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import what.eat.data.persistence.entity.DataDishEntity;
import what.eat.data.persistence.entity.DataIngredientEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface DataDishJpaRepository extends JpaRepository<DataDishEntity, String> {

    String SELECT = "SELECT DISTINCT dish FROM DataDishEntity dish";
    String FETCH_ALL = " LEFT JOIN FETCH dish.tags tag" +
            " LEFT JOIN FETCH tag.parent tag_parent" + // ???
            " LEFT JOIN FETCH dish.ingredients ingredient" +
            " LEFT JOIN FETCH dish.parentRelations relation_parent" +
            " LEFT JOIN FETCH relation_parent.parent relation_parent_dish";

    @Query(SELECT + FETCH_ALL)
    List<DataDishEntity> findAllFullFetch();

    @Query(SELECT + FETCH_ALL + " WHERE dish.id = :dish_id")
    Optional<DataDishEntity> findOneFullFetch(@Param("dish_id") String id);


    @Query( SELECT + " WHERE dish.frLabel = :frLabel")
    Optional<DataDishEntity> finByFrLabel(@Param("frLabel") String frLabel);

}
