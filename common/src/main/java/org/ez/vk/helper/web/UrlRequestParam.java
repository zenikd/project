package org.ez.vk.helper.web;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ez.vk.exception.internal.EmptyRequest;
import org.ez.vk.exception.internal.InternalException;
import org.springframework.stereotype.Service;


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
