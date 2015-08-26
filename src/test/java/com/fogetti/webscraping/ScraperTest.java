package com.fogetti.webscraping;

import org.apache.wicket.util.tester.WicketTester;
import org.jinstagram.auth.model.Token;
import org.junit.Before;
import org.junit.Test;

import com.fogetti.webscraping.service.IInstagramService;
import com.fogetti.webscraping.service.IInstagramServiceFactory;
import com.fogetti.webscraping.service.InstagramServiceFactory;

public class ScraperTest {

	private static final Token EMPTY_TOKEN = null;
	private IInstagramServiceFactory factory;
	private IInstagramService service;
	private WicketTester tester;
	
	@Before
	public void before() throws Exception {
	    tester = new WicketTester(new Scraper());
		System.setProperty("auth.service.factory", "com.fogetti.webscraping.service.InstagramServiceFactory");
		factory = InstagramServiceFactory.getInstance();
		service = factory.create("url");
	}
	
	@Test
	public void redirect() throws Exception {
	    tester.setFollowRedirects(false);
	    tester.startPage(new OAuthRedirectPage(service.getAuthorizationUrl(EMPTY_TOKEN), 1));
	    
	    assert tester.getLastResponse().getRedirectLocation() != null;
	}
	
}
