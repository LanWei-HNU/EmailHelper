package com.lw.emailsender.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.mail.Store;
import javax.mail.Transport;

import org.springframework.util.StringUtils;

import com.lw.emailsender.model.ConfigPropertistBean;
import com.lw.emailsender.utils.mail.SimpleMailSender;

public class SendMailServiceImpl implements SendMailService{

	@Override
	public boolean checkAccount(ConfigPropertistBean cfProBean) {
		if(!isDataAlready(cfProBean))
			return false;
		//初始化账户信息
		SimpleMailSender.initEmailAccount(cfProBean);
		Transport transport;
		Store store;
		try {
			if("smtp".equals(cfProBean.getProtocol())){
				System.out.println("smtp");
				transport = SimpleMailSender.getInstance().getSession().getTransport();
				transport.connect(cfProBean.getHostName(), cfProBean.getEmail(), cfProBean.getPassword());
			}else if("pop3".equals(cfProBean.getProtocol())){
				store = SimpleMailSender.getInstance().getSession().getStore();
				store.connect(cfProBean.getHostName(), cfProBean.getEmail(), cfProBean.getPassword());
			}else{
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	private boolean isDataAlready(ConfigPropertistBean cfProBean){
		if(StringUtils.isEmpty(cfProBean.getEmail())
				||StringUtils.isEmpty(cfProBean.getHostName())
				||StringUtils.isEmpty(cfProBean.getPassword())
				||StringUtils.isEmpty(cfProBean.getProtocol())){
			return false;
		}
		return true;
	}

	@Override
	public String[][] readTemplateFile(String filePath) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendMail(String[][] allData, String subject) {
		// TODO Auto-generated method stub
		
	}

}
