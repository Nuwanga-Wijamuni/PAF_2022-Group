package com.epower.crud.PowerFailureManagement.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PowerFailureComplaintServices {
		
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
		
		//add new power failure complaint
		public String insertComplaint(String cust_name, String nic, String area, String grid_name, String complaint, String status) {
			
			String output = "";

			try {
				
				Connection con = connect();
				
				if(con == null)
				{return "Error while connecting to the database for inserting data";}
				
				String insertQuery = "insert into powerfailuredetails (`cust_name`, `nic`, `area`, `grid_name`,`complaint`,`status`)" + "values(?,?,?,?,?,?)";
				
				PreparedStatement ps = con.prepareStatement(insertQuery);
				
				ps.setString(1, cust_name);
				ps.setString(2, nic);
				ps.setString(3, area);
				ps.setString(4, grid_name);
				ps.setString(5, complaint);
				ps.setString(6, status);

				ps.execute();
				con.close();
				
				output = "Complaint Inserted Successfully";

			} catch(Exception e) {
				output = "Error While inserting the complaint.";
				System.err.println(e.getMessage());
			}

			return output;
		}
		
		//update complaint
		public String updateComplaint(String complaint_id,String status) {
			
			String output="";
			
			try {
				
				Connection con = connect();
				
				if (con==null)
				{ return "Error!! While connecting to the database for updating the " + complaint_id;}
				
				// create a prepared statement
				String query = "UPDATE powerfailuredetails SET status=? WHERE complaint_id=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setString(1, status);
				preparedStmt.setInt(2,Integer.parseInt(complaint_id));
				
				// execute the statement
				preparedStmt.execute();
				
				con.close();
				
				output = "Updated" + complaint_id + " status successfully";
				
			} catch (Exception e) {
				
				output = "Error while updating the " + complaint_id;
				System.err.println(e.getMessage());
			}
			
			return output;
		}
		
		//view all complaints
		public String viewComplaints() {
			
			String output ="";
			
			try {
				
				Connection conn = connect();
				
				if (conn==null)
				{ return "Error!! While connecting to the database for read the complaint";}
				
				output = "<table border='1'><tr><th>Complaint ID</th>" 
						+ "<th>Complainer</th>" 
						+ "<th>NIC</th>" 
						+ "<th>Area</th>" 
						+ "<th>Grid Name</th>"
						+ "<th>Complaint</th>"
						+ "<th>Date</th>"
						+ "<th>Time</th>"
						+ "<th>Status</th>"
						+ "<th>Update</th><th>Remove</th></tr>";
				
				String query = "select * from powerfailuredetails";
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				while(rs.next()) {
					
					String comp_id = Integer.toString(rs.getInt("complaint_id"));
					String c_name = rs.getString("cust_name");
					String nic = rs.getString("nic");
					String area = rs.getString("area");
					String gname = rs.getString("grid_name");
					String complaint = rs.getString("complaint");
					String comp_date = rs.getString("date");
					String comp_time = rs.getString("time");
					String status = rs.getString("status");
					
					// Add into the html table
					output += "<tr><td>" + comp_id + "</td>";
					output += "<td>" + c_name + "</td>";
					output += "<td>" + nic + "</td>";
					output += "<td>" + area + "</td>";
					output += "<td>" + gname + "</td>";
					output += "<td>" + complaint + "</td>";
					output += "<td>" + comp_date + "</td>";
					output += "<td>" + comp_time + "</td>";
					output += "<td>" + status + "</td>";
					
					output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
							+ "<td><form method='post' action=''>"
							+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
							+ "<input name='cus_id' type='hidden' value='" + comp_id
							+ "'>" + "</form></td></tr>";
					
				}
				
				conn.close();
				
				output += "</table>";
			} catch (Exception e) {
				output = "Error while getting complaints.";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		
		//Delete complaint
		public String deleteComplaint(String complaintID)
		{
			String output = "";
			try
			{
			Connection con = connect();
			
			if (con == null)
			{return "Error while connecting to the database for deleting complaint."; }
			
			// create a prepared statement
			String query = "delete from powerfailuredetails WHERE complaint_id=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(complaintID));
			
			// execute the statement
			preparedStmt.execute();
			
			con.close();
			output = "Complaint Deleted successfully";
			}
			catch (Exception e)
			{
				output = "Error while deleting the complaint.";
				System.err.println(e.getMessage());
			}
		return output;
		}
}
