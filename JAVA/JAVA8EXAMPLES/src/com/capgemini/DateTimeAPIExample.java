package com.capgemini;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTimeAPIExample {

	public static void main(String[] args) {
		
		//Class LocalDate methods
		LocalDate localDate = LocalDate.now();
		System.out.println("localDate.now :"+localDate);
		int dd = localDate.getDayOfMonth();
		int mm = localDate.getMonthValue();
		int yy = localDate.getYear();
		System.out.println("day from localDate "+dd);
		System.out.println("month from localDate "+mm);
		System.out.println("year from localDate "+yy);
		
		//class LocalTime methods
		System.out.println("*********************************************************");
		LocalTime localTime = LocalTime.now();
		System.out.println("LocalTime.now "+localTime);
		int hr = localTime.getHour();
		int minute = localTime.getMinute();
		int second = localTime.getSecond();
		int nano = localTime.getNano();
		System.out.println("hour from LocalTime :"+hr);
		System.out.println("minute from LocalTime :"+minute);
		System.out.println("second from LocalTime :"+second);
		System.out.println("nano second from LocalTime :"+nano);
		
		//class LocalDateTime methods
		System.out.println("*********************************************************");
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println("LocalDateTime.now :"+ldt);
		
		
		
	}

}
