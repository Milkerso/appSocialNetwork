package dawid.app.emailSender;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("emailSender")
public class EmailSenderImpl implements EmailSender {
	
	@Autowired
    private JavaMailSender javaMailSender;

	@Override
	public void sendEmail(String to, String subject, String content) {
		MimeMessage mail = javaMailSender.createMimeMessage();
		try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setFrom("noreply@appsocial.net");
            helper.setSubject(subject);
            MimeMultipart multipart=new MimeMultipart("related");
            BodyPart messageBodyPart=new MimeBodyPart();
            messageBodyPart.setContent(content, "text/html;charset=utf-8");
            multipart.addBodyPart(messageBodyPart);
            mail.setContent(multipart);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mail);
	
	}
}
