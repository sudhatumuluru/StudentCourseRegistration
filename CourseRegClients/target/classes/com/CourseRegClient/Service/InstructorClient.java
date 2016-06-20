package com.CourseRegClient.Service;

import java.util.Scanner;

import javax.ws.rs.core.MultivaluedMap;

import com.CourseRegClient.Entity.InstructorEntity;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class InstructorClient {
	
	String getUrl = "http://10.15.136.28:8080/CourseReg/v1/Instructors/";
	Client client = Client.create();
	InstructorEntity ie = new InstructorEntity();
	

	// Get Student details based on Instructor Id 
	public void ViewMyStudents(){
		try{
			WebResource webget = client.resource(getUrl);
			System.out.println("Enter Instructor Id:");
			Scanner scanner = new Scanner(System.in);
    		String InstructorID = scanner.nextLine();
    		String InstructorUri = "ViewMyStudents/" + InstructorID ;
    		ClientResponse response = webget.path(InstructorUri).accept("application/json").get(ClientResponse.class);
			
    		if (response.getStatus() != 200){
				throw new RuntimeException("Failed :HTTP error code:" + response.getStatus());
			}
			
    		System.out.println("View my student details based on InstructorID");
			String output = response.getEntity(String.class);
			System.out.println(output);
		}catch (Exception e){
		 e.printStackTrace();
		}
	}
	
	//  Get Instructor profile details based on Instructor Id 
	public void ViewMyProfile(){
		try{
			WebResource webget = client.resource(getUrl);
			System.out.println("Enter Instructor Id:");
			Scanner scanner = new Scanner(System.in);
    		String InstructorID = scanner.nextLine();
    		String InstructorUri = "ViewMyProfile/" + InstructorID ;
    		ClientResponse response = webget.path(InstructorUri).accept("application/json").get(ClientResponse.class);
			
    		if (response.getStatus() != 200){
				throw new RuntimeException("Failed :HTTP error code:" + response.getStatus());
			}
			
    		System.out.println("View my profile details based on InstructorID");
			String output = response.getEntity(String.class);
			System.out.println(output);
		}catch (Exception e){
		 e.printStackTrace();
		}
	}
	
	//  Get Instructor schedule details based on Instructor Id 
	public void ViewMySchedule(){
		try{
			WebResource webget = client.resource(getUrl);
			System.out.println("Enter Instructor Id:");
			Scanner scanner = new Scanner(System.in);
    		String InstructorID = scanner.nextLine();
    		String InstructorUri = "ViewMySchedule/" + InstructorID ;
    		ClientResponse response = webget.path(InstructorUri).accept("application/json").get(ClientResponse.class);
			
    		if (response.getStatus() != 200){
				throw new RuntimeException("Failed :HTTP error code:" + response.getStatus());
			}
			
    		System.out.println("View my profile details based on InstructorID");
			String output = response.getEntity(String.class);
			System.out.println(output);
		}catch (Exception e){
		 e.printStackTrace();
		}
	}
	
	// Put request Update Instructor Information
		public void updateInstructor(){
			try{
				WebResource webput = client.resource(getUrl);
				Scanner scanner = new Scanner(System.in);
				System.out.println("Enter Instructor ID :");
				String InstructorID = scanner.next();
				ie.setInstructorID(InstructorID);
				System.out.println("current Salary:");	
				String Salary = scanner.next();
				ie.setSalary(Salary);
				System.out.println("Enter new salary to be updated :");
				Salary = scanner.next();
				ie.setSalary(Salary);
			//	WebResource webget = client.resource("http://localhost:8080/CourseReg/v1/Instructors/Instructorupdate");
				ClientResponse response = webput.path("Instructorupdate").type("application/json").put(ClientResponse.class,ie);
			//	System.out.println("response= "+response);
				if (response.getStatus() != 200){
					throw new RuntimeException("Failed :HTTP error code:" + response.getStatus());
				}
				System.out.println("The Instructor salary has been changed");
				String output = response.getEntity(String.class);
			//	System.out.println(output);
				
			
			}catch (Exception e){
			 e.printStackTrace();
			}

		 }
		// Delete request Instructor Information
		public void deleteInstructor(){
			try{
				WebResource webdel = client.resource(getUrl);
				Scanner scanner = new Scanner(System.in);
				System.out.println("Enter Instructor ID :");
				String InstructorID = scanner.next();
				ie.setInstructorID(InstructorID);
				//WebResource webget = client.resource("http://localhost:8080/testdb/webapi/Instructors/deleteInstructor");
				ClientResponse response = webdel.path("deleteInstructor/"+InstructorID).type("application/json").delete(ClientResponse.class,ie);
				if (response.getStatus() != 204){
					throw new RuntimeException("Failed :HTTP error code:" + response.getStatus());
				}
				System.out.println("The Instructor Deleted");
				//String output = response.getEntity(String.class);
				//System.out.println(output);
				
			
			}catch (Exception e){
				System.out.println("Deletion unsuccessful");
			}

		 }
}