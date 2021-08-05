package login.demo.emails;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

    private final static Logger log = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender mailSender;

    @Override
    @Async
    public void send(String to, String emailMsg) {
        try {
            MimeMessage mimeMsg = mailSender.createMimeMessage();
            MimeMessageHelper msgHelper = new MimeMessageHelper(mimeMsg, "utf-8");
            msgHelper.setTo(to);
            msgHelper.setText(emailMsg, true);
            msgHelper.setSubject("Confirm your email");
            msgHelper.setFrom("confirmation@localhost.com");
            mailSender.send(mimeMsg);

        } catch (MessagingException e) {
            log.error("It could not send confirmation email", e);
            throw new IllegalStateException("It could not send confirmation email");
        }

    }
}
