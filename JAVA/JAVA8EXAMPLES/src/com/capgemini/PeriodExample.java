package com.capgemini;

import java.time.LocalDate;
import java.time.Period;

public class PeriodExample {

	public static void main(String[] args) {
		
		LocalDate birthday = LocalDate.of(1993, 3, 15);
		LocalDate today = LocalDate.now();
		Period period = Period.between(birthday, today);
		System.out.println("my Age is :"+period.getYears());
	}

}
