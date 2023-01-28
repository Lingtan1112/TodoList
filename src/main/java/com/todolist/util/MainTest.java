package com.todolist.util;

public class MainTest {

	
	
	public static void main(String[] args) {
		
		FunctionInterfaceDemo d = (number) -> 	
		{
		
			if(number%2==0) {
				return "Number is Even";
			}else {
				return "Number is Odd";
			}
				
		};
		String name = d.demo(2);
		
		System.out.println(name);
	
	}
	

}
