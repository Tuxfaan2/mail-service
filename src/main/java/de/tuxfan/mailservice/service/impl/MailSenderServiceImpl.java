package de.tuxfan.mailservice.service.impl;

import de.tuxfan.mailservice.MailServiceApplicationProperties;
import de.tuxfan.mailservice.model.MailRequest;
import de.tuxfan.mailservice.model.MailSuccessfullySendResponse;
import de.tuxfan.mailservice.service.MailSenderService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Properties;

@Service
public class MailSenderServiceImpl implements MailSenderService {
    private static final String HOST_URL = "smtp.gmail.com";
    public static final int HOST_PORT = 587;
    private final MailServiceApplicationProperties mailServiceApplicationProperties;

    public MailSenderServiceImpl(MailServiceApplicationProperties mailServiceApplicationProperties) {
        this.mailServiceApplicationProperties = mailServiceApplicationProperties;
    }

    @Override
    public MailSuccessfullySendResponse sendMail(MailRequest mailRequest) {
        JavaMailSender mailSender = getJavaMailSender();
        SimpleMailMessage simpleMailMessage = getSimpleMailMessage(mailRequest);
        mailSender.send(simpleMailMessage);
        return getMailSuccesfullySendResponse(simpleMailMessage);
    }

    private MailSuccessfullySendResponse getMailSuccesfullySendResponse(SimpleMailMessage simpleMailMessage) {
        MailSuccessfullySendResponse mailSuccessfullySendResponse = new MailSuccessfullySendResponse();
        mailSuccessfullySendResponse.setMailAdress(Objects.requireNonNull(simpleMailMessage.getTo())[0]);
        mailSuccessfullySendResponse.setSubject(simpleMailMessage.getSubject());
        return mailSuccessfullySendResponse;
    }

    private SimpleMailMessage getSimpleMailMessage(MailRequest mailRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailServiceApplicationProperties.getMailUser());
        message.setTo(mailRequest.getMailAdress());
        message.setSubject(mailRequest.getSubject());
        message.setText(mailRequest.getContent());
        return message;
    }

    private JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(HOST_URL);
        mailSender.setPort(HOST_PORT);

        mailSender.setUsername(mailServiceApplicationProperties.getMailUser());
        mailSender.setPassword(mailServiceApplicationProperties.getMailPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
