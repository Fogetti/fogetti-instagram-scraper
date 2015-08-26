package com.fogetti.webscraping;

import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

public class Scraper extends WebApplication {

	@Override
	public Class<? extends Page> getHomePage() {
		return LoginPage.class;
	}
	
	@Override
    public final Session newSession(Request request, Response response) {
        return new InstagramSession(request);
    }

	@Override
	public void init()
	{
		super.init();
		mountPage("/callback", OAuthCallbackPage.class);
	}
}
