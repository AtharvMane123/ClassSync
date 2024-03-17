package com.class_sync;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
    String StringSenderEmail;
    String stringHost = "smtp.gmail.com";
    Properties properties = System.getProperties();
    public SendEmail(String stringSenderEmail, String userName, String course, String message, int i ) {
        try {
            StringSenderEmail = stringSenderEmail;
            properties.put("mail.smtp.host", stringHost);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("desk.atharv16@gmail.com", "lmxkvjlbrkdziibr");
                }
            });
            MimeMessage mimeMessage = new MimeMessage(session);
            if(i==1)
            {
                mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("maneatharv36@gmail.com"));
                mimeMessage.setSubject("New Course Requested By: "+userName);
                mimeMessage.setText("Welcome Admin, \n\n"+userName+" has requested a \nnew Course for: "+message+"\nthrough email: "+stringSenderEmail+"\n\nSo we hope that the Course will be available soon to the users\n\nRegards Class-Sync..");
            }
            else {
                mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("maneatharv36@gmail.com"));
                mimeMessage.setSubject("Help Requested by : "+userName+"("+stringSenderEmail+")");
                mimeMessage.setText(message);
            }


        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Transport.send(mimeMessage);

                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
            thread.start();
        } catch (AddressException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }
}
