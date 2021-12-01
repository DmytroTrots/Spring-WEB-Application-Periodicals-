package com.periodical.trots.controllers.user;

import com.periodical.trots.utils.Mailer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;

@Controller
public class SendMailServlet {

    @GetMapping("/send-message")
    public String sendMessageGet() {
        return "SendMailPage";
    }

    @PostMapping("/send-message")
    public String sendMessagePost(@RequestParam("to") String to, @RequestParam("subject") String subject, @RequestParam("message") String message) {
        try {
            Mailer.send(to, subject, message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return "redirect:/send-message";
    }
}
