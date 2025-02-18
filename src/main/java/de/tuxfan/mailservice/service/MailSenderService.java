package de.tuxfan.mailservice.service;

import de.tuxfan.mailservice.model.MailRequest;
import de.tuxfan.mailservice.model.MailSuccessfullySendResponse;

public interface MailSenderService {
    MailSuccessfullySendResponse sendMail(MailRequest mailRequest);
}
