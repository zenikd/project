package org.ez.vk.helper.web;

public class UrlResponseParam {
	private String cookie;
	private String body;
	
	public UrlResponseParam(String body) {
		super();
		this.body = body;
	}
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
}
