package com.epower.crud.UserPowerConsumption.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.epower.crud.UserPowerConsumption.services.UserPowerConsumptionServices;

@Path("/powerCons")
public class UserPowerConsumptionResource {
	UserPowerConsumptionServices billServ = new UserPowerConsumptionServices();
	
	//View power consumption details
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String getBill(){
		return billServ.viewPowerConsumptionDetails();
	}
	
	//Insert power consumption details
	@POST
	@Path("/insertPowConsData")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertBillInfo(@FormParam("unitAmount") String unitAmount, @FormParam("killowatt") String killowatt, @FormParam("t_hrs") String thrs,
								@FormParam("c_id") String c_id) 
	{
		
		String output = billServ.insertBillDetails(unitAmount, killowatt, thrs, c_id) ;
		return output;
	}
	
	//Update Bill
	@PUT
	@Path("/updatePowConsData")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBillInfo(@FormParam("bill_id") String bill_id, @FormParam("unitAmount") String unitAmount,
			@FormParam("killowatt") String killowatt, @FormParam("unit") String unit, @FormParam("t_hrs") String thrs,@FormParam("b_id") String b_id) {
			
		String output = billServ.updateBillDetails(bill_id, unitAmount, killowatt, thrs);
		
		return output;
	}
	
	//Delete power consumption
	@DELETE
	@Path("/deletePowConsData")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBillInfo(String pCons)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(pCons, "", Parser.xmlParser());
		
		//Read the value from the element customer id
		String b_id = doc.select("bill_id").text();
		String output = billServ.deletePConsumptionDetails(b_id);
	
	return output;
	}

}
