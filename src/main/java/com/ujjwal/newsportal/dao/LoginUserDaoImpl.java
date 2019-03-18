package com.ujjwal.newsportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.ujjwal.newsportal.db.DatabaseConnect;
import com.ujjwal.newsportal.model.News;

public class LoginUserDaoImpl implements LoginUserDao {
	Connection connect = null;

	@Override
	public void loginuser(String username, String password) {
		if (connect != null) {
			Connection conn = DatabaseConnect.getMysqlConnection();
			System.out.println("checking control for us and ps");
			//String sql = "select * from login where username='" + username + "' and password='" + password + "'";
				String sql1 = "Select * FROM login";
				try {
					Statement stmt = connect.createStatement();
					ResultSet rs = stmt.executeQuery(sql1);
					LoginUserDao ud  = new LoginUserDaoImpl() ;

					System.out.println("checking control = " + rs.next());
					while (rs.next()) {
						
						ud.loginuser(username, password);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
			   System.out.println("Invalid Access");
			}
	}
	@Override
	public void registeruser(String fname, String lname, String email, String username, String password) {
		if (connect != null) {
			Connection conn = DatabaseConnect.getMysqlConnection();
			String sql = "INSERT INTO login (id, username, password, fname, lname, email) VALUES (NULL, ?, ?, ?, ?, ?)";

			// "INSERT INTO login (id, username, password, fname, lname, email) VALUES
			// (NULL, ?, ?, ?,?, ?)";
			PreparedStatement preparedStatement;
			try {
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, username);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, fname);
				preparedStatement.setString(4, lname);
				preparedStatement.setString(5, email);
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean isValiduser(String username, String password) {
		try {
			String sql = "select * from login where username='" + username + "' and password='" + password + "'";
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			boolean hasUser = rs.next();
			return hasUser;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void connection() {
		connect = DatabaseConnect.getMysqlConnection();
	}

	@Override
	public boolean findUserByKey(String key, String value) {
		try {
			String sql = "SELECT * FROM login WHERE" + key + " LIKE " + value;
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			boolean userExists = rs.next();
			return userExists;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}