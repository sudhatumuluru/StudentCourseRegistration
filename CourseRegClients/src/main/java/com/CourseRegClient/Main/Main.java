package com.CourseRegClient.Main;

import java.util.Scanner;

import com.CourseRegClient.Menu.*;
import com.CourseRegClient.Service.*;
import com.sun.jersey.api.client.Client;
/**
* Welcome to Main Method!!
*
* @author  Group 4
* @version 1.0
* @name :  Main
* @description : From here the execution flow starts
* @since   2015-11-17
**/
public class Main {
	
	/**
	 * This method calls the client part of the login 
	 * This method allows new users to register and 
	 * This method allows existing users to login
	 * This method allows all users to search for courses offered.
	 */
	public static void main(String[] args) {
		
			int choice = 0;
			do
			{
			System.out.println("\n****** Welcome to Silicon Valley Institute ********");
			System.out.println("1.Login");
			System.out.println("2.New user? Register");
			System.out.println("3.Search courses");
			System.out.println("4.Exit");
			Scanner scanner = new Scanner(System.in);
		    choice = scanner.nextInt();
		    LoginClient lc = new LoginClient();
		    switch(choice)
			{
		    
			case 1: lc.login();
					break;
					  
			case 2: lc.register();
			  		break;
			
			case 3: lc.searchCourse();
					break;	
					
			case 4: return;
					
		    default : return; 		
			}
			}while(choice <= 3);
		}
		
}