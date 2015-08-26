package com.fogetti.webscraping.service;

import static org.junit.Assert.assertNotNull;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import com.fogetti.webscraping.LoginPage;
import com.fogetti.webscraping.Scraper;

public class InstagramServiceFactoryTest {

	@Before
	public void before() throws Exception {
	    WicketTester tester = new WicketTester(new Scraper());
	    tester.startPage(new LoginPage());
	}
	
	@Test
	public void instance() throws Exception {
		System.setProperty("auth.service.factory", "com.fogetti.webscraping.service.InstagramServiceFactory");
		IInstagramServiceFactory factory = InstagramServiceFactory.getInstance();
		assertNotNull("The returned service factory instance was null", factory);
	}
	
	@Test
	public void create() throws Exception {
		System.setProperty("auth.service.factory", "com.fogetti.webscraping.service.InstagramServiceFactory");
		IInstagramServiceFactory factory = InstagramServiceFactory.getInstance();
		IInstagramService service = factory.create("url");
		assertNotNull("The returned service was null", service);
	}
}
