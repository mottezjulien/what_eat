package what.eat.data.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import what.eat.data.persistence.entity.DataTagEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface DataTagRepository extends JpaRepository<DataTagEntity, String> {

    @Query("SELECT tag FROM DataTagEntity tag WHERE tag.frLabel = :frLabel")
    Optional<DataTagEntity> finByFrLabel(@Param("frLabel") String frLabel);

    /*@Query("SELECT tag FROM DataTagEntity tag LEFT JOIN FETCH tag.parent tag_parent")
    List<DataTagEntity> findAllFetchParent();*/

}
