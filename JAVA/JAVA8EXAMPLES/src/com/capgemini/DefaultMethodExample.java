package com.capgemini;

interface Left{

	int a = 10;
	
	//default method
	default public void m1(){
		System.out.println("Inside Left interface..");
	}; 
	
	//add static method
	static public void m2(){
		System.out.println("");
	}
}

interface Right{
	
	int a = 20;
	default public void m1(){
		System.out.println("Inside Right interface..");
	};
}

public class DefaultMethodExample implements Left,Right{

	public static void main(String[] args) {
		
		//calling static method from inteerface
		Left.m2();

	}


	//If we are having same default methods implementing same class then we need to override any one of them 
	@Override
	public void m1() {
		// TODO Auto-generated method stub
		Left.super.m1();//this is how defauult method get called..
		System.out.println("Inside DefaultMethodExample..");
	}


}
