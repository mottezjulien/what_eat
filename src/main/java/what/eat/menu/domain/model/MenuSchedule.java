package what.eat.menu.domain.model;

import what.eat.Ports;
import what.eat.menu.domain.port.MenuOutput;
import what.eat.recipe.domain.model.RecipeDish;
import what.eat.rule.domain.model.RuleEngine;
import what.eat.rule.domain.model.RuleEngineException;
import what.eat.utils.LocalDateUtils;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

public class MenuSchedule {

    private static final int DURATION_WEEK = 7;

    public static MenuSchedule of(LocalDate date) {
        MenuOutput weakOutput = Ports.getInstance().menuOutput();
        Optional<MenuSchedule> optSchedule = weakOutput.findByDate(date);
        return optSchedule.orElseGet(() -> weakOutput.persist(new MenuSchedule()));
    }

    public static MenuSchedule current() {
        return of(LocalDate.now());
    }

    private final String internalId;
    private final LocalDate begin;
    private final LocalDate end;
    private final Map<LocalDate, DefinedMenu> menusByDay = new HashMap<>();

    public MenuSchedule() {
        this(LocalDate.now(), LocalDate.now().plusDays(DURATION_WEEK - 1));
    }

    public MenuSchedule(LocalDate begin, LocalDate end) {
        this.internalId = UUID.randomUUID().toString();
        this.begin = begin;
        this.end = end;
    }

    public MenuSchedule(String internalId, LocalDate begin, LocalDate end) {
        this.internalId = internalId;
        this.begin = begin;
        this.end = end;
    }

    public String internalId() {
        return internalId;
    }

    public LocalDate begin() {
        return begin;
    }

    public LocalDate end() {
        return end;
    }

    public DefinedMenu findOrGenerate(LocalDate date) throws MenuWeekException {
        DefinedMenu menu = menusByDay.get(date);
        if (menu == null) {
            try {
                menu = new DefinedMenu(generate(date), date);
                menusByDay.put(date, Ports.getInstance().menuOutput().persistMenu(menu, this));
            } catch (RuleEngineException e) {
                throw new MenuWeekException("error to generate menu", e);
            }
        }
        return menu;
    }

    private RecipeDish generate(LocalDate date) throws RuleEngineException {
        RuleEngine ruleEngine = Ports.getInstance().ruleOutput()
                .findAny()
                .orElseGet(RuleEngine::new);
        return ruleEngine.generate(this, date);
    }

    public Stream<Menu> stream() {
        return LocalDateUtils
                .stream(begin, end)
                .map(date -> {
                    Menu menu = menusByDay.get(date);
                    if(menu == null) {
                        menu = new UndefinedMenu(date);
                    }
                    return menu;
                });
    }

    public void insert(DefinedMenu menu) {
        menusByDay.put(menu.date(), menu);
    }

}
