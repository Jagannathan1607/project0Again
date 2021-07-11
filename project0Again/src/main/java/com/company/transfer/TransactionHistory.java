package com.company.transfer;

public class TransactionHistory {
	private int accountNo;
	private String type;
	public TransactionHistory(int accountNo) {
		this.accountNo = accountNo;
	}
	public int getAccountNo() {
		return accountNo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
