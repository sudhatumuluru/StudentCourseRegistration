package com.CourseRegClient.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.ws.rs.core.MultivaluedMap;
import com.CourseRegClient.Entity.CourseEntity;
import com.CourseRegClient.Entity.StudentEntity;
import com.CourseRegClient.Validation.ValidateInput;
import com.CourseRegClient.Entity.DepartmentEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
* Welcome to Course Registration!!
*
* @author  Group 4
* @version 1.0
* @name :  CourseService Client
* @description : This Course Service Client request for Course information
* @since   2015-11-10
**/
public class CourseClient {
	String getUrl = "https://localhost:8443/CourseReg/v1/courses";
	Client client = Client.create();
	CourseEntity cc = new CourseEntity();
	ClientResponse responseEntity;
	WebResource webResource;
	Gson gson = new Gson();
	Scanner scanner = new Scanner(System.in);
	private static int Range;
	String etag = "";
	ValidateInput vi = new  ValidateInput();
	
	/**
	 * Method calls HTTP GET requests. The method gets all Course information
	 * from the Course Table. 
	 * Method uses "Last Modified" header. 
	 * If the response is not modified, Response status = 304, 
	 * If the modified data has been successfully read, Response status = 200. 
	 * Else appropriate error message is displayed. 
	 */
	public void getAllCourses(){
		try{
			webResource = client.resource(getUrl);
			responseEntity = webResource.path("allcourses").accept("application/json").get(ClientResponse.class);
			String lastModifiedTime = responseEntity.getHeaders().getFirst("Last-modified");
			webResource.header("If-Modified-Since", lastModifiedTime);
			responseEntity = webResource.accept("application/json").get(ClientResponse.class);
			if (responseEntity.getStatus() != 200){
				throw new RuntimeException("Failed :HTTP error code:" + responseEntity.getStatus());
			 }
			if(responseEntity.getStatus()== 304){
				System.out.println("Data is not modified");
			}else if(responseEntity.getStatus()== 200){
				System.out.println("Modified data recieved!!!");}
 			String output = responseEntity.getEntity(String.class);
			displayCourseList(output);
			}catch (Exception e){
				System.out.println("No Courses found!!!");
		   }
		}
	
	/**
	 * Method calls HTTP GET requests. The method gets all Course information
	 * from the Course Table based on course code. 
	 * Method uses "Last Modified" header. 
	 * If the response is not modified, Response status = 304, 
	 * If the modified data has been successfully read, Response status = 200. 
	 * Else appropriate error message is displayed. 
	 */
	public void getCourseInfo(){
		try{
			webResource = client.resource(getUrl);
			System.out.println("Enter Course Code:");
			String code = vi.getValidStringInput();
    		String courseUri = "coursecode/" + code ;
    	    responseEntity = webResource.path(courseUri).accept("application/json").get(ClientResponse.class);
    	    String lastModifiedTime = responseEntity.getHeaders().getFirst("Last-modified");
			webResource.header("If-Modified-Since", lastModifiedTime);
    		responseEntity = webResource.accept("application/json").get(ClientResponse.class);
			if (responseEntity.getStatus() != 200){
    			System.out.println("Incorrect Course Code");
				throw new RuntimeException("Failed :HTTP error code:" + responseEntity.getStatus());
			}
			if(responseEntity.getStatus()== 304){
				System.out.println("Data is not modified");
			}else if(responseEntity.getStatus()== 200){
				System.out.println("Modified data recieved!!!");
			}
    		String output = responseEntity.getEntity(String.class);
    		displayCourse(output);
		}catch (Exception e){
		  System.out.println("No Course Code found!!!");	
		  //e.printStackTrace();
		}
	}
	/**
	 * Method calls HTTP GET requests. The method gets all Course information
	 * from the Course Table based on the Quarter the course is conducted.
	 *  Method uses "Last Modified" header.  
	 * If Response status = 200, data has been successfully read. 
	 * Else appropriate error message is displayed. 
	 */
	public void getCourseQtr(){
		try{
			webResource = client.resource(getUrl);
			System.out.println("View Courses by Quarter(Fall/Spring/Winter/Summer:");
			String code = vi.getValidStringInput();
			responseEntity = webResource.path("allcourses").queryParam("quarter",code).accept("application/json").get(ClientResponse.class);
			String lastModifiedTime = responseEntity.getHeaders().getFirst("Last-modified");
			webResource.header("If-Modified-Since", lastModifiedTime);
			responseEntity = webResource.accept("application/json").get(ClientResponse.class);
			if (responseEntity.getStatus() != 200){
				throw new RuntimeException("Failed :HTTP error code:" + responseEntity.getStatus());
			}
			if(responseEntity.getStatus()== 304){
				System.out.println("Data is not modified");
			}else if(responseEntity.getStatus()== 200 ){
				System.out.println("Modified data recieved!!!");
			}
			String output = responseEntity.getEntity(String.class);
			displayCourseList(output);
			}catch (Exception e){
				System.out.println("No Courses found for the Quarter!!!");
			}
		}
	/**
	 * Method calls HTTP GET requests. The method gets all Course information
	 * from the Course Table based on course Index. Set of 3 courses are displayed.
	 * Method uses "Last Modified" header. 
	 * If the response is not modified, Response status = 304, 
	 * If the modified data has been successfully read, Response status = 200. 
	 * Else appropriate error message is displayed. 
	 */
	public void getCourseIndx(){
		
		Range = 1;
		viewNextCourse();
		char choice;
		boolean done = false;
		while(!done){
			System.out.println("View next page (Enter n ) \n View Prev Page (Enter p) \n Exit (Enter y) ");
			choice = scanner.next().charAt(0);
			switch (choice){
				case 'n': viewNextCourse();
						  break;
				case 'p':
					Range = Range - 4;
					if(Range > 0){
					  	 viewNextCourse();}
					else{
						 System.out.println("Cannot view Prev Page");
						 Range = 1;
					}
					 break;
				case 'y':
					done = true;
					return;		
				default: 
					break;
			}
		}
	}	
	/**
	 * Method calls HTTP GET requests. The method gets Department details
	 * from the Department Table. 
	 * Method uses "Last Modified" header. 
	 * If the response is not modified, Response status = 304, 
	 * If the modified data has been successfully read, Response status = 200. 
	 * Else appropriate error message is displayed. 
	 */
	public void getAllDept(){
		try{
			webResource = client.resource(getUrl);
			responseEntity = webResource.path("dept").accept("application/json").get(ClientResponse.class);
			String lastModifiedTime = responseEntity.getHeaders().getFirst("Last-modified");
			webResource.header("If-Modified-Since", lastModifiedTime);
    		responseEntity = webResource.accept("application/json").get(ClientResponse.class);
			if (responseEntity.getStatus() != 200){
				throw new RuntimeException("Failed :HTTP error code:" + responseEntity.getStatus());
			}
			if(responseEntity.getStatus()== 304){
				System.out.println("Data is not modified");
			}else if(responseEntity.getStatus()== 200){
				System.out.println("Modified data recieved!!!");
			}
			String output = responseEntity.getEntity(String.class);
			displayDept(output);
			}catch (Exception e){
			 e.printStackTrace();
		   }
		}
	/**
	 * Method calls HTTP GET requests. The method gets Course information
	 * by location from the Course Table. 
	 * Method uses "Last Modified" header. 
	 * If the response is not modified, Response status = 304, 
	 * If the modified data has been successfully read, Response status = 200. 
	 * Else appropriate error message is displayed. 
	 */
	public void getCourselocation(){
		try{
			webResource = client.resource(getUrl);
			System.out.println("Enter Location:");
			String locat = vi.getValidStringInput();
	   		String courseUri = "location/" + locat ;
	   		responseEntity = webResource.path(courseUri).accept("application/json").get(ClientResponse.class);
	   		String lastModifiedTime = responseEntity.getHeaders().getFirst("Last-modified");
			webResource.header("If-Modified-Since", lastModifiedTime);
    		        responseEntity = webResource.accept("application/json").get(ClientResponse.class);
	   		if (responseEntity.getStatus() != 200){
				throw new RuntimeException("Failed :HTTP error code:" + responseEntity.getStatus());
			}
	   		if(responseEntity.getStatus()== 304){
				System.out.println("Data is not modified");
			}else if(responseEntity.getStatus()== 200){
				System.out.println("Modified data recieved!!!");
			}
			String output = responseEntity.getEntity(String.class);
			displayCourseList(output);
		}catch (Exception e){
			System.out.println("No Courses found in the searched location!!!");
	   }
	}
	/**
	 * Method calls HTTP GET requests. The method gets Course information
	 * from based on Fees from the Course Table. 
	 * Method uses "Last Modified" header. 
	 * If the response is not modified, Response status = 304, 
	 * If the modified data has been successfully read, Response status = 200. 
	 * Else appropriate error message is displayed. 
	 */
	public void getCourseFee(){
		try{
		    webResource = client.resource(getUrl);
			System.out.println("Enter Fee Range :");
		   	int code = vi.getValidIntInput();
		   	String courseUri = "fee/" + code ;
		   	responseEntity = webResource.path(courseUri).accept("application/json").get(ClientResponse.class);
		   	String lastModifiedTime = responseEntity.getHeaders().getFirst("Last-modified");
			webResource.header("If-Modified-Since", lastModifiedTime);
			responseEntity = webResource.accept("application/json").get(ClientResponse.class);
		   	if (responseEntity.getStatus() != 200 ){
					throw new RuntimeException("Failed :HTTP error code:" + responseEntity.getStatus());
			}
		   	if(responseEntity.getStatus()== 304){
				System.out.println("Data is not modified");
			}else if(responseEntity.getStatus()== 200){
				System.out.println("Modified data recieved!!!");
			}
			String output = responseEntity.getEntity(String.class);
			displayCourseList(output);
		}catch (Exception e){
			System.out.println("No Courses found for Fee Range!!!");
		}
	}
	/**
	 * Method converts json data into text and display in the console
	 */
	private void displayCourse(String inputCourseList)throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("*********************Course List ****************************");
		CourseEntity courseList = mapper.readValue(inputCourseList,CourseEntity.class);
		System.out.println("CourseCode		:"	+	courseList.getCourseCode());
		System.out.println("CourseTitle		:"	+	courseList.getCourseTitle());
		System.out.println("DepartmenttID\t	:"	+	courseList.getDeptID());
		System.out.println("Description		:"	+	courseList.getDescription());
		System.out.println("CourseFee		:"	+	courseList.getCourseFee());	
		System.out.println("Quarter			:"	+	courseList.getQuarter());
		System.out.println("Location		:"	+	courseList.getLocation());
		System.out.println("StartDate		:"	+	courseList.getStartDate());
		System.out.println("EndDate			:"	+	courseList.getEndDate());
		System.out.println("Timings			:"	+	courseList.getTimings());
		System.out.println("Units			:"	+	courseList.getUnits());		
		System.out.println("Capacity		:"	+	courseList.getCapacity());
		System.out.println("FilledStatus\t	:"	+	courseList.getFilledStatus());
		System.out.println("*******************************************************");
	}
	/**
	 * Method converts json data into text and display in the console
	 */	
	private void displayDept(String inputCourseList)throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("**************Department List ****************************\n");
		DepartmentEntity[] deptList = mapper.readValue(inputCourseList,DepartmentEntity[].class);
		for (int i = 0; i <  deptList.length; i++) {
			System.out.println("\n*******************************************************");
			System.out.println("Department ID	:"	+	 deptList[i].getDeptID());
			System.out.println("Department Name	:"	+	 deptList[i].getDeptName());
		}
 	}	
	/**
	 * Method converts json data into text and display in the console
	 */
	private void displayCourseList(String inputCourseList)throws Exception {
	   ObjectMapper mapper = new ObjectMapper();
	   System.out.println("*********************Course List ****************************");
	   CourseEntity[] courseList = mapper.readValue(inputCourseList,CourseEntity[].class);
	   for (int i = 0; i < courseList.length; i++) {
			System.out.println("courseCode		:"	+	courseList[i].getCourseCode());
			System.out.println("courseTitle		:"	+	courseList[i].getCourseTitle());
			 		System.out.println("deptID			:"	+	courseList[i].getDeptID());
					System.out.println("description		:"	+	courseList[i].getDescription());
			 		System.out.println("courseFee		:"	+	courseList[i].getCourseFee());	
			 		System.out.println("quarter			:"	+	courseList[i].getQuarter());
			 		System.out.println("location		:"	+	courseList[i].getLocation());
			 		System.out.println("startDate		:"	+	courseList[i].getStartDate());
			 		System.out.println("endDate			:"	+	courseList[i].getEndDate());
			 		System.out.println("timings			:"	+	courseList[i].getTimings());
			 		System.out.println("units			:"	+	courseList[i].getUnits());		
			 		System.out.println("capacity		:"	+	courseList[i].getCapacity());
			 		System.out.println("filledStatus\t	:"	+	courseList[i].getFilledStatus());
			 		System.out.println("*******************************************************");
			}
	  }			
	/**
	 * Method calls HTTP POST requests. The method insert course information
	 * into the Course Table. 
	 * If Response status = 200, data has been successfully read. 
	 * Else appropriate error message is displayed. 
	 */
	public void insertCourse(){
		try{
			System.out.println("Add a new Course by entering the course details:");
			System.out.println("\n*******************************************************");
			System.out.println("CourseCode		:"	);
			String courseCode = vi.getValidStringInput();
			cc.setCourseCode(courseCode);
			System.out.println("CourseTitle		:"	);
			String courseTitle = vi.getValidStringInput();
			cc.setCourseTitle(courseTitle);
			System.out.println("DepartmenttID	:"	);
			String deptID = vi.getValidStringInput();
			cc.setDeptID(deptID);
			System.out.println("Description		:"	);
			String description = vi.getValidStringInput();
			cc.setDescription(description);
			System.out.println("CourseFee		:"	);
			int courseFee = vi.getValidIntInput();
			cc.setCourseFee(courseFee);
			System.out.println("Quarter			:"	);		
			String quarter = vi.getValidStringInput();
			cc.setQuarter(quarter);
			System.out.println("Location		:"	);	
			String location = vi.getValidStringInput();
			cc.setLocation(location);
			System.out.println("StartDate		:"	);
			String startDate = vi.getValidStringInput();
			cc.setStartDate(startDate);
			System.out.println("EndDate			:"	);
			String endDate = vi.getValidStringInput();
			cc.setEndDate(endDate);
			System.out.println("Timings			:"	);
			String timings = vi.getValidStringInput();
			cc.setTimings(timings);
			System.out.println("Units			:"	);	
			int units = vi.getValidIntInput();
			cc.setUnits(units);
			System.out.println("Capacity		:"	);
			int capacity = vi.getValidIntInput();
			System.out.println("FilledStatus\t	:"	);
			cc.setCapacity(capacity);
			int filledStatus = vi.getValidIntInput();
			cc.setFilledStatus(filledStatus);
			System.out.println("Data passed" + cc);
			webResource = client.resource(getUrl);
			responseEntity = webResource.path("addcourse").type("application/json").post(ClientResponse.class,cc);
			System.out.println("Status:" +responseEntity.getStatus());
			if (responseEntity.getStatus() != 200){
			   	throw new RuntimeException("Failed :HTTP error code:" + responseEntity.getStatus());
			 }else {
				 System.out.println("The Course inserted successfully");
			 }
			String output = responseEntity.getEntity(String.class);
			displayCourse(output);
		}catch (Exception e){
			System.out.println("No Courses was not inserted !!!");
	 }
	}
	/**
	 * Method calls HTTP PUT requests. The method updates course location information
	 * based on the course code into the Course Table. 
	 * Method uses "If-match and Data modified since" header. 
	 * If ETag and Last modified value doesn't match, Response status =
	 * 412, If data has been successfully updated, Response status = 200. Else
	 * appropriate error message is displayed.
	 */
	 
	public void putCourse(){
		try{
			webResource = client.resource(getUrl);
			System.out.println("                Update Course Location                  ");
			System.out.println("\n*******************************************************");
			System.out.println("CourseCode		:"	);
			String courseCode = vi.getValidStringInput();
			cc.setCourseCode(courseCode);
			System.out.println("Location		:"	);	
			String location = vi.getValidStringInput();
			cc.setLocation(location);
			responseEntity = webResource.path("update").type("application/json").put(ClientResponse.class,cc);
			String lastModifiedTime = responseEntity.getHeaders().getFirst("Last-modified");
			etag = responseEntity.getHeaders().getFirst("ETag");
			
			webResource.header("If-Match", etag);
			webResource.header("If-Unmodified-Since", lastModifiedTime);
			responseEntity = webResource.type("application/json").put(ClientResponse.class, cc);
			if (responseEntity.getStatus() != 200){
				throw new RuntimeException("Failed :HTTP error code:" + responseEntity.getStatus());
			}
		    if(responseEntity.getStatus()== 200)
			{
			 	System.out.println("Course Information Updated!!!");
			}
		    String output = responseEntity.getEntity(String.class);
		  }catch (Exception e){
		System.out.println("Course location could not be Updated!!!");
	}
}
	/**
	 * Method calls HTTP PUT requests. The method updates course location information
	 * based on the course code into the Course Table. 
	 * If ETag value doesn't match, Response status = 412, 
	 * If data has been successfully updated, Response status = 200.   
	 * Else appropriate error message is displayed. 
	 */
	public void delCourse(){
		try{
			webResource = client.resource(getUrl);
			System.out.println("CourseCode		:"	);
			String courseCode = vi.getValidStringInput();
			cc.setCourseCode(courseCode);
			responseEntity = webResource.path("delete").type("application/json").delete(ClientResponse.class,cc);
			if (responseEntity.getStatus() != 200){
				throw new RuntimeException("Failed :HTTP error code:" + responseEntity.getStatus());
			}else{
			System.out.println("The Course is successfully Deleted");}
			String output = responseEntity.getEntity(String.class);
		}catch (Exception e){
			System.out.println("Courses could not be delted!!!");
	   }
	}
	public void viewNextCourse(){
		try{
			System.out.println("Range:" + Range);
			int iStart = Range;
		    int iRange =  2;
		    Range = Range + 2;
		    System.out.println("Range:" + iStart);
		    System.out.println("Range:" + iRange);
			String str1 = Integer.toString(iStart);
			String str2 = Integer.toString(iRange);
			webResource = client.resource(getUrl);
			MultivaluedMap queryParams = new MultivaluedMapImpl();
			queryParams.add("start",str1);
			queryParams.add("size",str2);
			System.out.println("Start String,End String:" + str1 + str2);
		    responseEntity = webResource.path("allcourses").queryParams(queryParams).accept("application/json").get(ClientResponse.class);
			if (responseEntity.getStatus() != 200){
				throw new RuntimeException("Failed :HTTP error code:" + responseEntity.getStatus());
			}
			String output = responseEntity.getEntity(String.class);
			displayCourseList(output);
			}catch (Exception e){
				System.out.println("Course could not be found!!!");
		}
	}
}
