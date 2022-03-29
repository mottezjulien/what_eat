package what.eat.user.access.infrastructure.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class AccessTokenAuthenticationFilter implements Filter {

    public static final String HEADER_KEY_TOKEN = "Access-Token";
    private final AuthenticationManager authenticationManager;

    public AccessTokenAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = ((HttpServletRequest) request).getHeader(HEADER_KEY_TOKEN);
        if(token != null) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(token, token, List.of()));
        }
        filterChain.doFilter(request, response);
    }
}
