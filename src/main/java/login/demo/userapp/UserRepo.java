package login.demo.userapp;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepo  {
    Optional<UserApp> findByEmail(String email);
}
