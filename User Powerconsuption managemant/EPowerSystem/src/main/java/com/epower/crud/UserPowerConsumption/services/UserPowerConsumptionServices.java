package com.epower.crud.UserPowerConsumption.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserPowerConsumptionServices {
		
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
		
		//add power consumption details
		public String insertBillDetails( String unitAmount, String killowatt, String thrs, String c_id) {
			
			String output = "";

			try {
				
				Connection con = connect();
				
				if(con == null)
				{return "Error while connecting to the database for inserting data";}
				
				String insertQuery = "insert into powerconsumptiondetails (`unitAmount`, `killowatt`, `t_hrs`,`c_id`)" + "values(?,?,?,?)";
				
				PreparedStatement ps = con.prepareStatement(insertQuery);
				
				ps.setString(1, unitAmount);
				ps.setString(2, killowatt);
				ps.setString(3, thrs);
				ps.setString(4, c_id);

				ps.execute();
				con.close();
				
				output = "Inserted Successfully";

			} catch(Exception e) {
				output = "Error While inserting the bill details.";
				System.err.println(e.getMessage());
			}

			return output;
		}
		
		//update billing
		public String updateBillDetails(String bill_id,String unitAmount, String killowatt, String thrs) {
			
			String output="";
			
			try {
				
				Connection con = connect();
				
				if (con==null)
				{ return "Error!! While connecting to the database for updating the " + bill_id;}
				
				// create a prepared statement
				String query = "UPDATE powerconsumptiondetails SET unitAmount=?, killowatt=?, t_hrs=? WHERE bill_id=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setString(1, unitAmount);
				preparedStmt.setString(2, killowatt);
				preparedStmt.setString(3, killowatt);
				preparedStmt.setInt(4,Integer.parseInt(bill_id));
				
				// execute the statement
				preparedStmt.execute();
				
				con.close();
				
				output = "Updated successfully";
				
			} catch (Exception e) {
				
				output = "Error while updating the " + bill_id;
				System.err.println(e.getMessage());
			}
			
			return output;
		}
		
		//view user power consumption details
		public String viewPowerConsumptionDetails() {
			
			String output ="";
			
			try {
				
				Connection con = connect();
				
				if (con==null)
				{ return "Error!! While connecting to the database for read the bill";}
				
				output = "<table border='1'><tr><th>Bill ID</th>" 
						+ "<th>Unit Amount</th>" 
						+ "<th>Customer Name</th>" 
						+ "<th>Total Hours</th>" 
						+ "<th>Killo Watt</th>" 
						+ "<th>Update</th><th>Remove</th></tr>";
				
				String query = "select * from powerconsumptiondetails p, customer c where c.c_id = p.c_id";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				while(rs.next()) {
					
					String b_id = Integer.toString(rs.getInt("bill_id"));
					String c_name = rs.getString("c_name");
					String unitAmount = rs.getString("unitAmount");
					String tHrs = rs.getString("t_hrs");
					String kw = rs.getString("killowatt");
					
					// Add into the html table
					output += "<tr><td>" + b_id + "</td>";
					output += "<td>" + unitAmount + "</td>";
					output += "<td>" + c_name + "</td>";
					output += "<td>" + tHrs + "</td>";
					output += "<td>" + kw + "</td>";
					
					output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
							+ "<td><form method='post' action=''>"
							+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
							+ "<input name='cus_id' type='hidden' value='" + b_id
							+ "'>" + "</form></td></tr>";
					
				}
				
				con.close();
				
				output += "</table>";
			} catch (Exception e) {
				output = "Error while reading power consumption details";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		
		//Delete billing
		public String deletePConsumptionDetails(String bID)
		{
			String output = "";
			try
			{
			Connection con = connect();
			
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			
			// create a prepared statement
			String query = "delete from powerconsumptiondetails WHERE bill_id=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(bID));
			
			// execute the statement
			preparedStmt.execute();
			
			con.close();
			output = "Deleted successfully";
			}
			catch (Exception e)
			{
				output = "Error while deleting the consuption details.";
				System.err.println(e.getMessage());
			}
		return output;
		}
}
