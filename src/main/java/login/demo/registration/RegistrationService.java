package login.demo.registration;

import login.demo.exceptions.UnprocessableEntityException;
import login.demo.registration.token.ConfirmationToken;
import login.demo.registration.token.ConfirmationTokenService;
import login.demo.users.Role;
import login.demo.users.UserEntity;
import login.demo.users.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Service
public class RegistrationService {

    public RegistrationService(EmailValidator emailValidator,
                               UserService userService,
                               ConfirmationTokenService confirmationTokenService) {
        this.emailValidator = emailValidator;
        this.userService = userService;
        this.confirmationTokenService = confirmationTokenService;
    }

    private final EmailValidator emailValidator;
    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;
     private ConfirmationToken confirmationToken;

    @SneakyThrows
    public String register(RegistrationRequest request) {

        if (!emailValidator.test(request.getEmail()))
            throw new UnprocessableEntityException(">>>> Email format is not valid");

        //Enabled would be true only when registration is confirm by email
        return userService.signUpUser(
                new UserEntity(request.getName(), request.getLastName(),
                        request.getEmail(), request.getPassword(), Role.USER, false, false));
    }


    @Transactional
    public String confirmToken(String token) {

        checkConfirmationToken(token);
        confirmationTokenService.setConfirmedAt(token);

        String email = confirmationToken.getUser().getEmail();
        userService.setConfirmEnabledToken(email);
        return "Token confirmed";
    }


    private void checkConfirmationToken(String token) {

        confirmationToken = confirmationTokenService.findByToken(token)
                .orElseThrow(() -> new EntityNotFoundException("Token not found"));

        if (confirmationToken.getConfirmedAt() != null)
            throw new IllegalStateException("Email is already confirmed");

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now()))
            throw new IllegalStateException("Token has expired");
    }


}
