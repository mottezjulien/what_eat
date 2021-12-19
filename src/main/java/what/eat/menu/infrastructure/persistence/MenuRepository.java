package what.eat.menu.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, String> {

  /*  String SELECT = "SELECT men FROM MenuEntity men";
    String FETCH_DISH = " LEFT JOIN FETCH men.dish dis";
    String WHERE_ID_EQUALS = " WHERE men.id = :menuId";

    @Query(SELECT + FETCH_DISH)
    List<MenuEntity> findAll_fetchDish();

    @Query(SELECT + FETCH_DISH + WHERE_ID_EQUALS)
    Optional<MenuEntity> findById_fetchDish(@Param("menuId") String id);

    Optional<MenuEntity> findByDate(LocalDate date); */
}
