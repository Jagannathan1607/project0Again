package com.company.account;

public class CreateAccount{
	private String phoneNo;
	private long amount;
	public CreateAccount(String phoneNo, int amount){
		this.phoneNo = phoneNo;
		this.amount = amount;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
}
