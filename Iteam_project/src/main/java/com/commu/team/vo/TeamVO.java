package com.commu.team.vo;

import java.util.Date;


public class TeamVO {

	private int bno;
	private String title;
	private String content;
	private String writer;
	private int people;
	private String skill;
	private Date regdate;
	private int cnt;
	private int recommend;
	private String complete;
	private int reply_count;
	
	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public int getPeople() {
		return people;
	}

	public void setPeople(int people) {
		this.people = people;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getComplete() {
		return complete;
	}

	public void setComplete(String complete) {
		this.complete = complete;
	}

	public int getReply_count() {
		return reply_count;
	}

	public void setReply_count(int reply_count) {
		this.reply_count = reply_count;
	}

	
}
