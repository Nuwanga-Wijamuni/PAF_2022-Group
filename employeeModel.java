package com.elecgrid.crud.employeeManagement.model;

public class employeeModel {

	private int E_ID;
	private String E_name;
	private String E_Mobile;
	private String E_email;
	
	
	public employeeModel() {
	
	}
	
	public employeeModel(int e_ID, String e_name, String e_Mobile, String e_email) {
		super();
		E_ID = e_ID;
		E_name = e_name;
		E_Mobile = e_Mobile;
		E_email = e_email;
	}

	public int getE_ID() {
		return E_ID;
	}

	public void setE_ID(int e_ID) {
		E_ID = e_ID;
	}

	public String getE_name() {
		return E_name;
	}

	public void setE_name(String e_name) {
		E_name = e_name;
	}

	public String getE_Mobile() {
		return E_Mobile;
	}

	public void setE_Mobile(String e_Mobile) {
		E_Mobile = e_Mobile;
	}

	public String getE_email() {
		return E_email;
	}

	public void setE_email(String e_email) {
		E_email = e_email;
	}
	
	
}
