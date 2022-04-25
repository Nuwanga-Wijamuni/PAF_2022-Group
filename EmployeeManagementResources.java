package com.epower.crud.EmployeeManagement.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.epower.crud.EmployeeManagement.services.EmployeeManagementServices;

@Path("/employee")
public class EmployeeManagementResources {

	EmployeeManagementServices empServ = new EmployeeManagementServices();
	
	//view all employees
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String viewAllEmpData() {
		return empServ.viewEmpDetails();
	}
	
	
	//Add new employee
	@POST
	@Path("/addEmployeeData")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertEmpInfo(@FormParam("employee_name") String employee_name, @FormParam("salary") String emp_salary,
								@FormParam("department_name") String emp_department) 
	{
		
		String output = empServ.insertEmpDetails(employee_name, emp_salary, emp_department);
		 return output;
	}
	
	//View Employee by ID
	@GET
	@Path("/{e_id}")
	@Produces(MediaType.TEXT_HTML)
	public String getEmpInfoById(@PathParam("e_id") int id){
		return empServ.getEmpDetailsById(id);
	}
	
	//Update Employee
	@PUT
	@Path("/updateEmployeeData")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateEmpInfo(@FormParam("employee_name") String emp_name, @FormParam("salary") String emp_salary,
			@FormParam("department_name") String emp_department, @FormParam("e_id") String emp_id) {
			
		String output = empServ.updateEmpDetails(emp_id, emp_name, emp_salary, emp_department);
		
		return output;
	}
	
	//Remove employee
	@DELETE
	@Path("/deleteEmployeeData")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteEmpInfo(String emp)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(emp, "", Parser.xmlParser());
		
		//Read the value from the element <e_id>
		String emp_id = doc.select("e_id").text();
		String output = empServ.deleteEmpDetails(emp_id);
	
	return output;
	}
}
