package com.email.service;

;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;

import java.util.Properties;

@Service
public class EmailService {

    public boolean sendEmail(String subject, String message, String to)
    {
        boolean f=false;
        //rest of the code...

        String from ="ejazsan1996@gmail.com";
        //Variable for gmail 
        String host= "smtp.gmail.com" ;

        //get the system properties
        Properties properties = System.getProperties();
        System.out.println("PROPERTIES "+properties);

        //settting important information to properties object
        properties.put("mail.smtp.host" , host);
        properties.put("mail.smtp.port" , "465");
        properties.put("mail.smtp.ssl.enable" , "true");
        properties.put("mail.smtp.auth", "true");

        //to get the session object...
        Session session=Session.getInstance(properties, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("Ejazsan1996@gmail.com","ejazalam@1996");
            }



        });

        session.setDebug(true);

        //step 2 : compose the message [text, multimedia]
        MimeMessage m = new MimeMessage(session);

        try {
            // from email id
            m.setFrom(from);

            //adding recipient to message

            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            //adding subject to message
            m.setSubject(subject);


            //Adding attachment..
            // file path
            String path="C:\\Users\\Ejaz\\OneDrive\\Desktop\\banner1.png";

            MimeMultipart mimeMultipart = new MimeMultipart();
            //text
            //file

            MimeBodyPart textMime = new MimeBodyPart();

            MimeBodyPart fileMime = new MimeBodyPart();

            try {
                textMime.setText(message);

                File file = new File(path);
                fileMime.attachFile(file);

                mimeMultipart.addBodyPart(textMime);
                mimeMultipart.addBodyPart(fileMime);


            }catch (Exception e) {

                e.printStackTrace();
            }


            m.setContent(mimeMultipart);



            //step 3 : send the message using transport 
            Transport.send(m);

            System.out.println("sent successfully");

            f=true;

        }catch (Exception e) {
            e.printStackTrace();
        }




    }


}


