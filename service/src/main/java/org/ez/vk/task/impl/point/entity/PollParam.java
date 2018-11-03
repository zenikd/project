package org.ez.vk.task.impl.point.entity;

public class PollParam {
	private String status;
	private String link;
	private String reward;
	private String poll_owner;
	private String poll_id;
	private String poll_answer;

	public String getPoll_owner() {
		return poll_owner;
	}

	public void setPoll_owner(String poll_owner) {
		this.poll_owner = poll_owner;
	}

	public String getPoll_id() {
		return poll_id;
	}

	public void setPoll_id(String poll_id) {
		this.poll_id = poll_id;
	}

	public String getPoll_answer() {
		return poll_answer;
	}

	public void setPoll_answer(String poll_answer) {
		this.poll_answer = poll_answer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

}
