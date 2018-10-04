package org.ez.vk.service.impl.accountservice;

public class AccountFSParam {
	private String fullName;
	private String customPath;
	private String defaultpath;
	private String login;
	private String pass;

	public AccountFSParam(String customPath, String defaultpath, String login, String pass) {
		super();
		this.customPath = customPath;
		this.defaultpath = defaultpath;
		this.login = login;
		this.pass = pass;
	}

	public String getCustomPath() {
		return customPath;
	}

	public void setCustomPath(String customPath) {
		this.customPath = customPath;
	}

	public String getFullName() {
		return fullName;
	}

	public String getDefaultpath() {
		return defaultpath;
	}

	public String getLogin() {
		return login;
	}

	public String getPass() {
		return pass;
	}
	
	

}
