package com.CourseRegClient.Validation;

import java.util.*;
import java.util.regex.Pattern;
/**
* Welcome to Course Registration!!
*
* @author  Group 4
* @version 1.0
* @name :  Validate Input 
* @description : This validate input service validates the input from the users.
* @since   2015-11-10
**/
public class ValidateInput {
	 Scanner scanner = new Scanner(System.in);
	 
	 	/**
		 * This method checks if the given input is integer or not. 
		 * 
		 */
	 
 public int getValidIntInput(){
	    int field = 0;
	    boolean isNumber;
	    do{
	    	if(scanner.hasNextInt()){
	    		field = scanner.nextInt();
	    		if(field <=0){
	    			isNumber = invalidInput();
	    		}else {
	    			isNumber = true;}
	    		}else{
				isNumber = invalidInput();
				scanner.next();
			}
		}while(!(isNumber));
		return field;
	}
 	/**
	 * The method gets all Course code from user and checks for the pattern matching
	 * 
	 */
 public String getValidStringInput(){
	    String field1 = null;
	    boolean isString;
	    do{
	    	if(scanner.hasNext()){
	    		field1 = scanner.nextLine();
	    		Pattern regex=Pattern.compile("[^-A-Za-z0-9 ]");
	    		if (field1.matches(".*" + regex + ".*")) {
	    			isString = invalidInput();
	    		}else {
	    		 isString = true;}
			}else{
				isString = invalidInput();
				scanner.nextLine();
			}
	     }while(!(isString));
		return field1;
	}
 	/**
	 * This method is invoked when user enter invalid input
	 */
 public boolean invalidInput(){
	    System.out.println("Enter Valid Input :");
	    boolean invalidInp = false;
		return invalidInp;
	}
 
}
			