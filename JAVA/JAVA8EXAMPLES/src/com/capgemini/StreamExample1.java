package com.capgemini;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExample1 {

	public static void main(String[] args) {
		
		List<Employee> employees = new ArrayList<>();
		Employee e1 = new Employee("Nilesh",20000);
		Employee e2 = new Employee("Priyanka",50000);
		Employee e3 = new Employee("Snehal",80000);
		
		employees.add(e1);
		employees.add(e2);
		employees.add(e3);
		
		List<Employee> FilteredList = employees.stream().filter(employee -> employee.getSalary()>30000).collect(Collectors.toList());
		System.out.println(FilteredList);
	}

}

class Employee{
	
	String name;
	int salary;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	Employee(String name,int salary){
		this.name=name;
		this.salary=salary;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", salary=" + salary + "]";
	}

	
}
