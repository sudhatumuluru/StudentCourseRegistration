package com.CourseRegClient.Service;

import java.util.Scanner;
import com.google.gson.Gson;

import javax.ws.rs.core.MultivaluedMap;

import com.CourseRegClient.Entity.CourseEntity;
import com.CourseRegClient.Entity.DepartmentEntity;
import com.sun.jersey.api.client.*;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.CourseRegClient.Validation.*;
public class CourseClient {
	
	String getUrl = "http://10.15.136.28:8080/CourseReg/v1/courses/";
	Client client = Client.create();
	CourseEntity cc = new CourseEntity();
	ClientResponse responseEntity;
	WebResource webResource;
	Scanner scanner = new Scanner(System.in);
	ValidateInput vi = new  ValidateInput();
	private static int Range;
	// Get list of all courses
		public void getAllCourses() {
			try{
				webResource = client.resource(getUrl);
				responseEntity = webResource.path("allcourses").accept("application/json").get(ClientResponse.class);
				if (responseEntity.getStatus() != 200) {
					throw new RuntimeException("Failed : HTTP error code : " + responseEntity.getStatus());
				}
				String output = responseEntity.getEntity(String.class);
				displayCourseList(output);
				}catch (Exception e){
				System.out.println("No Courses found!!!");
		   }
		} 
	
	// Get Course details by passing Course Code Information
	public void getCourseInfo(){
		try{
			System.out.println("Enter Course Code:");
			String code = vi.getValidStringInput();
			webResource = client.resource("http://10.15.136.28:8080/CourseReg/v1/courses/coursecode/" + code);
  	   	    responseEntity = webResource.accept("application/json").get(ClientResponse.class);

    		if (responseEntity.getStatus() != 200){
				throw new RuntimeException("Failed :HTTP error code:" + responseEntity.getStatus());
			}
			
    		System.out.println("The Course Details");
			String output = responseEntity.getEntity(String.class);
			displayCourse(output);
		}catch (Exception e){
		 e.printStackTrace();
	}
	} 
	// Search for Courses by Fall
		public void getCourseQtr(){
			try{
				webResource = client.resource(getUrl);
				System.out.println("View Courses by Quarter(Fall/Spring/Winter/Summer):");
				String code = vi.getValidStringInput();
				responseEntity = webResource.path("allcourses").queryParam("quarter",code).accept("application/json").get(ClientResponse.class);				
	    		if (responseEntity.getStatus() != 200){
					throw new RuntimeException("Failed :HTTP error code:" + responseEntity.getStatus());
				}
				System.out.println("The Course Details");
				String output = responseEntity.getEntity(String.class);
				displayCourseList(output);
			}catch (Exception e){
			 e.printStackTrace();
			}
		}
	// Search for Courses by Range
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
		public void viewNextCourse(){
				try{
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
	// Get list all Departments
			public void getAllDept(){
				try{
					webResource = client.resource(getUrl);
					responseEntity = webResource.path("dept").accept("application/json").get(ClientResponse.class);
					if (responseEntity.getStatus() != 200){
						throw new RuntimeException("Failed :HTTP error code:" + responseEntity.getStatus());
					}
					System.out.println("The Course Details");
					String output = responseEntity.getEntity(String.class);
					displayDept(output);
					}catch (Exception e){
					 e.printStackTrace();
				   }
				}
    // Get Course details by course location
			public void getCourselocation(){
				try{
					webResource = client.resource(getUrl);
					System.out.println("Enter Location:");
					String locat = vi.getValidStringInput();
			   		String courseUri = "location/" + locat ;
			   		responseEntity = webResource.path(courseUri).accept("application/json").get(ClientResponse.class);					
		    		if (responseEntity.getStatus() != 200){
						throw new RuntimeException("Failed :HTTP error code:" + responseEntity.getStatus());
					}
					
		    		System.out.println("The Course Details by location");
					String output = responseEntity.getEntity(String.class);
					displayCourseList(output);
				}catch (Exception e){
				 e.printStackTrace();
				}
			}
    // Get Course details by course Fee
			public void getCourseFee(){
				try{
					webResource = client.resource(getUrl);
					System.out.println("Enter Fee Range :");
				   	int code = vi.getValidIntInput();
				   	String courseUri = "fee/" + code ;
				   	responseEntity = webResource.path(courseUri).accept("application/json").get(ClientResponse.class);
				   	if (responseEntity.getStatus() != 200){
								throw new RuntimeException("Failed :HTTP error code:" + responseEntity.getStatus());
						}
						System.out.println("List of Courses where is less than" + code);
						String output = responseEntity.getEntity(String.class);
						displayCourseList(output);
						}catch (Exception e){
						 e.printStackTrace();
					}
				}
    // Post request Course Information
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
	// Put request Update Course Information
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
		// Delete request Course Information
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
		private void displayCourseList(String inputCourseList)throws Exception  {
			 ObjectMapper mapper = new ObjectMapper();
			System.out.println("*********************Course List ****************************\n");
	//		CourseEntity[] courseList = gson.fromJson(inputCourseList, CourseEntity[].class);
			CourseEntity[] courseList = mapper.readValue(inputCourseList,CourseEntity[].class);
			for (int i = 0; i < courseList.length; i++) {
				System.out.println("\n*******************************************************");
				System.out.println("Course Code   		: " + courseList[i].getCourseCode());
				System.out.println("Course Title    	: " + courseList[i].getCourseTitle());
				System.out.println("Dept ID   		: " + courseList[i].getDeptID());
				System.out.println("Description  		: " + courseList[i].getDescription());
				System.out.println("Course Fee  		: " + courseList[i].getCourseFee());
				System.out.println("Quarter 		: " + courseList[i].getQuarter());
				System.out.println("Location  		: " + courseList[i].getLocation());
				System.out.println("Start Date  		: " + courseList[i].getStartDate());
				System.out.println("End Date  		: " + courseList[i].getEndDate());
				System.out.println("Timings   		: " + courseList[i].getTimings());
				System.out.println("Units   		: " + courseList[i].getUnits());
				System.out.println("Capacity   		: " + courseList[i].getCapacity());
				System.out.println("Filled Status   	: " + courseList[i].getFilledStatus());
				
			}
		}
		/**
		 * Method converts json data into text and display in the console
		 */
		private void displayCourse(String inputCourseList)throws Exception {
			ObjectMapper mapper = new ObjectMapper();
			System.out.println("*********************Course List ****************************");
			CourseEntity courseList = mapper.readValue(inputCourseList,CourseEntity.class);
			//CourseEntity courseList = gson.fromJson(inputCourseList,CourseEntity.class);
			//StudentEntity[] studentList = gson.fromJson(inputStudentList, StudentEntity[].class);

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
}
