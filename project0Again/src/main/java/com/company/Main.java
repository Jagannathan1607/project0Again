package com.company;

import java.sql.SQLException;

public class Main {
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
//		CreateAccount create = new CreateAccount("7305769596",5000);
//		System.out.println(create.getAccountNo());
//		CreateAccountDB accounts = new CreateAccountDB();
//		accounts.Add(create);
//		CreateAccount create1 = new CreateAccount("1234567890",1000);
//		accounts.Add(create1);
//		Transfer transfer = new Transfer();
//		transfer.amountTransfer(300, 1, 2);
//		GetHistory history = new GetHistory();
//		history.getHistory(1,"2021-03-03");
//		history.getHistory(1);
//		history.getHistory(1,"2021-03-03","2021-09-01");
//		history.getHistory(1,3);
		Manager.manager();
	}
}
