package com.ujjwal.newsportal.dao;

public interface LoginUserDao {
	public void connection();
	public void loginuser(String username, String password);
	public void registeruser(String fname, String lname, String email, String username, String password);
	public boolean isValiduser(String username, String password);
	public boolean findUserByKey(String key, String value);

}
