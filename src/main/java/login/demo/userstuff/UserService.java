package login.demo.userstuff;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repo.findByEmail(email)
                .orElseThrow( ()-> new UsernameNotFoundException(String.format("UserEntity with %s not found", email) ));
    }

    public String signUpUser(UserEntity user){
        repo.save(user);
        return "User has been registered";
    }
}
