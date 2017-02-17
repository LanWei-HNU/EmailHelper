package com.lw.emailsender.domain;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lw.emailsender.HelloWorld;

public class AppMain {

	public static void main(String[] args) {
		/*
		 * 不使用springde 情况 //创建一个HelloWorld对象 HelloWorld helloworld=new
		 * HelloWorld(); //为对象赋值 helloworld.setName("baixl"); //调用hello（）方法
		 */
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		HelloWorld helloworld = (HelloWorld) ctx.getBean("helloWorld");
		helloworld.sayHello();
	}

}
