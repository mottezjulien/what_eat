package what.eat.user.access.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    @Query("SELECT user FROM UserEntity user WHERE user.login = :login AND user.encodePassword = :encodePassword")
    Optional<UserEntity> findByLogin(@Param("login") String login, @Param("encodePassword")  String encodePassword);

}
