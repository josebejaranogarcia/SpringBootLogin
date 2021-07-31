package login.demo.userstuff;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo repo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) {
        return repo.findByEmail(email)
                .orElseThrow( ()-> new UsernameNotFoundException(String.format("UserEntity with %s not found", email) ));
    }


    public String signUpUser(UserEntity user){

        boolean userExists= repo.findByEmail(user.getEmail()).isPresent();
        if(userExists)
            throw new IllegalArgumentException(">>> Email is already in use");

        String encryptedPassword= bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        repo.save(user);
        return "User has been registered";
    }
}
