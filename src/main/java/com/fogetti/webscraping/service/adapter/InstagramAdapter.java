package com.fogetti.webscraping.service.adapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jinstagram.Instagram;
import org.jinstagram.entity.common.Caption;
import org.jinstagram.entity.media.MediaInfoFeed;
import org.jinstagram.entity.users.basicinfo.UserInfo;
import org.jinstagram.entity.users.feed.UserFeedData;
import org.jinstagram.exceptions.InstagramException;

import com.fogetti.webscraping.service.IInstagram;

public class InstagramAdapter implements IInstagram, Serializable {
	
	private static final long serialVersionUID = -9138788034148432935L;
	private final Instagram delegate;

	public InstagramAdapter(Instagram delegate) {
		this.delegate = delegate;
	}

	@Override
	public UserInfo getCurrentUserInfo() throws InstagramException {
		return delegate.getCurrentUserInfo();
	}

	@Override
	public String getUrl(String mediaId) throws InstagramException {
		MediaInfoFeed mediaInfo = delegate.getMediaInfo(mediaId);
		return mediaInfo.getData().getLink();
	}

	@Override
	public String getContent(String mediaId) throws InstagramException {
		MediaInfoFeed mediaInfo = delegate.getMediaInfo(mediaId);
		Caption caption = mediaInfo.getData().getCaption();
		return (caption == null) ? "" : caption.getText();
	}

	@Override
	public Integer getLikeCount(String mediaId) throws InstagramException {
		MediaInfoFeed mediaInfo = delegate.getMediaInfo(mediaId);
		return mediaInfo.getData().getLikes().getCount();
	}

	@Override
	public Integer getCommentCount(String mediaId) throws InstagramException {
		MediaInfoFeed mediaInfo = delegate.getMediaInfo(mediaId);
		return mediaInfo.getData().getComments().getCount();
	}

	@Override
	public String getMentionedUrl(String mediaId) throws InstagramException {
		MediaInfoFeed mediaInfo = delegate.getMediaInfo(mediaId);
		Caption captionObj = mediaInfo.getData().getCaption();
		String caption = (captionObj == null) ? "" : captionObj.getText();
		List<String> userList = new ArrayList<>();
		boolean found = true;
		while (found) {
			caption = StringUtils.substringAfter(caption, "@");
			if (!StringUtils.isBlank(caption)) {
				String user = StringUtils.substringBefore(caption, " ");
				userList.add(user);
			} else {
				found = false;
			}
		}
		StringBuilder builder = new StringBuilder();
		for (String tag : userList) {
			List<UserFeedData> users = delegate.searchUser(tag).getUserList();
			for (UserFeedData user : users) {
				if (tag.toLowerCase().equals(user.getUserName().toLowerCase()))
					builder.append(user.getProfilePictureUrl() + " ");
			}
		}
		return builder.toString();
	}

	@Override
	public String getMentionedBio(String mediaId) throws InstagramException {
		MediaInfoFeed mediaInfo = delegate.getMediaInfo(mediaId);
		Caption captionObj = mediaInfo.getData().getCaption();
		String caption = (captionObj == null) ? "" : captionObj.getText();
		List<String> userList = new ArrayList<>();
		boolean found = true;
		while (found) {
			caption = StringUtils.substringAfter(caption, "@");
			if (!StringUtils.isBlank(caption)) {
				String user = StringUtils.substringBefore(caption, " ");
				userList.add(user);
			} else {
				found = false;
			}
		}
		StringBuilder builder = new StringBuilder();
		for (String tag : userList) {
			List<UserFeedData> users = delegate.searchUser(tag).getUserList();
			for (UserFeedData user : users) {
				if (tag.toLowerCase().equals(user.getUserName().toLowerCase()))
					builder.append(user.getBio() + " ");
			}
		}
		return builder.toString();
	}

	@Override
	public Integer getFollowerCount(String mediaId) throws InstagramException {
		MediaInfoFeed mediaInfo = delegate.getMediaInfo(mediaId);
		String userId = mediaInfo.getData().getUser().getId();
		UserInfo userInfo = delegate.getUserInfo(userId);
		return userInfo.getData().getCounts().getFollowedBy();
	}

}
