package what.eat.recipe.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import what.eat.recipe.infrastructure.persistence.entity.RecipeDishEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeDishRepository extends JpaRepository<RecipeDishEntity, String> {


    String SELECT = "SELECT DISTINCT dish FROM RecipeDishEntity dish";

    String FETCH_ALL_CHILDREN = " LEFT JOIN FETCH dish.children child1"
            +  " LEFT JOIN FETCH child1.children child2"
            +  " LEFT JOIN FETCH child2.children child3"
            +  " LEFT JOIN FETCH child3.children child4"
            +  " LEFT JOIN FETCH child4.children child5";

    String WHERE_ID = " WHERE dish.id = :id";

    @Query(SELECT + " WHERE (dish.type = 'FINAL_SIMPLE' or dish.type = 'FINAL_COMPOSITE')")
    List<RecipeDishEntity> findFinals();


    @Query(SELECT + FETCH_ALL_CHILDREN + WHERE_ID)
    Optional<RecipeDishEntity> findById_FetchAllChildren(@Param("id") String id);

    /*String NATIVE_SELECT_ID = "SELECT dish.* FROM WHAT_RECIPE_DISH dish where dish.id = :id";

    String NATIVE_TRANSVERSE = "SELECT DISTINCT dish.* FROM WHAT_RECIPE_DISH dish"
            + " INNER JOIN WHAT_RECIPE_DISH_PARENT_CHILD parent_child ON parent_child.child_id = dish.id"
            + " INNER JOIN traverse ON traverse.id = parent_child.parent_id";

    //@Query(value = "WITH RECURSIVE traverse AS (" + NATIVE_SELECT_ID + " UNION ALL " + NATIVE_TRANSVERSE + ") SELECT DISTINCT * FROM traverse", nativeQuery = true)
    //@Query(value = "WITH RECURSIVE traverse AS (" + NATIVE_SELECT_ID + ") SELECT DISTINCT * FROM traverse", nativeQuery = true)

    @Query(value = NATIVE_SELECT_ID, nativeQuery = true)
    List<RecipeDishEntity> findById_recursively(@Param("id") String id);*/



}
