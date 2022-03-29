package what.eat.user.access.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Scope("singleton")
public class UserAccessStore {

    private final List<UserAccess> list = new ArrayList<>();

    public UserAccess push(String login) {
        clear();
        Optional<UserAccess> optUserAccess = list.stream()
                .filter(userAccess -> userAccess.login().equals(login))
                .findAny();
        return optUserAccess
                .map(found -> {
                    UserAccess keep = found.keep();
                    list.remove(found);
                    list.add(keep);
                    return keep;
                }).orElseGet(() ->{
                    UserAccess newAccess = new UserAccess(login);
                    list.add(newAccess);
                    return newAccess;
                });
    }

    public Optional<UserAccess> findByToken(String token) {
        clear();
        return list.stream()
                .filter(userAccess -> userAccess.token().equals(token))
                .findAny();

    }


    private void clear() {
        list.stream().filter(userAccess -> userAccess.isExpired())
                .forEach(userAccess -> list.remove(userAccess));

    }
}
