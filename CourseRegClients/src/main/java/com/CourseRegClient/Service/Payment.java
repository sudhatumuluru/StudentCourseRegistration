package com.CourseRegClient.Service;

import com.sun.jersey.api.client.Client;



import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.CourseRegClient.Menu.*;
import java.util.Scanner;
import com.CourseRegClient.Service.LoginClient;

/**
* Welcome to Course Enrollment!!
*
* @author  Group 4
* @version 1.0
* @name :  PaymentService Client
* @description : This Payment Service Client request for Course and student id information and enrolls him to the course.
* @since   2015-11-17
**/

public class Payment {
	
	/**
	 * Method calls HTTP POST requests. The method gets student id and course code from the user.
	 * 
	 * If Response status = 200, data has been successfully inserted into database. 
	 * Else appropriate error message is displayed. 
	 */
	
	public void enrollStudent(){
		Client client = Client.create();
		StudentMenu sm = new StudentMenu();
		int StudentID = 0;
		String CourseCode = "";
		String EmailID;
			try {
				//LoginClient id = new LoginClient();
				//StudentID = Integer.parseInt(id.getID());
				System.out.println("Enter your student id :");
				Scanner scanId = new Scanner(System.in);
				StudentID = scanId.nextInt(); 
			}catch(Exception e)
		    {
		    	System.out.println("Please check your Studentid and try again");
		    	return;
		    }
				System.out.println("Enter the coursecode you would like to register :");
				Scanner scanCoursecode = new Scanner(System.in);
				CourseCode = scanCoursecode.nextLine();
		   try {
		    	System.out.println("Please enter your credit card number : ");
		    	Scanner scanCardnum = new Scanner(System.in);
		    	long Cardnum = scanCardnum.nextLong();
		    	int length = String.valueOf(Cardnum).length();
		    	if(length == 16 || length == 15 )
		    	{
		    		System.out.println("Please enter the card expiry date : ");
		    		Scanner scanCardexp = new Scanner(System.in);
		    		String Cardexp = scanCardexp.nextLine();
		    		 WebResource webResource = client
		 		    		.resource("http://10.15.136.28:8080/CourseReg/v1/students/enrollstudent");
		 		    String input = "{\"studentID\":"+StudentID+",\"courseCode\":\""+CourseCode+"\"}";
		 		    ClientResponse response = webResource.type("application/json")
		 				   .post(ClientResponse.class, input);
		 		   switch(response.getStatus())
				    {
						case 700: 	System.out.println("Congratulations ! Your enrollment successfull ....  ");
									break;
						case 800:	System.out.println("Please enter a valid course \n");
									break;
						case 900: 	System.out.println("Please register first and then enroll !!! \n");
									break;
						case 808:	System.out.println("You have already registered for this course.... \n");
									break;
				    }
		    	}
		    	else
		    	{
		    		System.out.println("Please enter the valid 16 digit card number");
		    	}
		    }catch(Exception e)
		    	{
		    	System.out.println("Please check your card number and try again");
		    	return;
		 
		    
		    
	  } 
	}
}