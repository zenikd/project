package org.ez.vk.task.impl.point;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.ez.vk.entity.db.reservable.AccountVk;
import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.helper.web.UrlResponseParam;
import org.ez.vk.task.impl.point.entity.CommentParam;
import org.ez.vk.task.impl.point.entity.PollParam;
import org.ez.vk.task.impl.point.exception.TaskReservedException;
import org.ez.vk.task.impl.point.task.CommentTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LikestTaskHelper {
	@Autowired
	LikestWebHelper webHelper;
	
	private static final Logger log = Logger.getLogger(CommentTask.class);

	private final static Pattern oidPattern = Pattern.compile("oid\":\"([\\d]+)");
	private final static Pattern rewardPattern = Pattern.compile("reward\":\"([\\d]+)");
	private final static Pattern taskPattern = Pattern.compile("/[a-z]+([\\d]+)\"");

	public Integer getDefaultTask(TaskParam taskParam, AccountVk accountVk) throws InternalException {
		String oid = taskParam.getOid();
		UrlResponseParam response = webHelper.getResponseWithCookie("http://likest.ru/api/orders.accept?oid=" + oid, accountVk);
		Matcher m = taskPattern.matcher(response.getBody());
		if (m.find()) {
			return Integer.parseInt(m.group(1));
		}
		log.info("Task is no longer available");
		throw new TaskReservedException();
	}

	public CommentParam getCommentTask( AccountVk accountVk) throws InternalException {
		UrlResponseParam response = webHelper.getResponseWithCookie("http://likest.ru/api/orders.getComments", accountVk);
		ObjectMapper mapper = new ObjectMapper();
		try {
			return (CommentParam) mapper.readValue(response.getBody(), CommentParam.class);
		} catch (IOException e) {
			log.info("Task is no longer available");
			throw new TaskReservedException();
		}
	}
	
	public PollParam getPollTask( AccountVk accountVk) throws InternalException {
		UrlResponseParam response = webHelper.getResponseWithCookie("http://likest.ru/api/orders.getPolls", accountVk);
		ObjectMapper mapper = new ObjectMapper();
		try {
			return (PollParam) mapper.readValue(response.getBody(), PollParam.class);
		} catch (IOException e) {
			log.info("Task is no longer available");
			throw new TaskReservedException();
		}
	}

	public TaskParam getOid(String url, AccountVk accountVk) throws InternalException {
		TaskParam taskParam = new TaskParam();

		UrlResponseParam response = webHelper.getResponseWithCookie(url, accountVk);
		Matcher oidMacher = oidPattern.matcher(response.getBody());
		if (oidMacher.find()) {
			taskParam.setOid(oidMacher.group(1));
		}

		Matcher rewardMatcher = rewardPattern.matcher(response.getBody());
		if (rewardMatcher.find()) {
			taskParam.setReward(Integer.parseInt(rewardMatcher.group(1)));
		}
		return taskParam;

	}
}
