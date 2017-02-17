package com.lw.emailsender.utils.mail;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lw.emailsender.model.ConfigPropertistBean;





/**
 * 简单邮件发送类
 * 
 * @author Yu.ling
 *
 */
public class SimpleMailSender {
	private static String email;

//	private static final Logger log = LogManager.getLogger(SimpleMailSender.class.getName());
	protected  Logger log = LoggerFactory.getLogger(this.getClass());
	/**
	 * 发送邮件的props文件
	 */
	private final transient Properties props = System.getProperties();
	/**
	 * 邮件服务器登录验证
	 */
	private transient PasswordAuthenticator authenticator;

	/**
	 * 邮箱session
	 */
	private transient Session session;
	
	private static String senderDisplayName;

	/**
	 * 邮件发送器
	 */
	private static SimpleMailSender instance;

	private SimpleMailSender(){
		
	}
	
	public Session getSession() {
		return session;
	}


	public static String getEmail() {
		return email;
	}

	public static void setEmail(String email) {
		SimpleMailSender.email = email;
	}

	/**
	 * 
	 * 初始化邮箱账户信息
	 * @param smtpHostName,
	 *            smtp服务，如为NULL，则用smtp.exmail.qq.com
	 * @param username，邮箱用户名
	 * @param password，邮箱密码
	 * @return
	 */
	public static void initEmailAccount(ConfigPropertistBean cfProBean) {
		instance = new SimpleMailSender(cfProBean.getHostName(), 
										cfProBean.getEmail(), 
										cfProBean.getPassword(),
										cfProBean.getProtocol());
		senderDisplayName = cfProBean.getDisplayName();
	}
	
	/**
	 * 获取邮件发送器
	 * 
	 * @param smtpHostName,
	 *            smtp服务，如为NULL，则用smtp.exmail.qq.com
	 * @param username，邮箱用户名
	 * @param password，邮箱密码
	 * @return
	 */
	public synchronized static SimpleMailSender getInstance() {
//		log.debug("get the SimpleMailSender instance");
		return instance;
	}

	/**
	 * 初始化邮件发送器
	 * 
	 * @param smtpHostName
	 *            SMTP邮件服务器地址, smtp.163.com
	 * @param username
	 *            发送邮件的用户名(地址)
	 * @param password
	 *            发送邮件的密码
	 * @param protocol
	 *            协议：目前仅支持smtp与pop3两种协议           
	 */
	private SimpleMailSender(final String smtpHostName, final String username, final String password, final String protocol) {
		init(username, password, smtpHostName,protocol);
	}


	/**
	 * 初始化
	 * 
	 * @param username
	 *            发送邮件的用户名(地址)
	 * @param password
	 *            密码
	 * @param smtpHostName
	 *            SMTP主机地址
	 * @param protocol
	 *            协议：目前仅支持smtp与pop3两种协议
	 */
	private void init(String username, String password, String smtpHostName,String protocol) {
		// 初始化props
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", smtpHostName);
		if("smtp".equals(protocol)){
			props.put("mail.transport.protocol", protocol);
		}else if("pop3".equals(protocol)){
			props.put("mail.store.protocol", protocol);
		}

		// 验证
		authenticator = new PasswordAuthenticator(username, password);
		email = username;
		// 创建session
		session = Session.getInstance(props, authenticator);
		session.setDebug(true);
	}
	
	/**
	 * 发送邮件
	 * 
	 * @param recipient
	 *            收件人邮箱地址
	 * @param subject
	 *            邮件主题
	 * @param content
	 *            邮件内容
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void send(String recipient, String subject, Object content, File attachment) throws AddressException, MessagingException {
		log.debug("send mail, recipient = "+recipient+", subject = "+subject+", content = "+content.toString());
		if(recipient==null || recipient==""||subject==null || subject==""||content==null || content=="")
			return;
		// 创建mime类型邮件
		final MimeMessage message = new MimeMessage(session);
		// 设置发信人
		try {
			message.setFrom(new InternetAddress(authenticator.getUserName(), senderDisplayName));
		} catch (UnsupportedEncodingException e) {
			log.error("sendmail exception : ", e);
		}
		// 设置收件人
		message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
		// 设置主题
		message.setSubject(subject);
		
		 // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
        Multipart multipart = new MimeMultipart();
        
        // 添加邮件正文
        BodyPart contentPart = new MimeBodyPart();
        contentPart.setContent(content.toString(), "text/html;charset=UTF-8");
        multipart.addBodyPart(contentPart);
        
        // 添加附件的内容
        if (attachment != null) {
            BodyPart attachmentBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachment);
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            
            // 网上流传的解决文件名乱码的方法，其实用MimeUtility.encodeWord就可以很方便的搞定
            // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
            //sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
            //messageBodyPart.setFileName("=?GBK?B?" + enc.encode(attachment.getName().getBytes()) + "?=");
            
            //MimeUtility.encodeWord可以避免文件名乱码
            try {
				attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachment.getName()));
			} catch (UnsupportedEncodingException e) {
				log.error("sendmail exception : ", e);
			}
            multipart.addBodyPart(attachmentBodyPart);
        }
		
		// 设置邮件内容
		message.setContent(multipart);
		// 发送
		Transport.send(message);
	}
	/**
	 * 群发邮件
	 * 
	 * @param recipient
	 *            收件人邮箱地址
	 * @param subject
	 *            邮件主题
	 * @param content
	 *            邮件内容
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void send(String recipients[], String subject, Object content, File attachment) throws AddressException, MessagingException {
		log.debug("send mail, recipient = "+recipients.toString()+", subject = "+subject+", content = "+content.toString()+" ");
		if(recipients[0]==null || recipients[0]==""||subject==null || subject==""||content==null || content=="")
			return;
		// 创建mime类型邮件
		final MimeMessage message = new MimeMessage(session);
		// 设置发信人
		try {
			message.setFrom(new InternetAddress(authenticator.getUserName(), senderDisplayName));
		} catch (UnsupportedEncodingException e) {
			log.error("sendmail exception : ", e);
		}

		// 设置收件人们
		final int num = recipients.length;
		InternetAddress[] addresses = new InternetAddress[num];
		for (int i = 0; i < num; i++) {
			addresses[i] = new InternetAddress(recipients[i]);
		}
		message.setRecipients(RecipientType.TO, addresses);
		// 设置主题
		message.setSubject(subject);
		
		 // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
        Multipart multipart = new MimeMultipart();
        
        // 添加邮件正文
        BodyPart contentPart = new MimeBodyPart();
        contentPart.setContent(content.toString(), "text/html;charset=UTF-8");
        multipart.addBodyPart(contentPart);
        
        // 添加附件的内容
        if (attachment != null) {
            BodyPart attachmentBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachment);
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            
            // 网上流传的解决文件名乱码的方法，其实用MimeUtility.encodeWord就可以很方便的搞定
            // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
            //sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
            //messageBodyPart.setFileName("=?GBK?B?" + enc.encode(attachment.getName().getBytes()) + "?=");
            
            //MimeUtility.encodeWord可以避免文件名乱码
            try {
				attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachment.getName()));
			} catch (UnsupportedEncodingException e) {
				log.error("sendmail exception : ", e);
			}
            multipart.addBodyPart(attachmentBodyPart);
        }
		
		// 设置邮件内容
		message.setContent(multipart);
		// 发送
		Transport.send(message);
	}
	/**
	 * 发送邮件
	 * 
	 * @param recipient
	 *            收件人邮箱地址
	 * @param subject
	 *            邮件主题
	 * @param content
	 *            邮件内容
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void send(String recipient, String subject, Object content) throws AddressException, MessagingException {
		log.debug(">>>>>>>>>>>>>>>send mail, recipient = "+recipient+", subject = "+subject+", content = "+content.toString()+"<<<<<<<<< ");
		if(recipient==null || recipient==""||subject==null || subject==""||content==null || content=="")
			return;
		// 创建mime类型邮件
		final MimeMessage message = new MimeMessage(session);
		// 设置发信人
		try {
			message.setFrom(new InternetAddress(authenticator.getUserName(), senderDisplayName));
		} catch (UnsupportedEncodingException e) {
			log.error("sendmail exception : ", e);
		}
		// 设置收件人
		message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
		// 设置主题
		message.setSubject(subject);
		// 设置邮件内容
		message.setContent(content.toString(), "text/html;charset=utf-8");
		// 发送
		Transport.send(message);
	}
	/**
	 * 群发邮件
	 * 
	 * @param recipient
	 *            收件人邮箱地址
	 * @param subject
	 *            邮件主题
	 * @param content
	 *            邮件内容
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void send(String recipients[], String subject, Object content) throws AddressException, MessagingException {
		log.debug("send mail, recipient = "+recipients.toString()+", subject = "+subject+", content = "+content.toString()+" ");
		if(recipients[0]==null || recipients[0]==""||subject==null || subject==""||content==null || content=="")
			return;
		// 创建mime类型邮件
		final MimeMessage message = new MimeMessage(session);
		// 设置发信人
		try {
			message.setFrom(new InternetAddress(authenticator.getUserName(), senderDisplayName));
		} catch (UnsupportedEncodingException e) {
			log.error("sendmail exception : ", e);
		}
		// 设置收件人
//		message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
		// 设置收件人们
		final int num = recipients.length;
		InternetAddress[] addresses = new InternetAddress[num];
		for (int i = 0; i < num; i++) {
			addresses[i] = new InternetAddress(recipients[i]);
		}
		message.setRecipients(RecipientType.TO, addresses);
		// 设置主题
		message.setSubject(subject);
		// 设置邮件内容
		message.setContent(content.toString(), "text/html;charset=utf-8");
		// 发送
		Transport.send(message);
	}
	/**
	 * 群发邮件
	 * 
	 * @param recipients
	 *            收件人们
	 * @param subject
	 *            主题
	 * @param content
	 *            内容
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void send(List<String> recipients, String subject, Object content)
			throws AddressException, MessagingException {

		if(recipients==null || recipients.isEmpty()||subject==null || subject==""||content==null || content=="")
			return;
		log.debug("send mail, recipient = "+recipients.toString()+", subject = "+subject+", content = "+content.toString()+" ");

		// 创建mime类型邮件
		final MimeMessage message = new MimeMessage(session);
		// 设置发信人
		message.setFrom(new InternetAddress(authenticator.getUserName()));
		// 设置收件人们
		final int num = recipients.size();
		InternetAddress[] addresses = new InternetAddress[num];
		for (int i = 0; i < num; i++) {
			addresses[i] = new InternetAddress(recipients.get(i));
		}
		message.setRecipients(RecipientType.TO, addresses);
		// 设置主题
		message.setSubject(subject);
		// 设置邮件内容
		message.setContent(content.toString(), "text/html;charset=utf-8");
		// 发送
		Transport.send(message);
	}

}
