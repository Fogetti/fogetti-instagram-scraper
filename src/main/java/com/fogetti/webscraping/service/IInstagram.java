package com.fogetti.webscraping.service;

import org.jinstagram.entity.users.basicinfo.UserInfo;
import org.jinstagram.exceptions.InstagramException;

public interface IInstagram {

	UserInfo getCurrentUserInfo() throws InstagramException;
}
