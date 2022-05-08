package what.eat.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import what.eat.demo.dto.DemoDishResponse;
import what.eat.demo.dto.DemoRuleResponse;
import what.eat.generic.brain.BrainException;

import java.util.stream.Stream;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    @Autowired
    private DemoService service;

    @GetMapping("/dishes/byGroupId/{groupId}/")
    public Stream<DemoDishResponse> dishes(@PathVariable("groupId") String groupId) throws BrainException, DemoException {
        return service
                .dishes(groupId)
                .map(this::toResponseDish);
    }

    private DemoDishResponse toResponseDish(DemoDish domain) {
        DemoDishResponse response = new DemoDishResponse();
        response.setId(domain.id());
        response.setLabel(domain.frLabel());
        return response;
    }

    @GetMapping("/rule-groups/")
    public Stream<DemoRuleResponse> ruleGroups() {
        return service.ruleGroups()
                .map(this::toResponseRuleGroup);
    }

    private DemoRuleResponse toResponseRuleGroup(DemoRuleGroup domain) {
        DemoRuleResponse response = new DemoRuleResponse();
        response.setId(domain.getId());
        response.setLabel(domain.getLabel());
        response.setDesc(domain.getDesc());
        return response;
    }

}
