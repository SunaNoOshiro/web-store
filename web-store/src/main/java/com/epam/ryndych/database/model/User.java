package com.epam.ryndych.database.model;

import java.sql.Date;

public class User {
	private int id;
	private String login;
	private String password;
	private String firstName;
	private String lastName;
	private String country;
	private String email;
	private Date birthday;
	private Date registration;
	private Sex sex;
	private Permission permission = Permission.USER;
	private String phoneNumber;
	private boolean blackList = false;
	private String address;
	private String state;
	private String city;

	enum Permission {
		ADMIN, USER;
	}
	enum Sex {
		MALE, FEMALE;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if(id <= 0){
			System.out.println("User's id is <= 0 !!! It is can't be set ");
			return;
		}
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		if(login == null || login.equals("")){
			System.out.println("User's login is null !!! It is can't be set ");
			return;
		}
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if(password == null || password.equals("")){
			System.out.println("User's password is null !!! It is can't be set ");
			return;
		}
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String fname) {
		if(fname == null || fname.equals("")){
			System.out.println("User's fname is null !!! It is can't be set ");
			return;
		}
		this.firstName = fname;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lname) {
		if(lname == null){
			System.out.println("User's lname is null !!! It is can't be set ");
			return;
		}
		this.lastName = lname;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		if(country == null){
			System.out.println("User's country is null !!! It is can't be set ");
			return;
		}
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if(email == null){
			System.out.println("User's email is null !!! It is can't be set ");
			return;
		}
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		if(birthday == null){
			System.out.println("User's birthday is null !!! It is can't be set ");
			return;
		}
		this.birthday = birthday;
	}
	
	public Date getRegistration() {
		return registration;
	}

	public void setRegistration(Date registration) {
		if(registration == null){
			System.out.println("User's registration is null !!! It is can't be set ");
			return;
		}
		this.registration = registration;
	}
	public String getSex() {
		return sex.toString();
	}

	public void setSex(String sex) {
		if(sex == null){
			System.out.println("User's sex is null !!! It is can't be set ");
			return;
		}
		
		try {
			this.sex = Sex.valueOf(sex.toUpperCase());
		} catch (IllegalArgumentException e) {
			System.out.println("Incorrect value of 'sex' ");
		}
	}

	public String getPermission() {
		return permission.toString();
	}

	public void setPermission(String permission) {
		if(permission == null){
			System.out.println("User permission is null !!! It is can't be set ");
			return;
		}
			
		try {
			this.permission = Permission.valueOf(permission.toUpperCase());
		} catch (IllegalArgumentException e) {
			System.out.println("Incorrect value of 'permission' ");
		}
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		if(phoneNumber == null ){
			System.out.println("User phoneNumber is null !!! It is can't be set ");
			return;
		}
		this.phoneNumber = phoneNumber;
	}
	
	public boolean isBlackList() {
		return blackList;
	}

	public void setBlackList(boolean blackList) {
		this.blackList = blackList;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ userId = " + id);
		sb.append("; firstName = " + firstName);
		sb.append("; lastNme = " + lastName);
		sb.append("; login = " + login);
		sb.append("; country = " + country);
		sb.append("; birthday : " + birthday);
		sb.append("; sex : " + sex.toString());
		sb.append("; permission : " + permission.toString());
		sb.append("; email : " + email);
		sb.append("; phoneNumber : " + phoneNumber);
		return sb.toString();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
