package login.demo.users;

import login.demo.registration.token.ConfirmationToken;
import login.demo.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
@Log
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService tokenService;


    @Override
    public UserDetails loadUserByUsername(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException
                        (String.format("UserEntity with %s not found", email)));
    }


    public String signUpUser(UserEntity user) {

        existsUser(user.getEmail());

        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        userRepo.save(user);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user);

        tokenService.saveConfirmationToken(confirmationToken);
        log.info(token);

        //Send Email
        return token;
    }

    private void existsUser(String email) {
        boolean userExists = userRepo.findByEmail(email).isPresent();
        if (userExists)
            throw new IllegalArgumentException(">>> Email is already in use");
    }

    public void setConfirmEnabledToken(String email){
            userRepo.enableUser(email);
    }

}
