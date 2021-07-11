package com.company.check;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

import com.company.exception.AccountNotFoundException;
import com.company.exception.InvalidPhoneNo;
import com.company.exception.PhoneAlreadyExistException;
import com.company.factory.Factory;

public class Check {
	
	public static void checkPhoneExist(String phoneNo, Connection con) throws SQLException, PhoneAlreadyExistException{
		String checkPhone = "select phoneNo from accounts where phoneNo = " + phoneNo;
		Statement temp = con.createStatement();
		ResultSet rs = temp.executeQuery(checkPhone);
		if(rs.next()) {
			throw new PhoneAlreadyExistException("phone number already exists");
		}
	}
	public static void checkPhoneRegex(String phoneNo, Connection con) throws InvalidPhoneNo {
		if(!Pattern.matches("[0-9]{10}", phoneNo)) {
			throw new InvalidPhoneNo("enter valid phone number");
		}
	}
	public static void checkAccountPresence(int accountNo, Connection con) throws SQLException, AccountNotFoundException {
		String checkAcc = "select accountNo from accounts where accountNo = " + accountNo;
		Statement statement = con.createStatement();
		ResultSet rsCheck = statement.executeQuery(checkAcc);
		if(!rsCheck.next()) {
			throw new AccountNotFoundException("no account present with account number "+ accountNo);
		}
	}
}
