package what.eat.menu.infrastructure.persistence;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "WHAT_MENU_WEEK")
public class MenuWeekEntity {

    @Id
    private String id;

    private LocalDate begin;

    @Column(name = "_end")
    private LocalDate end;

    @OneToMany(mappedBy = "week")
    private Set<MenuEntity> menus = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getBegin() {
        return begin;
    }

    public void setBegin(LocalDate begin) {
        this.begin = begin;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public Set<MenuEntity> getMenus() {
        return menus;
    }

    public void setMenus(Set<MenuEntity> menus) {
        this.menus = menus;
    }
}
