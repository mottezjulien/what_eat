package what.eat.rule.domain.port;


import what.eat.rule.domain.model.RuleEngine;

import java.util.Optional;

public interface RuleOutput {

    Optional<RuleEngine> findAny();

}
