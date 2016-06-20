package com.CourseRegClient.Menu;

import java.util.Scanner;

import com.CourseRegClient.Menu.*;
import com.CourseRegClient.Service.*;

/**
* Welcome to Student Menu!!
*
* @author  Group 4
* @version 1.0
* @name :  Student Menu
* @description : This Student Menu allows the users to perform the operations corresponding to Students.
* @since   2015-11-17
**/
public class StudentMenu {
	
	/**
	 * This method allows users to perform operations respective to his role.
	 * Students can view courses and enroll for a course.
	 * update his profile.
	 */
	public void StudentOperations()
	{
		CourseMenu cm = new CourseMenu();
		StudentClient sc = new StudentClient();
		
		int choice = 0;
		do
		{
		System.out.println("\n****** Welcome Student ********");
		System.out.println("1.View Courses and Enroll");
		System.out.println("2.Update profile");
		//System.out.println("3.Delete enrollment");
		System.out.println("3.Go back to Main Menu.");
				
		System.out.println("Please enter your choice:");
		
		Scanner scanner = new Scanner(System.in);
	    choice = scanner.nextInt();
	    		
	    switch(choice)
		{
		
		case 1: cm.CourseOperations();
				break;
				  
		case 2: sc.updateStudent();
		  		break;
  				
		case 3: return;		
		
	    default : return; 
		
		}
		}while(choice <= 2);
		
	}	
}
