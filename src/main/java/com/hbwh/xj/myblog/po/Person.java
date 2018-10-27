package com.hbwh.xj.myblog.po;

import javax.persistence.Column;
import javax.persistence.Id;

import tk.mybatis.mapper.annotation.KeySql;

public class Person {

	@Id
	@KeySql(useGeneratedKeys = true)
	private Long id;
	private String name;
	private Integer age;
	private String job;
	private String city;
	
	@Column(name="`desc`")
	private String desc;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public void setAge(int age) {
		this.age = age;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + ", job=" + job + ", city=" + city + ", desc="
				+ desc + "]";
	}


}
