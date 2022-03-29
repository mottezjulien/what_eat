package what.eat.user.access.infrastructure.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import what.eat.user.access.domain.QueryAskAccess;
import what.eat.user.access.domain.UserAccess;
import what.eat.user.access.domain.UserAccessLoginException;
import what.eat.user.access.domain.UserAccessLoginService;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class UserAccessLoginController {

    @Autowired
    private UserAccessLoginService service;

    /*
    @GetMapping("/secure/test1")
    public String test1() {
        return "test1";
    }

    @GetMapping("/secure/test2")
    @RolesAllowed("ADMIN")
    public String test2() {
        return "test2";
    }*/

    @PostMapping("/login")
    public UserAccessLoginResponse login(@RequestBody UserAccessLoginRequest body, HttpServletRequest request) throws UserAccessLoginException {
        QueryAskAccess askAccess = new QueryAskAccess(body.getUser(), body.getPassword(), request.getRemoteAddr());
        return fromDomain(service.login(askAccess));
    }

    private UserAccessLoginResponse fromDomain(UserAccess userAccess) {
        UserAccessLoginResponse dto = new UserAccessLoginResponse();
        dto.setToken(userAccess.token());
        dto.setExpiry(userAccess.expiry());
        return dto;
    }

}
