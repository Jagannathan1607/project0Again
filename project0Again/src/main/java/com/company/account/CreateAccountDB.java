package com.company.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.company.check.Check;
import com.company.exception.InvalidPhoneNo;
import com.company.exception.PhoneAlreadyExistException;
import com.company.factory.Factory;

public class CreateAccountDB implements Account{
	Logger logger = Logger.getLogger("log");
	@Override
	public void add(CreateAccount createAccount) {
		Connection con = null;
		try {
			con = Factory.addConnection();
			con.setAutoCommit(false);
			
			String phoneNo = createAccount.getPhoneNo();	
			Check.checkPhoneRegex(phoneNo,con);
			Check.checkPhoneExist(phoneNo,con);
			String sql = "insert into accounts (amount,phoneNo) values(?,?)";
			PreparedStatement state = con.prepareStatement(sql);
			state.setLong(1, createAccount.getAmount());
			state.setString(2, createAccount.getPhoneNo());
			int rowCount = state.executeUpdate();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("select accountNo from accounts where phoneNo = " + phoneNo);
			rs.next();
			int accountNo = rs.getInt("accountNo");
			if(rowCount == 1) {
				logger.info("account created\nyour bank account no : " + accountNo);
			}
			con.commit();
		}catch (SQLException e) {
			try {
				con.rollback();
				logger.error(e.getMessage());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (InvalidPhoneNo e) {
			logger.error(e.getMessage());
		} catch (PhoneAlreadyExistException e) {
			logger.error(e.getMessage());
		} finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}