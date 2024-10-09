package com.capgemini;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Internationalization {

	public static void main(String[] args) {
		
		ZoneId zone = ZoneId.systemDefault();
		System.out.println("my zone is :"+zone);
		
		ZoneId la = ZoneId.of("America/Los_Angeles");
		System.out.println("zone id of America/Los_Angeles :"+la);
		
		ZonedDateTime zdt = ZonedDateTime.now();
		System.out.println("ZonedDateTime.now :"+zdt);
		
		ZonedDateTime zdtla = ZonedDateTime.now(la);
		System.out.println("ZonedDateTime.now of  America/Los_Angeles:"+zdtla);
		
	}

}
