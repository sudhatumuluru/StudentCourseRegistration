package com.CourseRegClient.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.ws.rs.core.MultivaluedMap;
import com.CourseRegClient.Entity.InstructorEntity;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.CourseRegClient.Entity.*;
/**
* Welcome to Instructor Operations!!
*
* @author  Group 4
* @version 1.0
* @name :  InstructorService Client
* @description : This Instructor Service Client request for Instructor information
* @since   2015-11-10
**/

public class InstructorClient {
	String getUrl = "https://localhost:8443/CourseReg/v1/Instructors/";
	Client client = Client.create();
	InstructorEntity ie = new InstructorEntity();
	Scanner sc = new Scanner(System.in);
	ClientResponse responseEntity;
	WebResource webResource;
	Scanner scanner = new Scanner(System.in);
	
	/**
	 * Method calls HTTP GET requests. The method gets Student information to Instructor
	 * from the Instructor Table. 
	 * If Response status = 200, data has been successfully read. 
	 * Else appropriate error message is displayed. 
	 */

	public void ViewMyStudents(){
		try{
			webResource = client.resource(getUrl);
			System.out.println("Enter Instructor Id:");
			String InstructorID = scanner.nextLine();
    		String courseUri = "ViewMyStudents/" + InstructorID ;
			String etag = responseEntity.getHeaders().getFirst("ETag");
			webResource.header("If-None-Match", etag);
    	    responseEntity = webResource.path(courseUri).accept("application/json").get(ClientResponse.class);
    		String output = responseEntity.getEntity(String.class);
    		displayInstructor(output);
			switch(responseEntity.getStatus())
		    {
				case 751: 	System.out.println("View My Student details was successful ....  ");
							break;
				case 851:	System.out.println("Please enter a valid InstructorID \n");
							break;
				case 808:	System.out.println("unexpected error : Please contact admin \n");
							break;
		    }
		}catch (Exception e){
		 e.printStackTrace();
		}
	}
	
	/**
	 * Method calls HTTP GET requests. The method gets Instructor profile to Instructor
	 * from the Instructor Table. 
	 * If Response status = 200, data has been successfully read. 
	 * Else appropriate error message is displayed. 
	 */
	public void ViewMyProfile(){
		try{
			webResource = client.resource(getUrl);
			System.out.println("Enter Instructor Id:");
			String InstructorID = scanner.nextLine();
    		String courseUri = "ViewMyProfile/" + InstructorID ;
			String etag = responseEntity.getHeaders().getFirst("ETag");
			webResource.header("If-None-Match", etag);
    	    responseEntity = webResource.path(courseUri).accept("application/json").get(ClientResponse.class);
    		String output = responseEntity.getEntity(String.class);
    		displayInstructor(output);
			switch(responseEntity.getStatus())
		    {
				case 761: 	System.out.println("View My Profile was successful ....  ");
							break;
				case 861:	System.out.println("Please enter a valid Instructor ID \n");
							break;
				case 808:	System.out.println("unexpected error : Please contact admin \n");
							break;
		    }
		}catch (Exception e){
		 e.printStackTrace();
		}
	}
	
	/**
	 * Method calls HTTP GET requests. The method gets Schedule instructor to Instructor
	 * from the Instructor Table. 
	 * If Response status = 200, data has been successfully read. 
	 * Else appropriate error message is displayed. 
	 */
	public void ViewMySchedule(){
		try{
			webResource = client.resource(getUrl);
			System.out.println("Enter Instructor Id:");
			String InstructorID = scanner.nextLine();
    		String courseUri = "ViewMySchedule/" + InstructorID ;
			String etag = responseEntity.getHeaders().getFirst("ETag");
			webResource.header("If-None-Match", etag);
    	    responseEntity = webResource.path(courseUri).accept("application/json").get(ClientResponse.class);
    		String output = responseEntity.getEntity(String.class);
    		displayInstructor(output);
			switch(responseEntity.getStatus())
		    {
				case 771: 	System.out.println("View My Schedule was successful ....  ");
							break;
				case 871:	System.out.println("Please enter a valid InstructorID \n");
							break;
				case 808:	System.out.println("unexpected error : Please contact admin \n");
							break;
		    }
		}catch (Exception e){
		 e.printStackTrace();
		}
	}

	 /**
	 * Method calls HTTP GET requests. The method displays formatted Instructor information
	 * from the Instructor Table. 
	 * If Response status = 200, data has been successfully read. 
	 * Else appropriate error message is displayed. 
	 */
	private void displayInstructor(String inputInstructorList)throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("*********************Instructor Details ****************************");
		InstructorEntity InstructorList = mapper.readValue(inputInstructorList,InstructorEntity.class);
		LoginEntity LoginList = mapper.readValue(inputInstructorList,LoginEntity.class);
		System.out.println("InstructorID			:"	+	InstructorList.getInstructorID());
		System.out.println("LastName			:"	+	InstructorList.getLastName());
		System.out.println("FirstName			:"	+	InstructorList.getFirstName());
		System.out.println("Gender				:"	+	InstructorList.getGender());
		System.out.println("Qualification			:"	+	InstructorList.getQualification());	
		System.out.println("HireDate			:"	+	InstructorList.getHireDate());
		System.out.println("Salary				:"	+	InstructorList.getSalary());
		System.out.println("ContactNo			:"	+	InstructorList.getContactNo());
		System.out.println("Address				:"	+	InstructorList.getAddress());	
		System.out.println("*********************************************************************");
	}
			
	 /**
	 * Method calls HTTP PUT requests. The method updates Instructor Salary information
	 * based on the Instructor ID into the Instructor Table. 
	 * If Response status = 200, data has been successfully read. 
	 * Else appropriate error message is displayed. 
	 */
	public void updateInstructor(){
		try{
			webResource = client.resource(getUrl);
			System.out.println("                Update Instructor Salary                  ");
			System.out.println("\n*******************************************************");
			System.out.println("Instructor Id		:"	);
			String InstructorID = scanner.nextLine();
			ie.setInstructorID(InstructorID);
			System.out.println("Salary		:"	);	
			String Salary = scanner.nextLine();
			ie.setSalary(Salary);
			System.out.println("Enter new Salary to be updated :");
			Salary = scanner.nextLine();
			ie.setSalary(Salary);
			responseEntity = webResource.path("Instructorupdate/"+InstructorID).type("application/json").put(ClientResponse.class,ie);
			String etag = responseEntity.getHeaders().getFirst("ETag");
			webResource.header("If-None-Match", etag);
			System.out.println("The Instructor Salary has been changed");
			String output = responseEntity.getEntity(String.class);
			displayInstructor(output);
			switch(responseEntity.getStatus())
		    {
				case 781: 	System.out.println("Updating Instructor Salary was successfull ....  ");
							break;
				case 881:	System.out.println("Please enter a valid InstructorID \n");
							break;
				case 808:	System.out.println("unexpected error : Please contact admin \n");
							break;
		    }
		}catch (Exception e){
		 e.printStackTrace();
	}
}

	 /**
	 * Method calls HTTP PUT requests. The method delete Instructor information
	 * based on the Instructor ID into the Instructor Table. 
	 * If Response status = 200, data has been successfully read. 
	 * Else appropriate error message is displayed. 
	 */
	public void deleteInstructor(){
		try{
			webResource = client.resource(getUrl);
			System.out.println("Instructor ID		:"	);
			String InstructorID = scanner.nextLine();
			ie.setInstructorID(InstructorID);
			responseEntity = webResource.path("deleteInstructor/"+InstructorID).type("application/json").delete(ClientResponse.class,ie);
			if (responseEntity.getStatus() != 200){
				throw new RuntimeException("Failed :HTTP error code:" + responseEntity.getStatus());
			}else{
			System.out.println("The Instructor is successfully Deleted");}
			String output = responseEntity.getEntity(String.class);
			switch(responseEntity.getStatus())
		    {
				case 791: 	System.out.println("Deleting Instructor was successfull ....  ");
							break;
				case 891:	System.out.println("Please enter a valid InstructorID \n");
							break;
				case 808:	System.out.println("unexpected error : Please contact admin \n");
							break;
		    }
		}catch (Exception e){
		 e.printStackTrace();
	   }
	}
}
