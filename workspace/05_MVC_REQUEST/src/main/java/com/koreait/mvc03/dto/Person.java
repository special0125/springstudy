package com.koreait.mvc03.dto;

import java.util.List;

public class Person {

	private String name;
	private int age;
	private List<String> hobbies;
	
	public Person() {}
	public Person(String name, int age, List<String> hobbies) {
		super();
		this.name = name;
		this.age = age;
		this.hobbies = hobbies;
	}
	
	
	public void info() {
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public List<String> getHobbies() {
		return hobbies;
	}
	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}
	
	
	
	
	
	
}
