package com.lw.emailsender.view;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private LeftPanel leftPanel;
	private RightPanel rightPanel;
	private ActionListener sysListener;

	public MainFrame(LeftPanel leftPanel,RightPanel rightPanel,ActionListener sysListener) throws HeadlessException {
		super();
		this.leftPanel = leftPanel;
		this.rightPanel = rightPanel;
		this.sysListener=sysListener;

		this.setSize(430 + 600, 500);								// 设置窗体大小
		this.setTitle("群发邮件助手");									// 设置窗体标题
		this.setLocationRelativeTo(null);							// 窗口至于屏幕中央
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	// 设置关闭窗体是的操作
		this.setResizable(false);									// 设置禁止改变窗体大小
		this.setLayout(new BorderLayout());							// JFrame默认的布局是边框布局
		// 关闭界面操作
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
//				try {
//					//closeWindow();
//				} catch (IOException e1) {
//					JOptionPane.showMessageDialog(null, "保存信息失败", "错误", JOptionPane.ERROR_MESSAGE);
//				}
				System.exit(0);
			}
		});
	}

	public void showUI() {
		//添加面板
		this.add("West", leftPanel);
		this.add("East",rightPanel);
		//绑定监听器
		this.leftPanel.getVerifyEmailBtn().addActionListener(sysListener);
		this.leftPanel.getSelectFileBtn().addActionListener(sysListener);
		this.leftPanel.getSendBtn().addActionListener(sysListener);
		
		// 设置窗体可见
		this.setVisible(true);
		
		
	}

}
