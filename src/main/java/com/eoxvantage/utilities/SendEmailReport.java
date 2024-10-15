package com.eoxvantage.utilities;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmailReport {

	protected static String toEmail;
	protected static String fromEmail;
	protected static String password;

	public SendEmailReport(String toEmail, String fromEmail, String password) {
		this.toEmail = toEmail;
		this.fromEmail = fromEmail;
		this.password = password;
	}

	static CurrentDateandTime date = new CurrentDateandTime();

	public static void sendEmail(String mailSubject, String mailBody) {

		String reportsPath = System.getProperty("user.dir") + "./Reports/" + date.date() + ".zip";

		// Email configuration
		toEmail = ApplicationFileReader.getCreds("toEmail");
		fromEmail = ApplicationFileReader.getCreds("hostEmail");
		password = ApplicationFileReader.getCreds("hostPassword");

		System.out.println(toEmail);
		System.out.println(fromEmail);
		System.out.println(password);

		// SMTP server settings
		String host = "smtp.googlemail.com";
		int port = 25;

		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		// Authenticate with the email server
		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		});
		session.setDebug(true);

		try {
			// Create a MimeMessage
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			message.setSubject(mailSubject);

			// Create a multipart message
			Multipart multipart = new MimeMultipart();

			// Create the email body text
			BodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText(mailBody);

			// Attach the text part to the message
			multipart.addBodyPart(textBodyPart);

			// Create and attach the zip file
			BodyPart attachmentBodyPart = new MimeBodyPart();
			String zipFilePath = reportsPath;
			DataSource source = new FileDataSource(zipFilePath);
			attachmentBodyPart.setDataHandler(new DataHandler(source));
			attachmentBodyPart.setFileName("reports.zip");

			// Attach the zip file part to the message
			multipart.addBodyPart(attachmentBodyPart);

			// Set the message content to the multipart message
			message.setContent(multipart);

			// Send the email
			Transport.send(message);
			System.out.println("Email sent successfully!");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
