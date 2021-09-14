package testpackage;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;

public class EmailSender {
	public static void main(String args[]){ } 
	
	public void emailStatement(String emailDestination, String subject, String message){
        try { 
        	String host ="smtp.gmail.com"; String user = "kunaldaga28@gmail.com";
            String pass = "goawayhackers123";
            
            String to = emailDestination;
            String from = "kunaldaga28@gmail.com";
            String emailSubject = subject;
            String messageText = message;
            boolean sessionDebug = false;

            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(emailSubject); msg.setSentDate(new Date());
            msg.setText(messageText);

            Transport transport=mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } 
        
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}