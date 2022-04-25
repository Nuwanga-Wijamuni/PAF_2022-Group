package com.elecgrid.crud.billingManagement.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.elecgrid.crud.billingManagement.model.billingModel;



public class billingServices {
	
	Connection con;
	String msgOutput;

	public billingServices(){
		try {
			String url = String.format("jdbc:mysql://localhost:3306/elecgrid");
			String username = "root";
			String password = "";

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username, password);
		} catch(Exception e) {

			System.out.println( e + "data connection is unsuccessful");
		}
	}

	//insert customer
	public billingModel insertBill(billingModel bill) {

		String insertQuery = "insert into bills (bill_id, bill_desc, bill_type, bill_no, unit) Values(?,?,?,?,?)";

		try {
			PreparedStatement ps = con.prepareStatement(insertQuery);
			ps.setInt(1, bill.getBill_id());
			ps.setString(2, bill.getBill_desc());
			ps.setString(4, bill.getBill_no());
			ps.setInt(5, bill.getUnit());

			ps.execute();
			
			

		} catch(Exception e) {
			System.out.println(e +"data insertion is unsuccessful");
		}

		return bill;
	}

	//get the list of the bills
	public ArrayList<billingModel> readBill() throws SQLException {
//		
//		msgOutput = "<table border='1'><tr><th>Customer ID</th><th>Customer Name</th>" +
//				"<th></th>" +
//				"<th>Item Description</th>" +
//				"<th>Update</th><th>Remove</th></tr>";

		ArrayList<billingModel> selData = new ArrayList<billingModel>();

		String selectQuery = "Select * from bills";

		PreparedStatement ps = con.prepareStatement(selectQuery);
		ResultSet rst = ps.executeQuery();

		while(rst.next()) {

			billingModel cm = new billingModel();

			cm.setBill_id(rst.getInt("bill_id"));
			cm.setBill_desc(rst.getString("bill_desc"));
			cm.setBill_no(rst.getString("bill_no"));
			cm.setUnit(rst.getInt("unit"));

			selData.add(cm);
		}

		return selData;
	}

	//update customer bill
	public billingModel updateBill(billingModel bill) {

		String updateQuery = "update bills set bill_id=?,bill_desc=?, bill_type=?, bill_no=?, unit=? where bill_id=?";

		try {
			PreparedStatement ps = con.prepareStatement(updateQuery);
			ps.setInt(1, bill.getBill_id());
			ps.setString(2, bill.getBill_desc());
			ps.setString(4, bill.getBill_no());
			ps.setInt(6, bill.getUnit());

			ps.executeUpdate();

		} catch(Exception e) {
			System.out.println(e +"data update is unsuccessful");
		}

		return bill;
	}
	
	//delete customer
	public int deleteBill(int bill_id) {

		String deleteQuery = "delete from bills where bill_id=?";

		try {
			PreparedStatement ps = con.prepareStatement(deleteQuery);
			ps.setInt(1,bill_id);

			ps.executeUpdate();

		} catch(Exception e) {
			System.out.println(e +"data deletion is unsuccessful");
		}

		return bill_id;
	}
}
