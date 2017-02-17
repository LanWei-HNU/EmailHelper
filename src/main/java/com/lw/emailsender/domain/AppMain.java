package com.lw.emailsender.domain;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lw.emailsender.HelloWorld;

public class AppMain {

	public static void main(String[] args) {
		/*
		 * ��ʹ��springde ��� //����һ��HelloWorld���� HelloWorld helloworld=new
		 * HelloWorld(); //Ϊ����ֵ helloworld.setName("baixl"); //����hello��������
		 */
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		HelloWorld helloworld = (HelloWorld) ctx.getBean("helloWorld");
		helloworld.sayHello();
	}

}
