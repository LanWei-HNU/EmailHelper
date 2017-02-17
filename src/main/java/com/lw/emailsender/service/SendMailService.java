package com.lw.emailsender.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.lw.emailsender.model.ConfigPropertistBean;



/**
 * 与发送邮件相关的业务在此处实现
 * @author LanWei
 *
 */
public interface SendMailService {
	/**
	 * 验证邮箱账户
	 * @param cfProBean	配置信息
	 * @return
	 */
	public boolean checkAccount(ConfigPropertistBean cfProBean);
	
	/**
	 * 读取模板文件
	 * @param filePath
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public String[][] readTemplateFile(String filePath) throws FileNotFoundException, IOException ;
	
	/**
	 * 发送邮件
	 * @param allData
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void sendMail(String[][] allData,String subject);
}
