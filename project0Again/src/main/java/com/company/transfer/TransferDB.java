package com.company.transfer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.company.check.Check;
import com.company.exception.AccountNotFoundException;
import com.company.exception.InsufficientAmountException;
import com.company.factory.Factory;

public class TransferDB implements Transfer {
	Logger logger = Logger.getLogger("log");
	Connection con = null;
	public void amountTransfer(int amount, int from, int to) {

		try {
			con = Factory.addConnection();
			
			con.setAutoCommit(false);
			
			Check.checkAccountPresence(from, con);
			
			Check.checkAccountPresence(to, con);
			
			List<Integer> getList= GetAndUpdateAmount.getAmount(from, to, con);
			
			GetAndUpdateAmount.updateAmount(amount, getList, from, to, con);
			
			CreateHistory create = new CreateHistory();
			
			create.createHistory(amount, from, to, con);
			
			con.commit();
			logger.info("amount transfered successfully");
			
			
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (InsufficientAmountException e) {
			logger.error(e.getMessage());
		} catch (AccountNotFoundException e) {
			logger.error(e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
}
