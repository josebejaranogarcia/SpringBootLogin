package login.demo.registration;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
@Log
public class RegistrationController {

    private  RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request)  {
        log.warning(">>>>>>>>>llamando a registro....");
        return registrationService.register(request);
    }

}
