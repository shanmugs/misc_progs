package com.capgemini;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaceExample {

	public static void main(String[] args) {
		
		/********** Predicate Example  **********/
		Predicate<Integer> predicate = x ->{
			if(x>0)
				return true;
			else
				return false;
		};
		System.out.println(predicate.test(40));
		
		
		/*********** Consumer Example ***********/
		Consumer<String> consumer = s -> System.out.println(s);
		consumer.accept("Hello!!!!");
		
		
		/********** Function Example ***********/
		Function<Integer,Integer> function = i->i*i;
		function.apply(5);
		
		
		/********* Supplier Example ***********/
		Supplier<String> supplier =  () -> "Hello I am Priyanka";
		System.out.println(supplier.get());
	}

}
