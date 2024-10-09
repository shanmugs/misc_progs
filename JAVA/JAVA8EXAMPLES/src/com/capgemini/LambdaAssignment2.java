package com.capgemini;


interface Math{
	
	int square(int b);
}

public class LambdaAssignment2 {

	public static void main(String[] args) {
		
		Math m = b -> b*b;
		System.out.println("Square of 5 "+m.square(5));
	}

}
