package com.fogetti.webscraping.start;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.jinstagram.auth.model.Token;
import org.jinstagram.entity.users.basicinfo.UserInfo;
import org.jinstagram.exceptions.InstagramException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fogetti.webscraping.InstagramSession;
import com.fogetti.webscraping.service.IInstagram;
import com.fogetti.webscraping.service.IInstagramFactory;
import com.fogetti.webscraping.service.InstagramServiceFactory;

public class StartPage extends WebPage {

    private static final Logger logger = LoggerFactory.getLogger(InstagramServiceFactory.class);
	private static final long serialVersionUID = -4008891865507010138L;
	private final IInstagramFactory factory;
	
	public StartPage(IInstagramFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public void onInitialize() {
		super.onInitialize();
		try {
			IInstagram instagram = buildInstagram();
			initializePageContent(instagram);
			addDynamicForm(instagram);
		} catch (InstagramException e) {
			logger.error("Could not get Instagram user info", e);
		}

	}

	protected IInstagram buildInstagram() {
		Token accessToken = InstagramSession.get().getAccessToken();
		System.out.println("Access Token :: " + accessToken.getToken());
		IInstagram instagram = factory.create(accessToken);
		return instagram;
	}

	protected void initializePageContent(IInstagram instagram) throws InstagramException {
		UserInfo userInfo = instagram.getCurrentUserInfo();
		
		System.out.println("***** User Info ******");
		System.out.println("Username : " + userInfo.getData().getUsername());
		System.out.println("First Name : " + userInfo.getData().getFirst_name());
		System.out.println("Last Name : " + userInfo.getData().getLast_name());
		System.out.println("Website : " + userInfo.getData().getWebsite());
		add(new Label("message", userInfo.getData().getUsername()));
	}

	protected void addDynamicForm(IInstagram instagram) {
		add(new DynamicForm("dynamicForm", instagram));
	}
	
}
