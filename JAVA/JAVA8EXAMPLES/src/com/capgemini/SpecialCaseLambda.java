package com.capgemini;

interface Intfr{
	public void m1();
}

public class SpecialCaseLambda {


	int x = 100;
		
	public void m2(){
		
		int y = 20;
		Intfr i = () ->{
			System.out.println("x = "+x);
			x=200;
			System.out.println("x = "+x);
			//y=60; //Local variable y defined in an enclosing scope must be final
			// local variables referenced from a lambda expression must be final
		};
		i.m1();
		System.out.println("y = "+y);
		y=80;//we can modify local variable value outside lambda ex.
		System.out.println("y ="+y);
		
	}

	
	public static void main(String[] args) {
			
		SpecialCaseLambda s = new SpecialCaseLambda();
		s.m2();
	}

}
