package login.demo.registration;

import login.demo.userstuff.Role;
import login.demo.userstuff.UserEntity;
import login.demo.userstuff.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final EmailValidator emailValidator;
    private final UserService userService;

    public String register(RegistrationRequest request) {

        if (!emailValidator.test(request.getEmail()))
            throw new IllegalStateException(">>>> Email format is not valid");

        return userService.signUpUser(
                new UserEntity(request.getName(), request.getLastname(),
                        request.getEmail(), request.getPassword(), Role.USER, false, true));
    }
}
