package login.demo.registration;

import login.demo.exceptions.UnprocessableEntityException;
import login.demo.users.Role;
import login.demo.users.UserEntity;
import login.demo.users.UserService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final EmailValidator emailValidator;
    private final UserService userService;

    @SneakyThrows
    public String register(RegistrationRequest request) {

        if (!emailValidator.test(request.getEmail()))
            throw new UnprocessableEntityException(">>>> Email format is not valid");

        //Enabled would be true only when registration is confirm by email
        return userService.signUpUser(
                new UserEntity(request.getName(), request.getLastName(),
                        request.getEmail(), request.getPassword(), Role.USER, false, false));
    }
}
