package com.CourseRegClient.Service;

import java.io.Console;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.apache.commons.codec.binary.Base64;

import  com.CourseRegClient.Service.*;
import com.CourseRegClient.Menu.*;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
* Welcome to Login Client!!
*
* @author  Group 4
* @version 1.0
* @name :  Login Client
* @description : This Login Service Client allows the users to login/register by validating the inputs.
* @since   2015-11-17
**/

public class LoginClient {
	/**
	 * This method allows existing users to login and navigates them to their respective operations page
	 * Validates the correctiveness of the username 
	 * Finds the role and navigates user to their operations.
	 */
	String EmailID;
	public void login(){
		
		Client client = Client.create();
		try{
			System.out.println("Enter your user name");
			Scanner scanuser = new Scanner(System.in);
    		String username = scanuser.nextLine();
    		EmailID = username;
    		if (username.indexOf('@')==-1) {
    	        System.out.print("Not a valid EmailID format!");
    	        return;
    		}
    		Console console = System.console();
    		System.out.println("Enter your password");
			Scanner scanpwd = new Scanner(System.in);
			char[] charpassword = console.readPassword();
			String password = new String(charpassword);
    		//String password = scanpwd.nextLine();
    		 /*
    		// the encoder is a web resource protected using BASIC HTTP Authentication
    	    final String urlString = "http://10.189.242.158:8080/CourseReg/v1/login/auth";
    		// open url connection
    	    URL url = new URL( urlString );
    	    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    	   
    	    // set up url connection to get retrieve information back
    	    con.setRequestMethod( "GET" );
    	    con.setDoInput( true );

    	    // stuff the Authorization request header
    	    byte[] encodedpassword = Base64.encodeBase64(( username + "-" + password ).getBytes());
    	    System.out.println("Your credentials has been encrypted in Base64 and sent through Authorization header\t"+new String(encodedpassword ));

    	    con.setRequestProperty( "Authorization", 
    	    		"Basic " + new String(encodedpassword ));   
 
    	    // pull the information back from the URL
    	    InputStream is = con.getInputStream();
    	    StringBuffer buf = new StringBuffer();
    	    int c;
    	    while( ( c = is.read() ) != -1 ) {
    	      buf.append( (char) c );
    	    }
    	    con.disconnect();
    	    
    	   if ((buf.toString()).equals("YES")){
   	     */
    		try {
    			String role="";
        		WebResource webResource2 = client
        					.resource("http://10.15.136.28:8080/CourseReg/v1/login/findrole/"+EmailID);
        		role = webResource2.type("plain/text")
        					.get(String.class);
        		
        		StudentMenu sm = new StudentMenu();
        		InstructorMenu im = new InstructorMenu();
        		AdminMenu am = new AdminMenu();
        		
        		switch(role.toUpperCase()){
        		case "STUDENT" 		: 	sm.StudentOperations();
        								String hateos1 = "https://10.15.136.28:8443/CourseReg/v1/courses/allcourses";
        								String hateos2 = "https://10.15.136.28:8443/CourseReg/v1/courses/location/San Jose";
        								URL url1 = new URL(hateos1);
        								URL url2 = new URL(hateos2);
        								System.out.println("To check all the courses offered:\t"+url1+"\n"+"To check cources offered in San Jose:\t"+url2+"\n");
        								break;
    								
        		case "INSTRUCTOR" 	:  	im.InstructorOperations();
        								String hateos3 = "https://10.15.136.28:8443/CourseReg/v1/students/allstudents";
        								URL url3 = new URL(hateos3);
    									System.out.println("To see all the students:\t"+url3+"\n");
    									break;
    								
        		case "ADMIN" 		:  	am.AdminOperations();
    									break;
    								
        		default 			: 	System.out.println("check your username and password");
    									break;
    		}
    		}catch(Exception e)
    		{
    			System.out.println("please check your username and passowrd");
    		}
    		
		
		} 	    catch(Exception e)	
		{
			System.out.println("please check your username and password");
			e.printStackTrace();
		}
	
	
	}
	
	/**
	 * This method allows existing users to register 
	 * New user will receive a password for their registered emailid and registers with the auto-generated password.
	 * 
	 */
		public void register(){
			try{
				String firstname="",lastname="",dob="",gender="",address="",contactno="";
				String temppwd = "";
				Scanner scanner = new Scanner(System.in);
				System.out.println("Enter your email");
	    		String email = scanner.nextLine();
	    		RandomPasswordGen rpg = new RandomPasswordGen();
	    		temppwd = rpg.sendemail(email);
	    		if(temppwd != "")
	    		{	
	    			System.out.println("Enter the received auto generated password : ");
	    			String password = scanner.nextLine();
	    			if(password.equals(temppwd))
	    			{
	    				System.out.println("Enter your first name");
	    				 firstname = scanner.nextLine();
	    				System.out.println("Enter your last name");
	    				 lastname = scanner.nextLine();
	    				System.out.println("Enter your date of birth");
	    				 dob = scanner.nextLine();
	    				System.out.println("Enter your gender");
	    				 gender = scanner.nextLine();
	    				System.out.println("Enter your contact no");
	    				 contactno = scanner.nextLine();
	    				System.out.println("Enter your address");
	    				 address = scanner.nextLine(); 
	    				 Client client = Client.create();
	    	    		 WebResource webResource = client
	    	 		    		.resource("http://10.15.136.28:8080/CourseReg/v1/students/registerstudent");
	    	 		    String input = "{\"firstName\":\""+firstname+"\""+",\"lastName\":\""+lastname+"\""+
	    	 		    		",\"dateOfBirth\":\""+dob+"\""+",\"gender\":\""+gender+"\""+
	    	 		    				",	\"contactNo\":\""+contactno+"\""+
	    	 		    				",\"address\":\""+address+"\""+
	    	 		    				",\"emailID\":\""+email+"\""+
	    	 		    				",\"password\":\""+temppwd+"\"}";
	    	 		    ClientResponse response = webResource.type("application/json")
	    	 				   .post(ClientResponse.class, input);
	    	 		    System.out.println("Congratualtions !!! You have successfully registered");
	    	 		    return;
	    			}
	    			else
	    			{
	    				System.out.println("Please check the password and try again....");
	    			}
	    		}	
	    		
	 		    
			}
			catch(Exception e)	
			{
				System.out.println("------");
				e.printStackTrace();
			}
			
		}
		/**
		 * This method allows all users to search for courses.  
		 * Searching is provisioned based different categories 
		 * that user wishes to search.
		 */
		public void searchCourse(){
			CourseSearchMenu csc = new CourseSearchMenu();
			csc.CourseOperations();

			}
		public String getID(){
			Client client = Client.create();
			WebResource webResource2 = client
					.resource("http://10.15.136.28:8080/CourseReg/v1/login/findID/"+EmailID);
			String id = webResource2.type("plain/text")
					.get(String.class);
			return id;

			}
}