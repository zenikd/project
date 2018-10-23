package org.ez.vk.task.impl.point;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ez.vk.entity.db.reservable.AccountVk;
import org.ez.vk.exception.internal.CaptchaException;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.helper.web.UrlRequestParam;
import org.ez.vk.helper.web.UrlResponseParam;
import org.ez.vk.helper.web.WebHelper;
import org.ez.vk.task.impl.RootTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

@Service
public class EarnPointTask2 extends RootTask {
	@Autowired
	WebHelper webHelper;

	private final static Pattern oidPattern = Pattern.compile("oid\":\"([\\d]+)");
	private final static Pattern taskPattern = Pattern.compile("/[a-z]+([\\d]+)\"");
	private final static Pattern statusIDPattern = Pattern.compile("\"status_id\":\"([\\d]+)\"");
	private final static Pattern statusTextPattern = Pattern.compile("\"status_status\":\"([^\"]+)\"");
	private final static Pattern userTokenPattern = Pattern.compile("\"user_token\":\"([^\"]+)\"");

	public void earn() throws InternalException {
		List<AccountVk> listAccount = getListWorkAccount(1);
		AccountVk accountVk = listAccount.get(0);
		authorize(accountVk);
		int a = 1;
		a++;
	}

	private void authorize(AccountVk accountVk) throws InternalException {
		UserActor actor =accountVk.getUserActor();
		String statusResponse = webHelper.getStringByUrl(
				String.format("http://likest.ru/api/users.login?authname=id%s&validation=status", actor.getId()));
		Matcher statusTextMatcher = statusTextPattern.matcher(statusResponse);
		String status;
		if (statusTextMatcher.find()) {
			status = statusTextMatcher.group(1);
		} else {
			throw new InternalException();
		}

		try {
			vk.status().set(actor).text(status).execute();
		} catch (ApiException e) {
			throw new CaptchaException();
		} catch (ClientException e) {
			throw new InternalException();
		}

		Matcher statusIdMatcher = statusIDPattern.matcher(statusResponse);
		String statusId;
		if (statusIdMatcher.find()) {
			statusId = statusIdMatcher.group(1);
		} else {
			throw new InternalException();
		}

		UrlRequestParam urlRequestParam = new UrlRequestParam(
				String.format("http://likest.ru/api/users.login?authname=id%s&status_id=%s", actor.getId(), statusId));

		UrlResponseParam urlResponseParam =  webHelper.urlResponseParam(urlRequestParam);
		
		Matcher userTokenMatcher = userTokenPattern.matcher(urlResponseParam.getBody());
		String userToken;
		if (userTokenMatcher.find()) {
			accountVk.setLikestSiteToken(userTokenMatcher.group(1));
			accountVk.setLikestSiteCookie(urlResponseParam.getCookie());
		} else {
			throw new InternalException();
		}

	}

}
