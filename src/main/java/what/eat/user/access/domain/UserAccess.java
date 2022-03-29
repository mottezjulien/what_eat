package what.eat.user.access.domain;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class UserAccess {

    private final String login;
    private final String token;
    private final LocalDateTime lastPing;

    public UserAccess(String login) {
        this.login = login;
        this.token = UUID.randomUUID().toString();
        this.lastPing = LocalDateTime.now();
    }

    private UserAccess(String login, String token) {
        this.login = login;
        this.token = token;
        this.lastPing = LocalDateTime.now();
    }

    public String login() {
        return login;
    }

    public String token() {
        return token;
    }

    public UserAccess keep() {
        return new UserAccess(login, token);
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiry());
    }

    public LocalDateTime expiry() {
        return lastPing.plus(Duration.ofMinutes(5));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccess that = (UserAccess) o;
        return Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }

}
