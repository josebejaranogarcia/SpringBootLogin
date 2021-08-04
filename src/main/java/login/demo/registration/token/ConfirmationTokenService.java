package login.demo.registration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepo tokenRepo;


    public void saveConfirmationToken(ConfirmationToken confirmationToken) {
        tokenRepo.save(confirmationToken);
    }

    public Optional<ConfirmationToken> findByToken(String token) {
        return tokenRepo.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return tokenRepo.updateConfirmedAt(token, LocalDateTime.now());
    }
}
