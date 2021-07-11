package com.company.history;

import java.awt.geom.RectangularShape;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.company.check.Check;
import com.company.exception.AccountNotFoundException;
import com.company.factory.Factory;
public class GetHistory implements History{
    int accountNo;
    public GetHistory(int accountNo) throws AccountNotFoundException{
    	Connection con = null;
		try {
			con = Factory.addConnection();
			System.out.println(accountNo);
			Check.checkAccountPresence(accountNo, con);
		}catch(SQLException e) {
			System.out.println(e);
		}
		this.accountNo = accountNo;
	}
	public void getHistory(String dateName) {
		Connection con = null;
		try {
			con = Factory.addConnection();
			String get = "select * from history where accountNo = "+ accountNo +" and date >= '"+ dateName+"'" + " order by date";
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery(get);
			int amount = 0;
			String type = null;
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
			String formatedDate;
			int temp = 1;
			boolean checkrs = rs.next();
			if(!checkrs) {
				System.out.println("no transaction made for this account since " + dateName);
			}else {
				System.out.println("---------------------------------------------------------------");
				while(checkrs) {
					amount = rs.getInt("amount");
					type = rs.getString("type");
					date = rs.getDate("date");
					formatedDate = sdf.format(date);
					System.out.println("\t"+ temp++ +"\t" + formatedDate + "\t" + amount+ "\t" + type);
					checkrs = rs.next();
				}
				System.out.println("---------------------------------------------------------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();	
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void getHistory(String from, String to) {
		Connection con = null;
		try {
			con = Factory.addConnection();
			String get = "select * from history where accountNo = '"+ accountNo + "' and date between '"+ from +"' and '"+ to + "'order by date";
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery(get);
			int amount = 0;
			String type = null;
			
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
			String formatedDate;
			
			int temp = 1;
			boolean checkrs = rs.next();
			if(!checkrs) {
				System.out.println("no transaction made for this account between " + from + " and " + to);
			}else {
				System.out.println("---------------------------------------------------------------");
				while(checkrs) {
					amount = rs.getInt("amount");
					type = rs.getString("type");
					date = rs.getDate("date");
					formatedDate = sdf.format(date);
					System.out.println("\t" + temp++ +"\t"+ formatedDate + "\t" + amount+ "\t" + type);
					checkrs = rs.next();
				}
				System.out.println("---------------------------------------------------------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void getHistory() {
		Connection con = null;
		try {
			con = Factory.addConnection();
			String get = "select * from history where accountNo = " + accountNo + " order by id desc limit 10";
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery(get);
			int amount = 0;
			String type = null;
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
			String formatedDate;
			int temp = 1;
			boolean checkrs = rs.next();
			if(!checkrs) {
				System.out.println("no transaction made for this account");
			}else {
				System.out.println("---------------------------------------------------------------");
				while(checkrs) {
					amount = rs.getInt("amount");
					type = rs.getString("type");
					date = rs.getDate("date");
					formatedDate = sdf.format(date);
					System.out.println("\t" + temp++ +"\t"+ formatedDate + "\t" + amount+ "\t" + type);
					checkrs = rs.next();
				}
				System.out.println("---------------------------------------------------------------");
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void getHistory(int month) {
		Connection con = null;
		try {
			con = Factory.addConnection();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date(System.currentTimeMillis()));
			calendar.add(Calendar.MONTH, -month);
			
			java.util.Date dateName = calendar.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dateNameString = sdf.format(dateName);
			String get = "select * from history where accountNo = " + accountNo + " and date >= '"+ dateNameString + "'"+"order by date";
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery(get);
			int amount = 0;
			String type = null;
			Date date = new Date(System.currentTimeMillis());
			String formatedDate;
			int temp = 1;
			boolean checkrs = rs.next();
			if(!checkrs) {
				System.out.println("no transaction made for last " + month + " month");
			}else {
				System.out.println("---------------------------------------------------------------");
				while(checkrs) {
					amount = rs.getInt("amount");
					type = rs.getString("type");
					date = rs.getDate("date");
					formatedDate = sdf.format(date);
					System.out.println("\t" + temp++ + "\t" + formatedDate + "\t" + amount+ "\t" + type);
					checkrs = rs.next();
				}
				System.out.println("---------------------------------------------------------------");
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}