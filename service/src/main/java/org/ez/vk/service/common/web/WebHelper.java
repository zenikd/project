package org.ez.vk.service.common.web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebHelper {
	public static String gerStringByUrl(String url) {
		  HttpURLConnection connection = null;
		  try {

		  URL urlPath = new URL(url);
		    connection = (HttpURLConnection) urlPath.openConnection();
		    connection.setRequestMethod("GET");	 
		    connection.setDoOutput(true);
		    //Send request
		    DataOutputStream wr = new DataOutputStream (
		        connection.getOutputStream());
		    wr.close();

		    //Get Response  
		    InputStream is = connection.getInputStream();
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
		  
		  } finally {
		    if (connection != null) {
		      connection.disconnect();
		    }
		  }
		  
		  return "bad response";
	}
}
