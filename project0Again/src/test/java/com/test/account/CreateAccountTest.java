package com.test.account;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.company.account.CreateAccount;
import com.company.account.CreateAccountDB;
import com.company.exception.InvalidPhoneNo;
import com.company.exception.PhoneAlreadyExistException;
import com.company.transfer.TransactionHistory;

class CreateAccountTest {
	static CreateAccount createAccount;
	static CreateAccountDB createAccountDB;
	@BeforeEach
	static void accountObj() throws SQLException, PhoneAlreadyExistException, InvalidPhoneNo{
		 createAccount = new CreateAccount("2345678123", 500);
		 createAccountDB =  new CreateAccountDB();
	}
	@Test
	void createTest() {
		int amount = 500;
	    Pattern pattern = Pattern.compile("[0-9]{10}");
		String phone = createAccount.getPhoneNo();
		Matcher match = pattern.matcher(phone);
		assertTrue(match.find());
		assertTrue(createAccount.getAmount() >= amount);
	}
	@Test
	void transactionHistoryTest(){
		TransactionHistory txrHistory = new TransactionHistory(1);
		txrHistory.setType("Debit");
		String type = "Debit";
		int accountNo = 1;
		assertEquals(type,txrHistory.getType());
		assertEquals(accountNo,txrHistory.getAccountNo());
	}
}
