package org.ez.vk.helper.web;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ez.vk.exception.internal.EmptyRequest;
import org.ez.vk.exception.internal.InternalException;
import org.springframework.stereotype.Service;

@Service
public class LikestSite extends WebHelper {
	private final static Pattern oidPattern = Pattern.compile("oid\":\"([\\d]+)");
	private final static Pattern taskPattern = Pattern.compile("/[a-z]+([\\d]+)\"");

	public String getTask(String url) throws InternalException {
		String oid = getOid(url);
		String task = getStringByUrl("http://likest.ru/api/orders.accept?oid=" + oid);
		Matcher m = taskPattern.matcher(task);
		if (m.find()) {
			return m.group(1);
		}

		throw new EmptyRequest();
	}

	private String getOid(String url) throws InternalException {

		String response = getStringByUrl(url);
		Matcher m = oidPattern.matcher(response);
		if (m.find()) {
			return m.group(1);
		}
		throw new EmptyRequest();

	}

	protected void setConnection(HttpURLConnection connection, URL urlPath) throws IOException {
		connection = (HttpURLConnection) urlPath.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Cookie",
				"_ym_uid=1540235407581242675; _ym_d=1540235407; _ym_visorc_19178605=w; _ga=GA1.2.730229691.1540235407; _gid=GA1.2.1361554786.1540235407; has_js=1; _ym_isad=2; SESSeb75810f03a0895d9ff136a416f45948=GiN7YRwli4iiQooz4m97VubZ3nMI11DjTDiMUqD-Cd0");
		connection.setDoOutput(true);
	}
}
