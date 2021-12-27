package what.eat.rule.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import what.eat.rule.persistence.entity.RuleEngineEntity;

import java.util.List;

@Repository
public interface RuleEngineRepository extends JpaRepository<RuleEngineEntity, String> {

    @Query("SELECT DISTINCT eng FROM RuleEngineEntity eng" +
            " LEFT JOIN FETCH eng.rules rul" +
            " LEFT JOIN FETCH rul.comparedTo comp")
    List<RuleEngineEntity> findAllFetchRulesFetchRuleIndicator();

}
