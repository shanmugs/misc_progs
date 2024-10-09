package com.capgemini;

interface Maths{
	
	int operation(int a,int b);
}


public class LambdaTest1 {
	
	public static void main(String []args){
		
		
		/******* with Annonymous class   *********/ 
		Maths m1 = new Maths(){
		
			@Override
			public int operation(int a, int b) {
				
				
				return a+b;
			}
			
		};
		System.out.println("with Annonymous class :- "+m1.operation(8,8));
		
		
		/************ with Lambda Expression ***********/
		Maths m2 = (int a,int b) -> {
			return a+b;
		};
		System.out.println("with Lambda Expression :- "+m2.operation(4, 4));
		
		
		
		/************ with Lambda Expression More Abstract Form***********/
		Maths m3 = (a,b) -> a+b;
		System.out.println("with Lambda Expression More Abstract Form :- "+m3.operation(9, 9));
	}

}
