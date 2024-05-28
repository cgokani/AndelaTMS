package com.andela.tms.repositories.services;

import java.io.IOException;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

public class SendGridMailService {

	 SendGrid sendGrid;

	    public SendGridMailService(SendGrid sendGrid) {
	        this.sendGrid = sendGrid;
	    }

	    void sendMail() {
	        Email from = new Email("test@example.com");
	        String subject = "Sending with SendGrid is Fun";
	        Email to = new Email("test@example.com");
	        Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
	        Mail mail = new Mail(from, subject, to, content);

	        Request request = new Request();
	        try {
	            request.setMethod(Method.POST);
	            request.setEndpoint("mail/send");
	            request.setBody(mail.build());
	            Response response = this.sendGrid.api(request);
	            sendGrid.api(request);

	            // ...
	        } catch (IOException ex) {
	            // ...
	        }
	    }
}
