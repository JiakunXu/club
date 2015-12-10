package com.wideka.club.framework.mail;

import javax.mail.Authenticator;

/**
 * 
 * @author
 * 
 */
public class SmtpAuth extends Authenticator {

	private String username;

	private String password;

	public SmtpAuth(String username, String password) {
		this.username = username;
		this.password = password;
	}

	protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
		return new javax.mail.PasswordAuthentication(username, password);
	}

}
