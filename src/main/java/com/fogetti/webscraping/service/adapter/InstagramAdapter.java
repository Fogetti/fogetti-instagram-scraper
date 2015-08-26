package com.fogetti.webscraping.service.adapter;

import java.io.Serializable;

import org.jinstagram.Instagram;
import org.jinstagram.entity.users.basicinfo.UserInfo;
import org.jinstagram.exceptions.InstagramException;

import com.fogetti.webscraping.service.IInstagram;

public class InstagramAdapter implements IInstagram, Serializable {
	
	private static final long serialVersionUID = -9138788034148432935L;
	private final transient Instagram delegate;

	public InstagramAdapter(Instagram delegate) {
		this.delegate = delegate;
	}

	@Override
	public UserInfo getCurrentUserInfo() throws InstagramException {
		return delegate.getCurrentUserInfo();
	}

}
