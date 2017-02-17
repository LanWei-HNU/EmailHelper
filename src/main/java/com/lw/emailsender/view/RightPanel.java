package com.lw.emailsender.view;

import java.awt.Dimension;
import java.awt.Font;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class RightPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final Dimension PANEL_SIZE = new Dimension(600,0);
	private static final Font font3 = new Font("隶书",1,14);
	
	
	
	private JTextArea logTextArea;
	private JScrollPane jsp;
	
	public RightPanel() {
		super();
		this.setPreferredSize(PANEL_SIZE);

		
		logTextArea = new JTextArea("******************************发送日志*************************\r\n"
				+ "日期："+new Date()+"\r\n \r\n");
		logTextArea.setLineWrap(true);//自动换行
		logTextArea.setEditable(false);
		logTextArea.setFont(font3);
		this.jsp = new JScrollPane(logTextArea);
		jsp.setPreferredSize(new Dimension(500,450));

		//分别设置水平和垂直滚动条总是出现 
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		this.add(jsp);
		
		System.out.println("右面板构造完成! ");
	}
	
	
}
