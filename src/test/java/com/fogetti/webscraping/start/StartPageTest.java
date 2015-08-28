package com.fogetti.webscraping.start;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.apache.wicket.util.tester.WicketTester;
import org.jinstagram.auth.model.Token;
import org.jinstagram.entity.users.basicinfo.UserInfo;
import org.jinstagram.entity.users.basicinfo.UserInfoData;
import org.junit.Before;
import org.junit.Test;

import com.fogetti.webscraping.InstagramSession;
import com.fogetti.webscraping.Scraper;
import com.fogetti.webscraping.service.IInstagram;
import com.fogetti.webscraping.service.IInstagramFactory;
import com.fogetti.webscraping.service.MockInstagramFactory;

public class StartPageTest {

	private WicketTester tester;
	private InstagramSession session;
	private Token token;
	private IInstagram instagram;
	private UserInfoData userinfodata;
	private UserInfo userinfo;
	private IInstagramFactory factory;

	@Before
	public void before() throws Exception {
		System.setProperty("auth.service.factory", "com.fogetti.webscraping.service.InstagramServiceFactory");
		tester = new WicketTester(new Scraper());
		session = (InstagramSession) tester.getSession();
		token = mock(Token.class);
		session.setAccessToken(token);
		instagram = mock(IInstagram.class);
		userinfodata = mock(UserInfoData.class);
		userinfo = mock(UserInfo.class);
		factory = new MockInstagramFactory(instagram);
	}
	
	@Test
	public void formVisible() throws Exception {		
		when(instagram.getCurrentUserInfo()).thenReturn(userinfo);
		when(userinfo.getData()).thenReturn(userinfodata);
		
		tester.startPage(new StartPage(factory));
		
		tester.assertVisible("dynamicForm");
	}

	@Test
	public void ajaxButtonVisible() throws Exception {		
		when(instagram.getCurrentUserInfo()).thenReturn(userinfo);
		when(userinfo.getData()).thenReturn(userinfodata);
		
		tester.startPage(new StartPage(factory));
		
		tester.assertVisible("dynamicForm:addButton");
	}
	
	@Test
	public void inputFieldVisible() throws Exception {		
		when(instagram.getCurrentUserInfo()).thenReturn(userinfo);
		when(userinfo.getData()).thenReturn(userinfodata);
		
		tester.startPage(new StartPage(factory));
		tester.executeAjaxEvent("dynamicForm:addButton", "onclick");
		
		tester.assertVisible("dynamicForm:mediaPanel:mediaIds:0:mediaId");
	}
}
