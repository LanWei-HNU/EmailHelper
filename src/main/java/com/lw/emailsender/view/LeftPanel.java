package com.lw.emailsender.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.lw.emailsender.utils.Constant;

public class LeftPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final Font FONT_A = new Font("宋体", 1, 15);
	private static final Font FONT_B = new Font("宋体", 3, 10);

	private static final Dimension PANEL_SIZE = new Dimension(430, 0);
	private static final Dimension FIELD_SIZE_A = new Dimension(180, 25);
	private static final Dimension FIELD_SIZE_B = new Dimension(173, 25);
	private static final Dimension FIELD_SIZE_C = new Dimension(250, 25);
	private static final Dimension FIELD_SIZE_D = new Dimension(20, 20);

	private JLabel hostNameLabel;
	private JLabel protocolLabel;
	private JLabel emailAbbrLabel;
	private JLabel emailPwdLabel;
	private JLabel subjectLabel;
	private JLabel displayNameLabel;
	private JLabel filePathLabel;
	private JLabel tipLabel;
	private JLabel nameColLabel;
	private JLabel emailColLabel;

	private JComboBox<String> protocolComBox;

	private JTextField hostNameField;
	private JTextField emailAbbrField;
	private JPasswordField emailPwdField;
	private JTextField subjectField;
	private JTextField displayNameField;
	private JTextField nameColField;
	private JTextField emailColField;

	private JButton verifyEmailBtn;
	private JButton selectFileBtn;
	private JButton sendBtn;

	
	public LeftPanel() {
		super();
		this.setPreferredSize(PANEL_SIZE);

		FlowLayout FLOW_LEFT = new FlowLayout(FlowLayout.LEFT);
		FLOW_LEFT.setHgap(15); // 组件水平间距
		FLOW_LEFT.setVgap(20); // 组件垂直间距
		this.setLayout(FLOW_LEFT);

		// 邮箱信息
		this.hostNameLabel = new JLabel("主机地址 ");
		this.hostNameLabel.setFont(FONT_A);
		this.hostNameField = new JTextField();
		this.hostNameField.setPreferredSize(FIELD_SIZE_A);
		this.add(hostNameLabel);
		this.add(hostNameField);

		this.protocolLabel = new JLabel("协议");
		this.protocolLabel.setFont(FONT_A);
		this.protocolComBox = new JComboBox<String>(new String[] { "smtp", "pop3" });
		this.add(protocolLabel);
		this.add(protocolComBox);

		this.emailAbbrLabel = new JLabel("邮箱账号 ");
		this.emailAbbrLabel.setFont(FONT_A);
		this.emailAbbrField = new JTextField();
		this.emailAbbrField.setPreferredSize(FIELD_SIZE_A);
		this.add(emailAbbrLabel);
		this.add(emailAbbrField);

		JLabel labelH1 = new JLabel("                "); // 换行
		this.add(labelH1);

		this.emailPwdLabel = new JLabel("邮箱密码 ");
		this.emailPwdLabel.setFont(FONT_A);
		this.emailPwdField = new JPasswordField();
		this.emailPwdField.setPreferredSize(FIELD_SIZE_A);
		this.add(emailPwdLabel);
		this.add(emailPwdField);

		this.verifyEmailBtn = new JButton(Constant.BUTTON_VERIFY_CMD);
		this.add(verifyEmailBtn);

		this.subjectLabel = new JLabel("邮件标题 ");
		this.subjectLabel.setFont(FONT_A);
		this.subjectField = new JTextField();
		this.subjectField.setPreferredSize(FIELD_SIZE_A);
		this.add(subjectLabel);
		this.add(subjectField);

		JLabel labelH2 = new JLabel("                ");
		this.add(labelH2);

		this.displayNameLabel = new JLabel("发件人名称");
		this.displayNameLabel.setFont(FONT_A);
		this.displayNameField = new JTextField();
		this.displayNameField.setPreferredSize(FIELD_SIZE_B);
		this.add(displayNameLabel);
		this.add(displayNameField);

		JLabel labelH3 = new JLabel("                ");
		this.add(labelH3);

		// 添加选择文件按钮
		this.selectFileBtn = new JButton(Constant.BUTTON_CHOOSEFILE_CMD);
		this.filePathLabel = new JLabel(Constant.LABEL_FILEPATH_TXT);
		this.filePathLabel.setFont(FONT_B);
		this.filePathLabel.setPreferredSize(FIELD_SIZE_C);
		;
		this.add(selectFileBtn);
		this.add(filePathLabel);

		// 添加发送按钮
		this.sendBtn = new JButton(Constant.BUTTON_SEND_CMD);
		this.add(sendBtn);

		// 等待提示
		this.tipLabel = new JLabel("");
		this.tipLabel.setFont(FONT_B);
		this.tipLabel.setPreferredSize(FIELD_SIZE_C);
		this.add(tipLabel);

		this.nameColLabel = new JLabel("姓名所在列");
		this.nameColLabel.setFont(FONT_A);
		this.nameColField = new JTextField();
		this.nameColField.setPreferredSize(FIELD_SIZE_D);
		this.add(nameColLabel);
		this.add(nameColField);

		JLabel labelH4 = new JLabel("                ");
		this.add(labelH4);

		this.emailColLabel = new JLabel("email所在列");
		this.emailColLabel.setFont(FONT_A);
		this.emailColField = new JTextField();
		this.emailColField.setPreferredSize(FIELD_SIZE_D);
		this.add(emailColLabel);
		this.add(emailColField);

		System.out.println("左面板构造完成! ");
	}
	
	public JLabel getHostNameLabel() {
		return hostNameLabel;
	}
	public JLabel getProtocolLabel() {
		return protocolLabel;
	}
	public JLabel getEmailAbbrLabel() {
		return emailAbbrLabel;
	}
	public JLabel getEmailPwdLabel() {
		return emailPwdLabel;
	}
	public JLabel getSubjectLabel() {
		return subjectLabel;
	}
	public JLabel getDisplayNameLabel() {
		return displayNameLabel;
	}
	public JLabel getFilePathLabel() {
		return filePathLabel;
	}
	public JLabel getTipLabel() {
		return tipLabel;
	}
	public JLabel getNameColLabel() {
		return nameColLabel;
	}
	public JLabel getEmailColLabel() {
		return emailColLabel;
	}
	public JComboBox<String> getProtocolComBox() {
		return protocolComBox;
	}
	public JTextField getHostNameField() {
		return hostNameField;
	}
	public JTextField getEmailAbbrField() {
		return emailAbbrField;
	}
	public JPasswordField getEmailPwdField() {
		return emailPwdField;
	}
	public JTextField getSubjectField() {
		return subjectField;
	}
	public JTextField getDisplayNameField() {
		return displayNameField;
	}
	public JTextField getNameColField() {
		return nameColField;
	}
	public JTextField getEmailColField() {
		return emailColField;
	}
	public JButton getVerifyEmailBtn() {
		return verifyEmailBtn;
	}
	public JButton getSelectFileBtn() {
		return selectFileBtn;
	}
	public JButton getSendBtn() {
		return sendBtn;
	}

}
