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
	@Autowired
	LikestAuthorizer likestAuthorizer;
	
	private final static Pattern oidPattern = Pattern.compile("oid\":\"([\\d]+)");
	private final static Pattern taskPattern = Pattern.compile("/[a-z]+([\\d]+)\"");
	

	public void earn() throws InternalException {
		List<AccountVk> listAccount = getListWorkAccount(1);
		AccountVk accountVk = listAccount.get(0);
		likestAuthorizer.authorize(accountVk);
		int a = 1;
		a++;
	}

}
