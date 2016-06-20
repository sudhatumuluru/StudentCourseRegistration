package com.CourseRegClient.Menu;

import java.util.Scanner;
import com.CourseRegClient.Service.*;

/**
* Welcome to Instructor Menu!!
*
* @author  Group 4
* @version 1.0
* @name :  Instructor Menu
* @description : This Instructor Menu allows the Instructors to perform the operations respective Instructor role.
* @since   2015-11-17
**/

public class InstructorMenu {

	/**
	 * This method allows Instructor to perform operations respective to his role,
	 * This method allows Instructor to view list of students in the course being taught by him, 
	 *  view profile and schedule.
	 */
	
	public void InstructorOperations() {
		int choice = 0;
	do
	{
	System.out.println("\n****** Welcome Instructor ********");
	System.out.println("1.View my students");
	System.out.println("2.View my profile");
	System.out.println("3.View my schedule");
	System.out.println("4. Go back to main menu");
	System.out.println("Please enter your choice:");
	
	Scanner scanner = new Scanner(System.in);
    choice = scanner.nextInt();
    InstructorClient ic = new InstructorClient();
    switch(choice)
	{
	
	case 1: ic.ViewMyStudents();
			break;
			  
	case 2: ic.ViewMyProfile();
	  		break;
	  		
	case 3: ic.ViewMySchedule();
			break;
	  
	case 4: return;
	
	case 5: return;			
	
    default : return; 
	
	}
	}while(choice <=3);
	
}	
}

