package com.capgemini;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamExample {

	public static void main(String[] args) {
		
		List<String> strings = Arrays.asList("abc","","bc","hgh","","");
		List<String> filteredList = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
		System.out.println("filteredList :"+filteredList);
		
		HashSet<String> set=new HashSet<String>();
		set.add("abc");
		set.add("");
		set.add("hjdfjh");
		set.add("bvdsf");
		set.add("");
		Set<String> filteredSet = set.stream().filter(s -> !s.isEmpty()).collect(Collectors.toSet());
		System.out.println("filteredSet :"+filteredSet);
		
		HashMap<Integer,String> map = new HashMap<Integer,String>();
		map.put(1,"abc");
		map.put(2,"");
		map.put(3,"bgah");
		map.put(4,"xyzcd");
		map.put(5,"");
		//how to work with Map
		List<Entry<Integer, String>> filteredMap = map.entrySet().stream().filter(Entry->!Entry.getValue().isEmpty()).collect(Collectors.toList());
		System.out.println("Filtered Map :"+filteredMap);
	}

}
