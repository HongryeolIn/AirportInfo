package com.airportinfo.utils;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;


public class EmailManager {
    private static final String USER = "MJUairportFile@gmail.com";
    private static final String PASSWORD = "kpflpgpvgvxxzcxh";
    
	public static void send(String email, String filePath) throws AddressException, MessagingException, UnsupportedEncodingException {
    	String recipient = email;
        //Property�� SMTP ���� ���� ����
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", 465);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        //SMTP ���������� ����� ������ ������� Session Ŭ������ �ν��Ͻ� ����
        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USER, PASSWORD);
            }
        });
        //Transport Ŭ������ ����Ͽ� �ۼ��� �޼����� �����Ѵ�
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(USER));
        //������ ���� �ּ�
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        // ����
        message.setSubject("��ް�ü���� ���� ����");
        // ���� ( content ���� �������� �����Ǿ� ���۵� )
    	String content = "�� ������ ������ ��ް�ü���� ���α׷��� ������Ʈ���� ������ ���� ���� �������,"
        		+ " ���� ���Ͽ��� ����� ������ ÷�����Ϸ� �޾ƿ� �� �ֽ��ϴ�.";
    	// ÷������ ��Ʈ (��ġ�� ���� �ʿ�)
        MimeBodyPart attachPart = new MimeBodyPart();
        FileDataSource fds = new FileDataSource(filePath);
        attachPart.setDataHandler(new DataHandler(fds));
        attachPart.setFileName(MimeUtility.encodeText(fds.getName(), "euc-kr","B"));
        MimeBodyPart bodypart = new MimeBodyPart();
        bodypart.setContent(content, "text/html;charset=euc-kr");
        // ��ġ�� ���� (���ڳ���+÷�����ϳ���)
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(bodypart);
        multipart.addBodyPart(attachPart);
        message.setContent(multipart);
        //����!
        Transport.send(message);
        System.out.println("Send Complete");
	}

}
