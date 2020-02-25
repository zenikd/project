package org.ez.vk.task.impl.point;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ez.vk.entity.db.AccountVk;
import org.ez.vk.exception.internal.CaptchaException;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.helper.web.UrlRequestParam;
import org.ez.vk.helper.web.UrlResponseParam;
import org.ez.vk.helper.web.WebHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.queries.likes.LikesType;

@Service
public class LikestAuthorizer {
	protected final static VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());

	@Autowired
	WebHelper webHelper;

	private final static Pattern likeIDPattern = Pattern.compile("\"like_id\":\"([\\d]+)\"");
	private final static Pattern likeHrefPattern = Pattern.compile("\"like_like\":\"([^\"]+)\"");
	private final static Pattern userTokenPattern = Pattern.compile("\"user_token\":\"([^\"]+)\"");
	private final static Pattern ownerIdPattern = Pattern.compile("[a-z]+(-?[0-9]+)_");
	private final static Pattern postIdPattern = Pattern.compile("_([\\d]+)");
	private final static Pattern likeTypePattern = Pattern.compile("/([a-z]+)");

	public void authorize(AccountVk accountVk) throws InternalException {
		UserActor actor = accountVk.getUserActor();
		String jsonLikeHref = getJsonLikeHref(actor.getId());
		String hrefToLike = getHrefToLike(jsonLikeHref);
		Integer ownerId = getOwnerId(hrefToLike);
		Integer postId = getPostId(hrefToLike);
		LikesType likeType = getLikesType(hrefToLike);

		addLike(actor, ownerId, postId, likeType);

		String likeId = getLikeId(jsonLikeHref);
		setAuthParam(accountVk, actor, likeId);

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		deleteLike(actor, ownerId, postId, likeType);
	}

	private void setAuthParam(AccountVk accountVk, UserActor actor, String likeId) throws InternalException {
		UrlRequestParam urlRequestParam = new UrlRequestParam(
				String.format("http://likest.ru/api/users.login?authname=id%s&like_id=%s", actor.getId(), likeId));

		UrlResponseParam urlResponseParam = webHelper.urlResponseParam(urlRequestParam);

		Matcher userTokenMatcher = userTokenPattern.matcher(urlResponseParam.getBody());
		String userToken;
		if (userTokenMatcher.find()) {
			accountVk.setLikestSiteToken(userTokenMatcher.group(1));
			accountVk.setLikestSiteCookie(urlResponseParam.getCookie());
		} else {
			throw new InternalException();
		}
	}

	private void addLike(UserActor actor, Integer ownerId, Integer postId, LikesType likeType)
			throws InternalException {
		try {
			vk.likes().add(actor, likeType, postId).ownerId(ownerId).execute();
		} catch (ApiException e) {
			throw new CaptchaException();
		} catch (ClientException e) {
			throw new InternalException();
		}
	}

	private String getJsonLikeHref(Integer id) throws InternalException {
		return webHelper.getStringByUrl(String.format("http://likest.ru/api/users.login?authname=id%s", id));
	}

	private String getHrefToLike(String jsonLikeHref) throws InternalException {
		Matcher likeHrefMatacher = likeHrefPattern.matcher(jsonLikeHref);
		if (likeHrefMatacher.find()) {
			return likeHrefMatacher.group(1);
		} else {
			throw new InternalException();
		}
	}

	private Integer getOwnerId(String hrefToLike) throws InternalException {
		Matcher ownerIdMatacher = ownerIdPattern.matcher(hrefToLike);
		if (ownerIdMatacher.find()) {
			return Integer.parseInt(ownerIdMatacher.group(1));
		} else {
			throw new InternalException();
		}
	}

	private Integer getPostId(String hrefToLike) throws InternalException {
		Matcher postIdMatacher = postIdPattern.matcher(hrefToLike);
		if (postIdMatacher.find()) {
			return Integer.parseInt(postIdMatacher.group(1));
		} else {
			throw new InternalException();
		}
	}

	private LikesType getLikesType(String hrefToLike) throws InternalException {
		Matcher likeTypeMatcher = likeTypePattern.matcher(hrefToLike);
		if (likeTypeMatcher.find()) {
			String likeType = likeTypeMatcher.group(1);
			if (likeType.equals("wall")) {
				return LikesType.POST;
			} else {
				return LikesType.PHOTO;
			}

		} else {
			throw new InternalException();
		}
	}

	private String getLikeId(String jsonLikeHref) throws InternalException {
		Matcher likeIDMatcher = likeIDPattern.matcher(jsonLikeHref);
		String likeID;
		if (likeIDMatcher.find()) {
			return likeIDMatcher.group(1);
		} else {
			throw new InternalException();
		}
	}

	private void deleteLike(UserActor actor, Integer ownerId, Integer postId, LikesType likeType)
			throws InternalException {
		try {
			vk.likes().delete(actor, likeType, postId).ownerId(ownerId).execute();
		} catch (ApiException e) {
			throw new CaptchaException();
		} catch (ClientException e) {
			throw new InternalException();
		}
	}

}
