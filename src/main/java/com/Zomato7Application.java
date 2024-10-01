package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.email.EmailSenderService;

@SpringBootApplication
public class Zomato7Application {
//
//	@Autowired
//	private EmailSenderService  service;
//	
	public static void main(String[] args) {
		SpringApplication.run(Zomato7Application.class, args);
	}
//	
//	@EventListener(ApplicationReadyEvent.class)
//
//	public void sendEmail() {
//		
//		service.sendEmail("naitikj1011@gmail.com", "Java", "Let's Creack the java");
//	}

}
