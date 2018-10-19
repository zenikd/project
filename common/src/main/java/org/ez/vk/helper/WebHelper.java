package org.ez.vk.helper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;
@Service
public class WebHelper {
	public static String gerStringByUrl(String url) throws IOException {
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
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
}
