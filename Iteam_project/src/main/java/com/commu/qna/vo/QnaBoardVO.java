package com.commu.qna.vo;

import java.util.Date;

public class QnaBoardVO {
	
	private int bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private int cnt;
	private int recommend;
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
	
	public int getReply_count() {
		return reply_count;
	}
	
	public void setReply_count(int reply_count) {
		this.reply_count = reply_count;
	}
	
	

}
