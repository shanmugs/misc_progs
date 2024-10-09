package com.capgemini;

import java.util.StringJoiner;

public class StringJoinerExample {

	public static void main(String[] args) {
		
		StringJoiner sj = new StringJoiner(",");
		sj.add("Pune");
		sj.add("Kolhapur");
		sj.add("Mumbai");
		System.out.println("Result"+sj.toString());
		
		StringJoiner sj1 = new StringJoiner("/","prefix-","-suffix");
		sj1.add("14");
		sj1.add("10");
		sj1.add("1993");
		System.out.println("Result"+sj1.toString());
	}

}
