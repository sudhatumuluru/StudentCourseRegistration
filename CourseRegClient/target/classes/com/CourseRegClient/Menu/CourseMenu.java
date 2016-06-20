package com.CourseRegClient.Menu;

import java.util.Scanner;
import com.CourseRegClient.Service.*;

import com.CourseRegClient.Service.CourseClient;

/**
* Welcome to Course Menu!!
*
* @author  Group 4
* @version 1.0
* @name :  Course Menu
* @description : This Course Menu allows the users to perform the operations corresponding to courses.
* @since   2015-11-17
**/

public class CourseMenu {
	
public void CourseOperations() {
	
	/**
	 * This method allows users to perform operations respective to his role.
	 * All users can search for the course based on criteria
	 * Students can enroll for a course by navigating to the payment page from here.
	 * Admin can make changes to the courses,delete or add new. 
	 */
	int choice = 0;
	do
	{
		System.out.println("\n");	
		System.out.println(" ********* Welcome To Course Menu ********* ");		
		System.out.println("1.Search for all Courses");
		System.out.println("2.Search for Courses by CourseCode");
		System.out.println("3.Search for Courses by Quarter");
		System.out.println("4.Search for Courses by Index 1-3");
		System.out.println("5.List all Departments ");
		System.out.println("6.List Courses by Location");
		System.out.println("7.List Courses by Fee");
		System.out.println("8.Add New Courses");
		System.out.println("9.Update Course location");
		System.out.println("10.Delete Course code");
		System.out.println("11.Go back to student menu");
		System.out.println("Please enter your choice:");
		
		Scanner scanner = new Scanner(System.in);
		choice = scanner.nextInt();
		CourseClient cc = new CourseClient();
		switch(choice){
			case 1 :cc.getAllCourses();
					paymentCall();
					break;
					
			case 2 :cc.getCourseInfo();
					paymentCall();
					break;
					
			case 3 :cc.getCourseQtr();
					paymentCall();
					break;
					
			case 4 :cc.getCourseIndx();
					paymentCall();
					break;
					
			case 5 :cc.getAllDept();
					paymentCall();
					break;
					
			case 6 :cc.getCourselocation();
					paymentCall();
					break;
					
			case 7 :cc.getCourseFee();
					paymentCall();
					break;
		
			case 8 :cc.insertCourse();
					break;
					
			case 9:cc.putCourse();
					break;
					
			case 10:cc.delCourse();
					break;
					
			case 11 : System.out.println("Do you wish to continue y / n");
					 String strchoice=scanner.nextLine();
					 if(strchoice.equalsIgnoreCase("y")) { return;}
					 else { break; }
	
			default : return;
			}
	}while(choice <= 10);
			
}
	/**
	 * 
	 * This method allows users to navigate to the payment page if he wishes to enroll. 
	 *  
	 */
	public void paymentCall()
	{
		System.out.println("Do you wish to enroll for any of the courses shown above.....y/n");
		Scanner scanner = new Scanner(System.in);
		String call = scanner.nextLine();
		if(call.equals("y"))
		{
			Payment pc = new Payment();
			pc.enrollStudent();
		}
		else 
		{
			CourseOperations();
		}
	}
}