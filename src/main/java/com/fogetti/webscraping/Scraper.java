package com.fogetti.webscraping;

import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import com.fogetti.webscraping.result.ResultPage;
import com.fogetti.webscraping.start.StartPage;

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
		mountPage("/login", LoginPage.class);
		mountPage("/callback", OAuthCallbackPage.class);
		mountPage("/start", StartPage.class);
		mountPage("/result", ResultPage.class);
	}
}
