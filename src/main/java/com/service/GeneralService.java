package com.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class GeneralService {
	  public int generateSixDigitNumber() {
	        Random random = new Random();
	        int randomNumber = random.nextInt(900000) + 100000; // Range: 100000 to 999999
	        return randomNumber;
	    }
}
