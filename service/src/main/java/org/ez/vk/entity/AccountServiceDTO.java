package org.ez.vk.entity;

public class AccountServiceDTO {	
	private String login;
	private String pass;
	private String type;

	public AccountServiceDTO(String login, String pass, String type) {	
		this.login = login;
		this.pass = pass;
		this.type = type;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
