package com.lw.emailsender.domain;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lw.emailsender.HelloWorld;
import com.lw.emailsender.view.MainFrame;

public class AppMain {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		MainFrame helloworld = (MainFrame) ctx.getBean("mainFrame");
		helloworld.showUI();
	}

}
