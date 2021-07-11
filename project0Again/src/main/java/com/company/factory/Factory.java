package com.company.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
public class Factory {
	static Properties props = new Properties();
	static {
		try {
			FileInputStream fis = new FileInputStream("./src/main/resources/dataBase.properties");
			props.load(fis);
			Class.forName(props.getProperty("driver"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection addConnection() throws SQLException {
		String url = props.getProperty("url");
		String username = props.getProperty("username");
		String password = props.getProperty("password");
		Connection con = DriverManager.getConnection(url,username,password);
		return con;
	}
	
}
