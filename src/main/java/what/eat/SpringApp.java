package what.eat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import what.eat.data.persistence.DataDishInitService;

@Component
public class SpringApp {

    @Autowired
    private DataDishInitService dishService;

    public void init() {
        dishService.init();
    }

    public void stop() {
        dishService.clear();
    }
}
