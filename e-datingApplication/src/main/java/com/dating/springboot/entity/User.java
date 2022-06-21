package com.dating.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	private String username;
	@Column(nullable=false)
	private String password;
	@Column(nullable=false)
	private Integer age;
	@Column(nullable=false)
	private Long phoneNumber;
	@Column(nullable=false)
	private String gender;
	@Column(nullable=false)
	private String email;
	@Column(nullable=false)
	private String city;
	@Column(nullable=false)
	private String country;

	public User() {
	}

	public User(Long userId,String name, String username,String password, Integer age, Long phoneNumber, String gender, String email, String city,
			String country) {
		super();
		this.userId = userId;
		this.name = name;
		this.username = username;
		this.password = password;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.email = email;
		this.city = city;
		this.country = country;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	
}
