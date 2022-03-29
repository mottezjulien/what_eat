package what.eat.user.access.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import what.eat.user.access.infrastructure.persistence.UserRepository;

@Service
public class UserAccessLoginService {

    @Autowired
    private UserAccessStore store;

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public UserAccess login(QueryAskAccess askAccess) throws UserAccessLoginException {
        String encodePassword = encoder.encode(askAccess.password());
        return repository.findByLogin(askAccess.login(), encodePassword)
                .map(user -> store.push(askAccess.login()))
                .orElseThrow(UserAccessLoginException::new);
    }
}
