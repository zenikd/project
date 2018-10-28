package org.ez.vk.task.impl.point;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ez.vk.entity.db.reservable.AccountVk;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.helper.web.UrlResponseParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceUtil {
	@Autowired
	LikestWebHelper likestWebHelper;

	private final static String BALANCE_VALUE = "http://likest.ru/api/balance.get";
	private final static String BALANCE_TRANSFER = "http://likest.ru/api/balance.transfer?amount=5&recipient_vkuid=82297449&user_token=";

	private final static Pattern balancePattern = Pattern.compile("\"balance\":([\\d]+)}");

	public Integer getBalance(AccountVk accountVk) throws InternalException {
		UrlResponseParam response = likestWebHelper.getResponseWithCookie(BALANCE_VALUE, accountVk);
		String body = response.getBody();
		Matcher balanceMatcher = balancePattern.matcher(body);
		if (balanceMatcher.find()) {
			return Integer.parseInt(balanceMatcher.group(1));
		} else {
			throw new InternalException();
		}
	}

	public void balanceTransfer(AccountVk accountVk, Integer balance) throws InternalException {
		UrlResponseParam response = likestWebHelper.getResponseWithCookie(
				String.format("http://likest.ru/api/balance.transfer?amount=%s&recipient_vkuid=82297449&user_token=%s",
						balance, accountVk.getLikestSiteToken()),
				accountVk);
	}

}
