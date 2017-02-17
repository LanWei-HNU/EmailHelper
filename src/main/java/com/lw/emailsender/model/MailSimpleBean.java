package com.lw.emailsender.model;

/**
 * 邮件发送bean
 * @author LanWei-HNU
 *
 */
public class MailSimpleBean {
	//收件人
	private String recipient;
	//邮件主题
	private String subject;
	//邮件内容
	private Object content;
	//发送状态
	private String statusDesc;
	//收件人姓名
	private String recName;
	
	public MailSimpleBean(String recipient, String subject, Object content,String recName) {
		super();
		this.recipient = recipient;
		this.subject = subject;
		this.content = content;
		this.recName = recName;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getRecName() {
		return recName;
	}
	public void setRecName(String recName) {
		this.recName = recName;
	}
	
	
}
