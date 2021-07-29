package login.demo.registration;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String name;
    private final String lastname;
    private final String email;
    private final String password;
}
