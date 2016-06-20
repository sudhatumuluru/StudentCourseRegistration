package com.CourseRegClient.Menu;

import java.util.Scanner;


import com.CourseRegClient.Service.*;

/**
* Welcome to Admin Menu!!
*
* @author  Group 4
* @version 1.0
* @name :  Admin Menu
* @description : This Admin Menu allows the users to perform the operations respective his role.
* @since   2015-11-17
**/

public class AdminMenu {

	/**
	 * This method allows admin to perform operations respective to his role,
	 * This method allows to add,update and delete operations on 
	 *  student,course and instructor 
	 */
	
	public void AdminOperations() {
		int choice = 0;
	do
	{
	System.out.println("\n****** Welcome Admin ********");
	System.out.println("1.Add new course");
	System.out.println("2.Update course details");
	System.out.println("3.Delete course details");
	System.out.println("4.Delete student details");
	System.out.println("4.Add student details");
	System.out.println("6.Update instructor details");
	System.out.println("7.Delete instructor details");
	System.out.println("8.Go back to main menu");
	
	System.out.println("Please enter your choice:");
	
	Scanner scanner = new Scanner(System.in);
    choice = scanner.nextInt();
    InstructorClient ic = new InstructorClient();
    CourseClient cc = new CourseClient();
    StudentClient sc = new StudentClient();
    
    switch(choice)
	{
	
	case 1: cc.insertCourse();
			break;
			  
	case 2: cc.putCourse();
	  		break;
	  		
	case 3: cc.delCourse();
			break;
	
	case 4: sc.batchAdd();
			break;
	
	case 5: sc.deleteBatch();
			break;
			
	case 6: ic.updateInstructor();
			break;
			
	case 7: ic.deleteInstructor();
			break;	
	
	case 8: System.out.println("Do you wish to continue y / n");
			String strchoice=scanner.nextLine();
			if(strchoice.equalsIgnoreCase("y")) { return;}
			else { break; }
	
    default : return;
	
	}
	}while(choice < 8);
	
}		
	
}
