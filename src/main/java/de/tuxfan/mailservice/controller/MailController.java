package de.tuxfan.mailservice.controller;

import de.tuxfan.mailservice.api.MailSenderApi;
import de.tuxfan.mailservice.model.MailRequest;
import de.tuxfan.mailservice.model.MailSuccessfullySendResponse;
import de.tuxfan.mailservice.service.MailSenderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class MailController implements MailSenderApi {
    private final MailSenderService mailSenderService;

    public MailController(MailSenderService mailSenderService) {
        this.mailSenderService = mailSenderService;
    }

    @Override
    public ResponseEntity<MailSuccessfullySendResponse> sendMail(MailRequest mailRequest) {
        return ResponseEntity.ok(mailSenderService.sendMail(mailRequest));
    }
}
