package com.lw.emailsender.utils.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 邮箱用户名和密码包装类
 * 
 * @author Yu.ling
 *
 */
public class PasswordAuthenticator extends Authenticator {

	private String userName;
	private String password;

	public PasswordAuthenticator(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}

	@Override
	public String toString() {
		return "PasswordAuthenticator [userName=" + userName + ", password=" + password + "]";
	}

}
