package com.fogetti.webscraping.service;

import org.jinstagram.entity.users.basicinfo.UserInfo;
import org.jinstagram.exceptions.InstagramException;

public interface IInstagram {

	UserInfo getCurrentUserInfo() throws InstagramException;
	String getUrl(String mediaId) throws InstagramException;
	String getContent(String mediaId) throws InstagramException;
	Integer getLikeCount(String mediaId) throws InstagramException;
	Integer getCommentCount(String mediaId) throws InstagramException;
	String getMentionedUrl(String mediaId) throws InstagramException;
	String getMentionedBio(String mediaId) throws InstagramException;
	Integer getFollowerCount(String mediaId) throws InstagramException;
}
