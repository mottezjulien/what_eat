package what.eat.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import what.eat.core.brain.BrainException;

import java.util.stream.Stream;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    @Autowired
    private DemoService service;

    @GetMapping("/")
    public Stream<DemoResponse> demo() throws BrainException {
        return service.demo().map(domain -> toResponse(domain));
    }

    private DemoResponse toResponse(Demo domain) {
        DemoResponse demoResponse = new DemoResponse();
        demoResponse.setId(domain.id());
        demoResponse.setLabel(domain.frLabel());
        return demoResponse;
    }

}
