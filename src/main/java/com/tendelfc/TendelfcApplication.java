package com.tendelfc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.support.GenericXmlApplicationContext;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class TendelfcApplication {

	public static void main(String[] args) {
		SpringApplication.run(TendelfcApplication.class, args);
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");
	}

}
