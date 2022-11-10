package com.airportinfo;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import com.airportinfo.utils.EmailManager;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class EmailTest {
	public static void main(String[] args) {
		try {
			String Email;
			String fileDir;
			Scanner sc = new Scanner(System.in);
			
			System.out.print("���� ���� �̸����� �Է��ϼ��� : ");
			Email = sc.nextLine();
			System.out.print("���� ��θ� �Է��ϼ��� : ");
			fileDir = sc.nextLine();
			sc.close();
			
			EmailManager.send(Email, fileDir);
		} catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
        	e.printStackTrace();
        }
	}
}
