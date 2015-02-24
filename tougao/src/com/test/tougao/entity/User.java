package com.test.tougao.entity;

public class User {
	private String user_id;//主键
    private String user_account;//账户
    private String user_name;//用户姓名
    private String user_password;//密码
    private String user_active;//是否激活
    private String user_role;//用户角色
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}

	public String getUser_account() {
		return user_account;
	}
    
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_name() {
		return user_name;
	}
	
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_active(String user_active) {
		this.user_active = user_active;
	}

	public String getUser_active() {
		return user_active;
	}

	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}

	public String getUser_role() {
		return user_role;
	}
//	public User(String id,String account,String name,String password,String active,String role){
//		super();
//		this.user_id = id;
//		this.user_account =  account;
//		this.user_name = name;
//		this.user_password = password;
//		this.user_active = active;
//		this.user_role = role;
//	}

	public User() {
		// TODO Auto-generated constructor stub
		super();
	}

}
