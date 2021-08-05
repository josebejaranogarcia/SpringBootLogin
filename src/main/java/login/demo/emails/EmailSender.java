package login.demo.emails;

import javax.mail.MessagingException;

public interface EmailSender {
    void send(String to, String email);
}
