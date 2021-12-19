package what.eat.rule.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import what.eat.rule.persistence.entity.RuleEntity;

@Repository
public interface RuleRepository extends JpaRepository<RuleEntity, String> {

}
