package com.tendelfc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.support.GenericXmlApplicationContext;

@SpringBootApplication
public class TendelfcApplication {

	public static void main(String[] args) {
		System.out.println("DATABASE_URL: " + System.getenv("DATABASE_URL"));
		SpringApplication.run(TendelfcApplication.class, args);
	}

}
