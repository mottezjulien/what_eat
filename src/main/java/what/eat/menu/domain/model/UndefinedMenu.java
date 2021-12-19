package what.eat.menu.domain.model;

import java.time.LocalDate;

public class UndefinedMenu implements Menu {

    private final LocalDate date;

    public UndefinedMenu(LocalDate date) {
        this.date = date;
    }

    @Override
    public LocalDate date() {
        return date;
    }

}
