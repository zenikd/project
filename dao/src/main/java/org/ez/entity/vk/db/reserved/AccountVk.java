package org.ez.entity.vk.db.reserved;

import com.vk.api.sdk.client.actors.UserActor;

public class AccountVk extends AbstractReservedEntity{
	protected String type;
	private UserActor userActor;
	private String customAccountUrl;
	private String defaultAccountUrl;
	private String userLogin;
	private String userPass;
	private String userName;
	
	public String getCustomAccountUrl() {
		return customAccountUrl;
	}


	public void setCustomAccountUrl(String customAccountUrl) {
		this.customAccountUrl = customAccountUrl;
	}


	public String getDefaultAccountUrl() {
		return defaultAccountUrl;
	}


	public void setDefaultAccountUrl(String defaultAccountUrl) {
		this.defaultAccountUrl = defaultAccountUrl;
	}


	public String getUserLogin() {
		return userLogin;
	}


	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}


	public String getUserPass() {
		return userPass;
	}


	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public UserActor getUserActor() {
		return userActor;
	}

	public void setUserActor(UserActor userActor) {
		this.userActor = userActor;
	}

}
