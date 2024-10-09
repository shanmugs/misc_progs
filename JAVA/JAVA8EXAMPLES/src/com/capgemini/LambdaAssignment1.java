package com.capgemini;

public class LambdaAssignment1 {

	public static void main(String[] args) {
		
		/********* Implementing Runnable interface with Lambda Expression **********/
		
		Runnable r = new Runnable(){

			@Override
			public void run() {
				
				System.out.println("Running 1 st thread..");
				
			}
			
		};
		r.run();

		
		Runnable r1 = ()-> System.out.println("Running 2 nd thread ..");
		r1.run();
	}

}
