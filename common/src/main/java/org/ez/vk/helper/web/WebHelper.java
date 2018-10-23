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
	public  String getStringByUrl(String url) throws InternalException {
		HttpURLConnection connection = null;
		try {

			URL urlPath = new URL(url);
			
			connection = (HttpURLConnection) urlPath.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Cookie",
					"_ym_uid=1540235407581242675; _ym_d=1540235407; _ym_visorc_19178605=w; _ga=GA1.2.730229691.1540235407; _gid=GA1.2.1361554786.1540235407; has_js=1; _ym_isad=2; SESSeb75810f03a0895d9ff136a416f45948=GiN7YRwli4iiQooz4m97VubZ3nMI11DjTDiMUqD-Cd0");
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

	protected void setConnection(HttpURLConnection connection, URL urlPath) throws IOException {
		connection = (HttpURLConnection) urlPath.openConnection();
		connection.setRequestMethod("GET");
		connection.setDoOutput(true);
	}

}
