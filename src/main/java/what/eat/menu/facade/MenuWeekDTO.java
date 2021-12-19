package what.eat.menu.facade;

import java.util.ArrayList;
import java.util.List;

public class MenuWeekDTO {

    private List<MenuDTO> menus = new ArrayList<>();

    public List<MenuDTO> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuDTO> menus) {
        this.menus = menus;
    }
}
