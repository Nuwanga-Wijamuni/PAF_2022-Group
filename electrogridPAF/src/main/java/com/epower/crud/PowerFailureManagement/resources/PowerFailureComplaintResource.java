package com.epower.crud.PowerFailureManagement.resources;

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

import com.epower.crud.PowerFailureManagement.services.PowerFailureComplaintServices;

@Path("/complaints")
public class PowerFailureComplaintResource {
	PowerFailureComplaintServices complaintServ = new PowerFailureComplaintServices();
	
	//View power failure complaint details
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String viewComplaints(){
		return complaintServ.viewComplaints();
	}
	
	//Insert power failure complaint  details
	@POST
	@Path("/insertComplaint")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertComplaint(@FormParam("cust_name") String cust_name, @FormParam("nic") String nic, @FormParam("area") String area,
								@FormParam("grid_name") String grid_name,@FormParam("complaint") String complaint, @FormParam("status") String status ) 
	{
		
		String newComplaint = complaintServ.insertComplaint(cust_name, nic, area, grid_name, complaint, status) ;
		return newComplaint;
	}
	
	//Update Complaint
	@PUT
	@Path("/updateComplaint")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateComplaintInfo(@FormParam("complaint_id") String complaint_id, @FormParam("status") String status) {
			
		String updateComp = complaintServ.updateComplaint(complaint_id, status);
		
		return updateComp;
	}
	
	//Delete power Complaint
	@DELETE
	@Path("/deleteComplaint")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBillInfo(String complain)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(complain, "", Parser.xmlParser());
		
		//Read the value from the element customer id
		String complaintID = doc.select("complaint_id").text();
		String output = complaintServ.deleteComplaint(complaintID);
	
	return output;
	}

}
