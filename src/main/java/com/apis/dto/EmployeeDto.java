package com.apis.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EmployeeDto {

	
	@NotBlank
	private Date birthDate;
	
	@NotBlank
	@Size(min = 2,max = 50)
	private String firstName;
	
	@NotBlank
	@Size(min = 2,max = 50)
	private String lastName;
	
	@NotBlank
	@Size(min=1,max=1)
	private String gender;
	
	@NotBlank
	private Date hireDate;

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	
	
}
