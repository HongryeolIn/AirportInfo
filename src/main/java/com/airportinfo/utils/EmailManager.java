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
    // 1. �߽����� ���� ������ ��й�ȣ ������
    private static final String USER = "MJUairportFile@gmail.com";
    private static final String PASSWORD = "kpflpgpvgvxxzcxh";

	
	public static void send(String email, String filePath) throws AddressException, MessagingException, UnsupportedEncodingException {
		/* �ϴ��� ��ĳ�� ���� gui�� �̵���
    	Scanner sc = new Scanner(System.in);
    	System.out.print("Enter your Email : ");
        String recipient = sc.nextLine();
        sc.close();
    	 */
    	String recipient = email;
    	

        // 2. Property�� SMTP ���� ���� ����
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", 465);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
 
        // 3. SMTP ���������� ����� ������ ������� Session Ŭ������ �ν��Ͻ� ����
        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USER, PASSWORD);
            }
        });
 
        // 4. Message Ŭ������ ��ü�� ����Ͽ� �����ڿ� ����, ������ �޽����� �ۼ��Ѵ�.
       
        // 5. Transport Ŭ������ ����Ͽ� �ۼ��� �޼����� �����Ѵ�
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(USER));
        
        // ������ ���� �ּ�
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

        // ����
        message.setSubject("��ް�ü���� ���� ����");
        // ����
    	String content = "�� ������ ������ ��ް�ü���� ���α׷��� ������Ʈ���� ������ ���� ���� �������,"
        		+ " ���� ���Ͽ��� ����� ������ ÷�����Ϸ� �޾ƿ� �� �ֽ��ϴ�.";
    	String attachment = "html";
    	
    	// ÷������ ��Ʈ (��ġ�� ���� �ʿ�)
        MimeBodyPart attachPart = new MimeBodyPart();
        FileDataSource fds = new FileDataSource(filePath);
        attachPart.setDataHandler(new DataHandler(fds));
        attachPart.setFileName(MimeUtility.encodeText(fds.getName(), "euc-kr","B"));
        MimeBodyPart bodypart = new MimeBodyPart();
        bodypart.setContent(content, "text/html;charset=euc-kr");
       
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(bodypart);
        multipart.addBodyPart(attachPart);
       
        message.setContent(multipart);
        Transport.send(message);
        /*
        Transport.send(message);
        */
        
        System.out.println("Send Complete");
	}

}
