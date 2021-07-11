package com.company.history;

public interface History {
	public void getHistory();
	
	public void getHistory(int month);
	
	public void getHistory(String fromDate);
	
	public void getHistory(String fromDate, String toDate);
}
