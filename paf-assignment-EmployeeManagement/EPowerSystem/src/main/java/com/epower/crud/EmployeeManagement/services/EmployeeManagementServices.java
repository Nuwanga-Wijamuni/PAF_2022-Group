package com.epower.crud.EmployeeManagement.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmployeeManagementServices {

	//connection
		private Connection connect() {
			Connection con = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				String url = String.format("jdbc:mysql://localhost:3306/electricgrid_db");
				String username = "root";
				String password = "";
				
				con = DriverManager.getConnection(url,username, password);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return con;
		}

		//insert employee
		public String insertEmpDetails(String employee_name, String emp_salary, String emp_department) {
			
			String output = "";

			try {
				
				Connection con = connect();
				
				if(con == null)
				{return "Error while connecting to the database for inserting data";}
				
				String insertEmp = "insert into employee (`e_id`, `employee_name`, `salary`, `department_name`)" + "values(?,?,?,?)";
				
				PreparedStatement ps = con.prepareStatement(insertEmp);
				ps.setInt(1, 0);
				ps.setString(2, employee_name);
				ps.setString(3, emp_salary);
				ps.setString(4, emp_department);

				ps.execute();
				con.close();
				
				output = "Inserted "+ employee_name + " Successfully";

			} catch(Exception e) {
				output = "Error While inserting the employee.";
				System.err.println(e.getMessage());
			}

			return output;
		}

		//view employee details
		public String viewEmpDetails() {
			
			String output ="";
			
			try {
				
				Connection con = connect();
				
				if (con==null)
				{ return "Error!! While connecting to the database for read the Employee Details";}
				
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>Employee Name</th><th>Salary:</th>" +
				"<th>Department:</th>" +
				"<th>Update</th><th>Remove</th></tr>";
				
				String query = "select * from employeeSector";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				while(rs.next()) {
					
					String emp_id = Integer.toString(rs.getInt("e_id"));
					String emp_name = rs.getString("employee_name");
					String emp_salary = rs.getString("salary");
					String emp_department = rs.getString("department_name");
					
					// Add into the html table
					output += "<tr><td>" + emp_name + "</td>";
					output += "<td>" + emp_salary  + "</td>";
					output += "<td>" + emp_department + "</td>";
					
					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
					+ "<td><form method='post' action='items.jsp'>"
					+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
					+ "<input name='cus_id' type='hidden' value='" + emp_id
					+ "'>" + "</form></td></tr>";
					
				}
				
				con.close();
				
				output += "</table>";
			} catch (Exception e) {
				output = "Error while fetching employees details.";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		//update employee details
		public String updateEmpDetails(String emp_id, String emp_name, String emp_salary, String emp_department) {
			
			String output="";
			
			try {
				
				Connection con = connect();
				
				if (con==null)
				{ return "Error!! While connecting to the database for updating the " + employee_name;}
				
				// create a prepared statement
				String query = "UPDATE employee SET employee_name=?, salary=?, department_name=? WHERE e_id=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setString(1, emp_name);
				preparedStmt.setString(2, emp_salary);
				preparedStmt.setString(3, emp_department);
				preparedStmt.setInt(4,Integer.parseInt(emp_id));
				
				// execute the statement
				preparedStmt.execute();
				
				con.close();
				
				output = "Updated successfully";
				
			} catch (Exception e) {
				
				output = "Error while updating the " + employee_name;
				System.err.println(e.getMessage());
			}
			
			return output;
		}
		
		//delete employee from db
		public String deleteEmpDetails(String emp_id)
		{
			String output = "";
			
			try
			{
			Connection con = connect();
			
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			
			// create a prepared statement
			String query = "delete from employee where e_id=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(emp_id));
			
			// execute the statement
			preparedStmt.execute();
			
			con.close();
			output = "Deleted successfully";
			}
			catch (Exception e)
			{
				output = "Error while deleting the employee details.";
				System.err.println(e.getMessage());
			}
		return output;
		}
		
		//get employee by id
		public String getEmpDetailsById(int emp_id) {
			String output ="";
			
			try {
				
				Connection con = connect();
				
				if (con==null)
				{ return "Error!! While connecting to the database for read the employee details";}
				
				
				
				String selquery = "select * from employee WHERE e_id= " + emp_id;
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(selquery);
				
				while(rs.next()) {
					
					
					String employee_name = rs.getString("employee_name");
					String emp_salary = rs.getString("salary");
					String emp_department = rs.getString("department_name");
					
					// view
					output += "Name: " + employee_name + "<br>" + "Salary: " + emp_salary;
					output += "<br>Department: " + emp_department;
					
					
				}
				
				con.close();
				
			} catch (Exception e) {
				output = "Error while reading employee";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
}
