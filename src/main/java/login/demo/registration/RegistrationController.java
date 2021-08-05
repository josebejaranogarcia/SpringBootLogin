package login.demo.registration;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private  RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request)  {
        return registrationService.register(request);
    }

    @GetMapping(path= "confirm")
    public String confirmToken(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }

}
