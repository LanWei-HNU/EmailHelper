package com.lw.emailsender.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.lw.emailsender.model.ConfigPropertistBean;
import com.lw.emailsender.service.SendMailService;
import com.lw.emailsender.utils.Constant;
import com.lw.emailsender.view.LeftPanel;
import com.lw.emailsender.view.RightPanel;

/**
 * 界面组件事件监听器
 * @author LanWei-HNU
 * 2017年2月17日
 */
public class SysListener implements ActionListener{

	private LeftPanel leftPanel;
	private RightPanel rightPanel;
	private SendMailService sendMailService;
	
	public SysListener(LeftPanel leftPanel, RightPanel rightPanel,SendMailService sendMailService) {
		super();
		this.leftPanel = leftPanel;
		this.rightPanel = rightPanel;
		this.sendMailService = sendMailService;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		if(Constant.BUTTON_VERIFY_CMD.equals(e.getActionCommand())){
			//邮箱认证
			ConfigPropertistBean cfProBean = bulidCfproBean(leftPanel);
			if(sendMailService.checkAccount(cfProBean)){
				//未完成验证
				System.out.println("验证成功");
			}else
				System.out.println("验证失败");
		}else if(Constant.BUTTON_CHOOSEFILE_CMD.equals(e.getActionCommand())){
			//选择文件
			
		}else if(Constant.BUTTON_SEND_CMD.equals(e.getActionCommand())){
			//发送事件
		}
	}
	
	private ConfigPropertistBean bulidCfproBean(LeftPanel leftPanel){
		ConfigPropertistBean cfProBean = ConfigPropertistBean.getInstance();
		cfProBean.setEmail(leftPanel.getEmailAbbrField().getText());
		cfProBean.setHostName(leftPanel.getHostNameField().getText());
		cfProBean.setPassword(String.valueOf(leftPanel.getEmailPwdField().getPassword()));
		cfProBean.setProtocol((String)leftPanel.getProtocolComBox().getSelectedItem());
		cfProBean.setDisplayName(leftPanel.getDisplayNameField().getText());
		return cfProBean;
	}

}
