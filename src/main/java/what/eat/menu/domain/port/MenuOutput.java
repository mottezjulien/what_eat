package what.eat.menu.domain.port;

import what.eat.menu.domain.model.DefinedMenu;
import what.eat.menu.domain.model.Menu;
import what.eat.menu.domain.model.MenuSchedule;

import java.time.LocalDate;
import java.util.Optional;

public interface MenuOutput {

    Optional<MenuSchedule> findByDate(LocalDate date);

    MenuSchedule persist(MenuSchedule menuSchedule);
    DefinedMenu persistMenu(DefinedMenu menu, MenuSchedule menuSchedule);

}
