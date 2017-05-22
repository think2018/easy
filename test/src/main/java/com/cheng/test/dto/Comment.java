package com.cheng.test.dto;

public class Comment {

	public Long id;
	public String comment;
	public String createTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Comment(Long id, String comment, String createTime) {
		super();
		this.id = id;
		this.comment = comment;
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", comment=" + comment + ", createTime=" + createTime + "]";
	}
}
