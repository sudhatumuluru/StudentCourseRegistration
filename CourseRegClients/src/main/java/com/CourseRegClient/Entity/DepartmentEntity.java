package com.CourseRegClient.Entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Group 4
 * @version 1.0
 * @name : Department entity
 * @description : This class defines all the attributes used by the class Department
 * @since 2014-09-27
 */
@XmlRootElement
//@XmlAccessorType(XmlAccessType.FIELD)
public class DepartmentEntity {
		private String DeptID;
		private String DeptName;
		private String Course;
		public DepartmentEntity() {
		}

		public String getDeptID() {
			return this.DeptID;
		}

		public void setDeptID(String DeptID) {
			this.DeptID = DeptID;
		}

		public String getDeptName() {
			return this.DeptName;
		}

		public void setDeptName(String DeptName) {
			this.DeptName = DeptName;
		}
		
		public String getCourse() {
			return this.Course;
		}

		public void setCourse(String Course) {
			this.Course = Course;
		}	
		
}
