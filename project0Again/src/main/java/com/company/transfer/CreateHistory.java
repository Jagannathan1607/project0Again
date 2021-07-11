package com.company.transfer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.company.factory.Factory;

public class CreateHistory {
	
	public void createHistory(int amount,int from, int to, Connection con) throws SQLException {
		
		Date date = new Date(System.currentTimeMillis());
		
		TransactionHistory fromHistory = new TransactionHistory(from);
		TransactionHistory toHistory = new TransactionHistory(to);
		
		fromHistory.setType("debit");
		toHistory.setType("credit");
		
		String history = "insert into history (accountNo,amount,type,date)values (?,?,?,?)";
		PreparedStatement historyState = con.prepareStatement(history);
		historyState.setInt(1, fromHistory.getAccountNo());
		historyState.setInt(2, amount);
		historyState.setString(3,fromHistory.getType());
		historyState.setDate(4, date);
		historyState.execute();
		historyState = con.prepareStatement(history);
		historyState.setInt(1, toHistory.getAccountNo());
		historyState.setInt(2, amount);
		historyState.setString(3,toHistory.getType());
		historyState.setDate(4, date);
		historyState.execute();
//	    System.out.println("history created !!!");
	}
}
