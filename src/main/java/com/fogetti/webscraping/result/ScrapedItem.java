package com.fogetti.webscraping.result;

import java.io.Serializable;

public class ScrapedItem implements Serializable {

	private static final long serialVersionUID = -4369005345593144531L;
	
	private String url;
	private String content;
	private Integer likeCount;
	private Integer commentCount;
	private String mentionedUrl;
	private String mentionedBio;
	private Integer followerCount;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}
	public Integer getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	public String getMentionedUrl() {
		return mentionedUrl;
	}
	public void setMentionedUrl(String mentionedUrl) {
		this.mentionedUrl = mentionedUrl;
	}
	public String getMentionedBio() {
		return mentionedBio;
	}
	public void setMentionedBio(String mentionedBio) {
		this.mentionedBio = mentionedBio;
	}
	public Integer getFollowerCount() {
		return followerCount;
	}
	public void setFollowerCount(Integer followerCount) {
		this.followerCount = followerCount;
	}
}
