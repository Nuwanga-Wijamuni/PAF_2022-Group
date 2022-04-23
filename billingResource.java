package com.elecgrid.crud.billingManagement.resource;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import com.elecgrid.crud.billingManagement.model.billingModel;
import com.elecgrid.crud.billingManagement.services.billingServices;

@Path("/bills")
public class billingResource {
	
billingServices services = new billingServices();
	
	@Path("/addBill")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public billingModel addCustomer(billingModel bModel) {
		
		 return services.insertBill(bModel);
	}
	
	@Path("/retrieveBill")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<billingModel> getCustomer() throws SQLException {
		
		return services.readBill();
	}
	
	@Path("/updateBill")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public billingModel updateCustomer(billingModel bModel) {
		
		 return services.updateBill(bModel);
	}
	
	@Path("/deleteBill/{cus_id}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public int deleteBill(@PathParam("bill_id") int bill_id) {
		
		return services.deleteBill(bill_id);
	}
}
