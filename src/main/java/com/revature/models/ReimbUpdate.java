package com.revature.models;

public class ReimbUpdate {
	
	private int id;
	private int status;
	
	public ReimbUpdate() {
		super();
	}

	public ReimbUpdate(int id, int status) {
		super();
		this.id = id;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
