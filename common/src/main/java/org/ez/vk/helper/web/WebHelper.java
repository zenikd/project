package org.ez.vk.helper.web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.ez.vk.exception.internal.InternalException;
import org.springframework.stereotype.Service;

@Service
public class WebHelper {
	public String getStringByUrl(String url) throws InternalException {
		HttpURLConnection connection = null;
		try {

			URL urlPath = new URL(url);

			connection = (HttpURLConnection) urlPath.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);

			// Send request
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.close();

			// Get Response
			InputStream is = null;

			is = connection.getInputStream();

			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
			String line;
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new InternalException();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	public  UrlResponseParam urlResponseParam(UrlRequestParam urlParam) throws InternalException {
		HttpURLConnection connection = null;
		try {

			URL urlPath = new URL(urlParam.getUrl());
			
			connection = (HttpURLConnection) urlPath.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Cookie",
					urlParam.getCookie());
			connection.setDoOutput(true);
			
			// Send request
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.close();

			// Get Response
			InputStream is = null;

			is = connection.getInputStream();

			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
			String line;
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			UrlResponseParam urlResponseParam = new UrlResponseParam(response.toString());
			Object cookie = connection.getHeaderFields().get("Set-Cookie");
			if(cookie != null)
			urlResponseParam.setCookie(removeCookieBacket(cookie.toString()));
			return urlResponseParam;
		} catch (Exception e) {
			e.printStackTrace();
			throw new InternalException();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	private String removeCookieBacket(String cookie) {
		return cookie.substring(1, cookie.length() - 1);
	}

}
