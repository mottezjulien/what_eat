package what.eat.user.access.infrastructure.config;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import what.eat.user.access.domain.UserAccess;
import what.eat.user.access.domain.UserAccessStore;

import java.util.List;
import java.util.Optional;

public class TokenAuthenticationProvider implements AuthenticationProvider {

    private final UserAccessStore store;

    public TokenAuthenticationProvider(UserAccessStore store) {
        this.store = store;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        Optional<UserAccess> optUserAccess = store.findByToken((String) token.getCredentials());
        return optUserAccess
                .map(userAccess -> new UsernamePasswordAuthenticationToken(userAccess.login(), userAccess.login(), List.of(new SimpleGrantedAuthority("USER"))))
                .orElse(null);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
