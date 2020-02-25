package org.ez.vk.task.impl.point;

import org.ez.vk.entity.db.AccountVk;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.helper.web.UrlRequestParam;
import org.ez.vk.helper.web.UrlResponseParam;
import org.ez.vk.helper.web.WebHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class LikestWebHelper {
	@Autowired
	WebHelper webHelper;

	public UrlResponseParam getResponseWithCookie(String url, AccountVk accountVk) throws InternalException {
		UrlRequestParam urlRequestParam = new UrlRequestParam(url);
		urlRequestParam.setCookie(accountVk.getLikestSiteCookie());

		return webHelper.urlResponseParam(urlRequestParam);
	}
}
