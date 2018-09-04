package com.learn.domain;

import org.apache.ibatis.type.Alias;

@Alias("PersonDO")
public class PersonDO {
	
	private Integer id;
	private String name;
	private Integer age;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	public static void main(String[] args) {
		System.out.println(new Integer(null));
	}

}
