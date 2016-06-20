package com.group4.studentcoursereg;

import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.group4.studentcourse.entity.*;

import com.mysql.jdbc.Connection;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3307/student_course_reg";
	//Database credentials
	static final String USER = "root";
	static final String PASS = "";
	static Connection connection = null;
	Statement statement;
	
@GET
   @Path("/DatabaseConnect")
   @Produces(MediaType.TEXT_PLAIN)
   public String connectToDatabse() {

try {
// STEP 2: Register JDBC driver
Class.forName("com.mysql.jdbc.Driver");

// STEP 3: Open a connection
connection = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
statement = (Statement) connection.createStatement();
String S = "Connected to database";
return S;

} catch (Exception e) {
e.printStackTrace();
} finally {

}
return null;
}                       

	@GET
	@Path("/DisplayAllStudents")
	@Produces(MediaType.TEXT_HTML)
	public String DisplayAllStudents() {
		int count = 0,i = 0; String displayAllStudents ="";//String display;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
			statement = (Statement) connection.createStatement();
			ResultSet x = statement.executeQuery("Select count(*) from student");
			
			while (x.next()){count = x.getInt(1); }
			
			System.out.println("Total List of Students are: "+ count);
			ResultSet rs = statement.executeQuery("Select * from student");
		while (rs.next()){
			String temp ="<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString("LastName")+"</td><td>"+rs.getString("FirstName")+"</td><td>"+rs.getString("Gender")+"</td><td>"+rs.getString("Address")+"</td><td>"+rs.getDate("DateofBirth")+"</td></tr>";
			displayAllStudents = displayAllStudents + temp;
					 
		}
		System.out.println(displayAllStudents);
		 
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		 return "Total Number of students are : "+count+"\n<table border="+"1"+"<thead><tr><th>Id</th><th>LastName</th><th>FirstName</th><th>Gender</th><th>Address</th><th>DateOfBirth</th></tr></thead><tbody>"+
					displayAllStudents+"</tbody></table>";


	}          

	@PUT
	@Path("/UpdateStudent/{passParameter1}/{passParameter2}")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.TEXT_PLAIN)
	public void UpdateStudent(@PathParam("passParameter1") String passParameter1,@PathParam("passParameter2") int passParameter2) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
			statement = (Statement) connection.createStatement();
			ResultSet rsbefore = statement.executeQuery("select firstname from student where studentid="+passParameter2);
			if(rsbefore.next())
			{
				statement.executeUpdate("update student set FirstName='"+passParameter1+"' where StudentID="+passParameter2);
			}	

		 
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}          
		
	package com.group4.studentcoursereg;

	import java.sql.DriverManager;


	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;

	import javax.ws.rs.Consumes;
	import javax.ws.rs.GET;
	import javax.ws.rs.PUT;
	import javax.ws.rs.Path;
	import javax.ws.rs.PathParam;
	import javax.ws.rs.Produces;
	import javax.ws.rs.QueryParam;
	import javax.ws.rs.core.MediaType;
	import com.group4.studentcourse.entity.*;

	import com.mysql.jdbc.Connection;

	/**
	 * Root resource (exposed at "myresource" path)
	 */
	@Path("/myresource")
	public class MyResource {

	    /**
	     * Method handling HTTP GET requests. The returned object will be sent
	     * to the client as "text/plain" media type.
	     *
	     * @return String that will be returned as a text/plain response.
	     */

		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		static final String DB_URL = "jdbc:mysql://localhost:3307/student_course_reg";
		//Database credentials
		static final String USER = "root";
		static final String PASS = "";
		static Connection connection = null;
		Statement statement;
		
	@GET
	   @Path("/DatabaseConnect")
	   @Produces(MediaType.TEXT_PLAIN)
	   public String connectToDatabse() {

	try {
	// STEP 2: Register JDBC driver
	Class.forName("com.mysql.jdbc.Driver");

	// STEP 3: Open a connection
	connection = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
	statement = (Statement) connection.createStatement();
	String S = "Connected to database";
	return S;

	} catch (Exception e) {
	e.printStackTrace();
	} finally {

	}
	return null;
	}                       

		@GET
		@Path("/DisplayAllStudents")
		@Produces(MediaType.TEXT_HTML)
		public String DisplayAllStudents() {
			int count = 0,i = 0; String displayAllStudents ="";//String display;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
				statement = (Statement) connection.createStatement();
				ResultSet x = statement.executeQuery("Select count(*) from student");
				
				while (x.next()){count = x.getInt(1); }
				
				System.out.println("Total List of Students are: "+ count);
				ResultSet rs = statement.executeQuery("Select * from student");
			while (rs.next()){
				String temp ="<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString("LastName")+"</td><td>"+rs.getString("FirstName")+"</td><td>"+rs.getString("Gender")+"</td><td>"+rs.getString("Address")+"</td><td>"+rs.getDate("DateofBirth")+"</td></tr>";
				displayAllStudents = displayAllStudents + temp;
						 
			}
			System.out.println(displayAllStudents);
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			 return "Total Number of students are : "+count+"\n<table border="+"1"+"<thead><tr><th>Id</th><th>LastName</th><th>FirstName</th><th>Gender</th><th>Address</th><th>DateOfBirth</th></tr></thead><tbody>"+
						displayAllStudents+"</tbody></table>";


		}          

		

		@PUT
		@Path("/studentupdate")
		@Produces(MediaType.APPLICATION_JSON)
		public Response updateStudent(String StudentJson) throws Exception{
			ObjectMapper mapper = new ObjectMapper();
			Course courseUpd = mapper.readValue(CourseJson, Course.class);
			CourseService courseService = new CourseService(); 
			System.out.println(courseUpd.getCourseCode());
		    courseService.updateCourse(courseUpd.getCourseCode(),courseUpd.getLocation());
		    return Response.status(200).entity(CourseJson).build();
		}	
}

		
		


