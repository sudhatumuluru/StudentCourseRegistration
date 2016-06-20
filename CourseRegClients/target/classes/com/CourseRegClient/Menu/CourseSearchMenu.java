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

public class CourseSearchMenu {
	
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
		System.out.println("8.Go back");
		System.out.println("Please enter your choice:");
		
		Scanner scanner = new Scanner(System.in);
		choice = scanner.nextInt();
		CourseClient cc = new CourseClient();
		switch(choice){
			case 1 :cc.getAllCourses();
					break;
					
			case 2 :cc.getCourseInfo();
					break;
					
			case 3 :cc.getCourseQtr();
					break;
					
			case 4 :cc.getCourseIndx();
					break;
					
			case 5 :cc.getAllDept();
					break;
					
			case 6 :cc.getCourselocation();
					break;
					
			case 7 :cc.getCourseFee();
					break;
					
			case 8 : return;
	
			default : return;
			}
	}while(choice <= 7);
			
}
}