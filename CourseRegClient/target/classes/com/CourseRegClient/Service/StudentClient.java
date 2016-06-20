package com.CourseRegClient.Service;

import java.util.Scanner;

import com.CourseRegClient.Entity.StudentEntity;
import com.google.gson.Gson;
import com.sun.jersey.api.client.*;
import com.CourseRegClient.Validation.*;
/**
 * Root resource
 */
public class StudentClient {

	Scanner sc = new Scanner(System.in);
	StudentEntity s = new StudentEntity();
	Client client = Client.create();
	ClientResponse responseEntity;
	WebResource webResource;
	String etag = "";
	ValidateInput validObj;
Gson gson = new Gson();

/**
 * Method calling HTTP GET requests for all students. Method uses
 * "Last Modified" header. If the response is not modified, Response status
 * = 304, If the modified data has been successfully read, Response status =
 * 200. Else appropriate error message is displayed.
 */
public void getAllStudents() {
	
	WebResource webResource = client.resource("https://localhost:8443/CourseReg/v1/students/allstudents");
	ClientResponse responseEntity = webResource.accept("application/json").get(ClientResponse.class);
	
	String lastModifiedTime = responseEntity.getHeaders().getFirst("Last-modified");
	webResource.header("If-Modified-Since", lastModifiedTime);
	
	responseEntity = webResource.accept("application/json").get(ClientResponse.class);
	
	if (responseEntity.getStatus() == 304) {
		System.out.println("Data is not modified");
	} else if (responseEntity.getStatus() == 200) {
		System.out.println("Modified data recieved!!!");
	}
	else{
		throw new RuntimeException("Operation Failed!!!. Please try again");
	}
		
	String output = responseEntity.getEntity(String.class);
	displayStudentList(output);
}

/**
 * Method calling HTTP PUT requests to update student data. Method uses
 * "If-match and Data modified since" header. 
 * If ETag and Last modified value doesn't match, Response status =
 * 412, If data has been successfully updated, Response status = 200. Else
 * appropriate error message is displayed.
 */
public void updateStudent() {

	String[] input = new String[2];
	System.out.println("Enter Student ID :");
	input[0] = validObj.getValidStringInput();
	System.out.println("Enter the field you would like to update :");
	System.out.println("1.Lastname \n 2. Firstname \n 3. DateOfBirth \n 4. Gender \n 5. ContactNo \n 6. Address \n");
	
	
	System.out.println("Enter New Last Name :");
	input[1] = validObj.getValidStringInput();

	String inputStr = "{\"studentID\":\"" + input[0] + "\",\"lastName\":\"" + input[1] + "\"}";

	webResource = client.resource("http://localhost:8080/CourseReg/v1/students/studentupdate");
	responseEntity = webResource.type("application/json").put(ClientResponse.class, inputStr);

	String lastModifiedTime = responseEntity.getHeaders().getFirst("Last-modified");
	etag = responseEntity.getHeaders().getFirst("ETag");
	
	webResource.header("If-Match", etag);
	webResource.header("If-Unmodified-Since", lastModifiedTime);
	
	responseEntity = webResource.type("application/json").put(ClientResponse.class, inputStr);

	if (responseEntity.getStatus() != 200) {
		throw new RuntimeException("Operation Failed!!!. Please try again");
	} else {
		System.out.println("Last name upated successfully!!!");
	}
}

/**
 * Method calling HTTP POST requests. The method accepts student details
 * from user and inserts it in student Table. Batch insertion operation is
 * implemented. If Response status = 200, data has been successfully
 * updated. Else appropriate error message is displayed.
 */
public void batchAdd() {
	System.out.println("Enter Number of students you want to add : ");
	int noOfStudents = sc.nextInt();

	for (int i = 0; i < noOfStudents; i++) {
		String[] input = new String[8];

		System.out.println("Enter Student Details for" + (i + 1) + "student");
		System.out.println("\n*******************************************************");
		System.out.println("Enter First Name: ");
		input[0] = validObj.getValidStringInput();
		System.out.println("Enter Last Name: ");
		input[1] = validObj.getValidStringInput();
		System.out.println("Enter Date OF Birth : ");
		input[2] = validObj.getValidStringInput();
		System.out.println("Enter Gender: ");
		input[3] = validObj.getValidStringInput();
		System.out.println("Enter Address: ");
		input[4] = validObj.getValidStringInput();
		System.out.println("Enter Contact Number: ");
		input[5] = validObj.getValidStringInput();
		System.out.println("Enter Email ID: ");
		input[6] = validObj.getValidStringInput();
		System.out.println("Enter Password: ");
		input[7] = validObj.getValidStringInput();

		String inputStr = "[{\"firstName\":\"" + input[0] + "\",\"lastName\":\"" + input[1]
				+ "\",\"dateOfBirth\":\"" + input[2] + "\",\"gender\":\"" + input[3] + "\",\"address\":\""
				+ input[4] + "\",\"contactNo\":\"" + input[5] + "\",\"emailID\":\"" + input[6]
				+ "\" ,\"password\":\"" + input[7] + "\"}]";

		webResource = client.resource("https://localhost:8443/CourseReg/v1/students/addBatch");
		ClientResponse responseEntity = webResource.type("application/json").post(ClientResponse.class, inputStr);

		if (responseEntity.getStatus() != 200) {
			throw new RuntimeException("Operation Failed!!!. Please try again");
		} else {
			System.out.println("Student data inserted scucessfully!!!");
		}
	}
}

/**
 * Method calling HTTP DELETE requests. The method accepts student IDs from
 * user and deletes those users form student Table. Batch delete operation
 * is implemented. If Response status = 200, data has been successfully
 * updated. Else appropriate error message is displayed.
 */
public void deleteBatch() {
	System.out.println("Enter Number of students you want to delete : ");
	int noOfStudents = sc.nextInt();
	String ids = null;
	for (int i = 0; i < noOfStudents; i++) {

		System.out.println("Enter Student ID :");
		if (i == 0) {
			ids = sc.next();
		} else {
			ids = ids + "," + sc.next();
		}
	}

	webResource = client.resource("https://localhost:8443/CourseReg/v1/students/deleteBatch/" + ids);
	ClientResponse responseEntity = webResource.type("application/json").delete(ClientResponse.class);

	if (responseEntity.getStatus() != 200) {
		throw new RuntimeException("Operation Failed!!!. Please try again");
	} else {
		System.out.println("Student data deleted successfully!!!");
	}
}

/**
 * Method to display all student's data
 */
private void displayStudentList(String inputStudentList) {

	System.out.println("*********************Student List ****************************\n");
	StudentEntity[] studentList = gson.fromJson(inputStudentList, StudentEntity[].class);

	for (int i = 0; i < studentList.length; i++) {
		System.out.println("\n*******************************************************");
		System.out.println("Student ID    : " + studentList[i].getStudentID());
		System.out.println("First Name    : " + studentList[i].getFirstName());
		System.out.println("Last Name     : " + studentList[i].getLastName());
		System.out.println("Address       : " + studentList[i].getAddress());
		System.out.println("Contact No.   : " + studentList[i].getContactNo());
		System.out.println("Date OF Birth : " + studentList[i].getDateOfBirth());
		System.out.println("Gender        : " + studentList[i].getGender());
		}
	}
}