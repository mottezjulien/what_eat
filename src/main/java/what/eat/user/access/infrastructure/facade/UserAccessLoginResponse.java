package what.eat.user.access.infrastructure.facade;

import java.time.LocalDateTime;

public class UserAccessLoginResponse {

    private String token;

    private LocalDateTime expiry;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setExpiry(LocalDateTime expiry) {
        this.expiry = expiry;
    }

    public LocalDateTime getExpiry() {
        return expiry;
    }
}
