package what.eat.menu.facade;

import java.util.ArrayList;
import java.util.List;

public class MenuScheduleDTO {

    private List<MenuShortDTO> menus = new ArrayList<>();

    public List<MenuShortDTO> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuShortDTO> menus) {
        this.menus = menus;
    }
}
