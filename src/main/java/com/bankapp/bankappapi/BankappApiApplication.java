package com.bankapp.bankappapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)  
public class BankappApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankappApiApplication.class, args);
	}

}