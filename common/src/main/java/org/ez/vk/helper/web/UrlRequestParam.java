package org.ez.vk.helper.web;

public class UrlRequestParam {
	private String url;
	private String cookie;

	public UrlRequestParam(String url) {
		this.url = url;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
