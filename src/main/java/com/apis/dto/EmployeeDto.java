package com.apis.dto;

import java.util.Date;
import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


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

	@Override
	public String toString() {
		return "EmployeeDto [birthDate=" + birthDate + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", gender=" + gender + ", hireDate=" + hireDate + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthDate, firstName, gender, hireDate, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeDto other = (EmployeeDto) obj;
		return Objects.equals(birthDate, other.birthDate) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(gender, other.gender) && Objects.equals(hireDate, other.hireDate)
				&& Objects.equals(lastName, other.lastName);
	}
	
	
	
	
}
