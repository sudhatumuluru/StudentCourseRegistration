package com.CourseRegClient.Entity;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Group 4
 * @version 1.0
 * @name : Login entity
 * @description : This class defines all the attributes used by the class login
 * @since 2015-09-27
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LoginEntity {
	
	private int id;

	private String emailID;

	private Timestamp lastAccessTime;

	private String password;

	private String role;

	
	//private Administrator administrator;
	
	//private Instructor instructor;

	//private Student student;

	public LoginEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmailID() {
		return this.emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public Timestamp getLastAccessTime() {
		return this.lastAccessTime;
	}

	public void setLastAccessTime(Timestamp lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	/*public Administrator getAdministrator() {
		return this.administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public Instructor getInstructor() {
		return this.instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	} */

}
