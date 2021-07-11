package com.company;

import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.company.account.CreateAccount;
import com.company.account.CreateAccountDB;
import com.company.exception.AccountNotFoundException;
import com.company.history.GetHistory;
import com.company.transfer.Transfer;
import com.company.transfer.TransferDB;

public class Manager {
	static Logger logger = Logger.getLogger("log");
	public static void manager() throws SQLException{
		Scanner sc = new Scanner(System.in);
		boolean num = true;
		while(num) {
			logger.info("\n1 - create an account\n2 - transfer amount\n3 - get transaction history\n0 - exit");
			int choose = sc.nextInt();
			switch(choose) {
			case 1 : 
				logger.info("enter phone number");
				sc.nextLine();
				String phoneNo = sc.nextLine();
				logger.info("enter deposit amount >= 500");
				
				int deposit = sc.nextInt();
				CreateAccountDB createdb = new CreateAccountDB();
				createdb.add(new CreateAccount(phoneNo, deposit));
				break;
				
			case 2 : 
				logger.info("enter amount to transfer");
				sc.nextLine();
				int amount = sc.nextInt();
	            
				logger.info("enter debiting bank account number");
				sc.nextLine();
				int from = sc.nextInt();
				logger.info("enter crediting bank account number");
				sc.nextLine();
				int to = sc.nextInt();
				Transfer transfer = new TransferDB();
				transfer.amountTransfer(amount, from, to);
				break;
			
			case 3 : 
				logger.info("enter account number to get history");
				sc.nextLine();
				int accountNo = sc.nextInt();
				GetHistory get;
				try {
					get = new GetHistory(accountNo);
				} catch (AccountNotFoundException e) {
					continue;
				}
				boolean temp = true;
				while(temp) {
					logger.info("1 - top 10 transactions\n2 - get transactions using last some month\n3 - get transactions using from date\n4 - get transactions using from and to date\n5 - close");
					int choice = sc.nextInt();
					switch(choice) {
					case 1 : 
						get.getHistory();
						break;	
					case 2 : 
						logger.info("enter last some month in number");
						sc.nextLine();
						int month = sc.nextInt();
						get.getHistory(month);
						break;
					case 3 : 
						logger.info("enter from date YYYY-MM-DD");
						sc.nextLine();
						String fromDateOnly = sc.nextLine();
						get.getHistory(fromDateOnly);
						break;
					case 4 : 
						logger.info("enter from date YYYY-MM-DD");
						sc.nextLine();
						String fromDate = sc.nextLine();
						logger.info("enter to date YYYY-MM-DD");
//						sc.nextLine();
						String toDate = sc.nextLine();
						get.getHistory(fromDate,toDate);
						break;
					case 5 :
						temp = false;
						break;
					default : 
						logger.info("choose correct option");
						break;
					}
				}
				break;
			case 0 : 
				num = false;
				break;
			default : 
				logger.info("choose correct option");
				break;
			}
		}
	}
}
