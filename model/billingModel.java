package com.elecgrid.crud.billingManagement.model;

public class billingModel {
	
	private int bill_id;
	private String bill_desc;
	private String bill_no;
	private int unit;
	
	public billingModel() {
		
	}
	
	public billingModel(int bill_id,String bill_desc, int unit, String bill_no) {
		super();
		this.bill_id = bill_id;
		this.bill_desc = bill_desc;
		this.unit = unit;
		this.bill_no = bill_no;
	}
	public int getBill_id() {
		return bill_id;
	}

	public void setBill_id(int bill_id) {
		this.bill_id = bill_id;
	}

	public String getBill_desc() {
		return bill_desc;
	}

	public void setBill_desc(String bill_desc) {
		this.bill_desc = bill_desc;
	}

	public String getBill_no() {
		return bill_no;
	}

	public void setBill_no(String bill_no) {
		this.bill_no = bill_no;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	

}
