package net.guides.springboot2.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "patient")
public class Patient {

	private long id;
	private String firstName;
	//private String lastName;
	private String contact;
	
	public Patient() {
		
	}
	
	public Patient(String firstName, String contact) {
		this.firstName = firstName;
		//this.lastName = lastName;
		this.contact = contact;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "Patientname", nullable = false)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/*
	@Column(name = "last_name", nullable = false)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	*/

	@Column(name = "PatientContactNo", nullable = false)
	public String getEmailId() {
		return contact;
	}
	public void setEmailId(String contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", Patientname=" + firstName + ", PatientContactNo=" + contact
				+ "]";
	}
	
}
