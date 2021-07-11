package com.company.transfer;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.company.exception.InsufficientAmountException;
import com.company.factory.Factory;

public class GetAndUpdateAmount {

	public static List<Integer> getAmount(int from, int to, Connection con) throws SQLException {
		
//		String f;
//		String t = "select amount,accountNo from accounts where accountNo = " + to;
		Statement state = con.createStatement();
		ResultSet result = state.executeQuery("select amount,accountNo from accounts where accountNo = " + from);
		int fromAmount = 0;
		int fromAccountNo = 0;
		while(result.next()) {
			fromAmount = result.getInt("amount");
			fromAccountNo = result.getInt("accountNo");
		}
		System.out.println(fromAccountNo);
		result = state.executeQuery("select amount,accountNo from accounts where accountNo = " + to);
		int toAmount = 0;
		int toAccountNo = 0;
		while(result.next()) {
			toAmount = result.getInt("amount");
			toAccountNo = result.getInt("accountNo");
		}
		System.out.println(toAccountNo);
//		System.out.println("got amount !!!");
//		String sql = "update accounts set amount = ";
//		System.out.println(fromAmount+ " " + fromAccountNo);
//		System.out.println(toAmount+ " " + toAccountNo);
		
		List<Integer> list = new ArrayList<>();
		list.add(fromAmount);
		list.add(toAmount);
		return list;
	}
	
	public static void updateAmount(int amount,List<Integer> list, int from, int to, Connection con) throws SQLException, InsufficientAmountException {
		
		int fromAmount = list.get(0);
		int toAmount = list.get(1);
		if(amount > fromAmount) {
			throw new InsufficientAmountException("Insufficient Amount to transfer\nyou have only " + fromAmount+ " in your account");
		}
		int updatedFrom = fromAmount - amount;
		int updatedTo = toAmount + amount;
		PreparedStatement prep = con.prepareStatement("update accounts set amount = ? where accountNo = " + from);
		prep.setInt(1, updatedFrom);
		PreparedStatement prep1 = con.prepareStatement("update accounts set amount = ? where accountNo = " + to);
		prep1.setInt(1, updatedTo);
		prep.executeUpdate();
//		int i = 1/0;
		
		prep1.executeUpdate();
//		System.out.println("amount updated !!!");
		System.out.println("balance...");
		System.out.println("Acc.no - " + from + "\t" + updatedFrom);
		System.out.println("Acc.no - " + to + "\t" + updatedTo);
	}
	
}
