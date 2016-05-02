package com.estsoft.aoptest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("config/applicationContext.xml");

		// ProductService productService =
		//ProductService productService =  (ProductService)applicationContext.getBean("productService"); //와 밑에 것은 동일함 !
		ProductService productService = applicationContext.getBean(ProductService.class);
		ProductVo vo = productService.findProduct("camera");
		//app에서 부르니깐 맨 마지막에 출력될 것임
		//sysout은 service call이 아니니깐 around에서 ProductVo return이 없으면 vo는 null값이 된다.
		System.out.println(vo);
		// 종료
		((ConfigurableApplicationContext) applicationContext).close();
	}
}
