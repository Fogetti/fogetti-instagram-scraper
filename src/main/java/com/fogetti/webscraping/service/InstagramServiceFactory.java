package com.fogetti.webscraping.service;

import java.io.Serializable;

import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.RequestCycle;
import org.jinstagram.auth.InstagramAuthService;
import org.jinstagram.auth.oauth.InstagramService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fogetti.webscraping.service.adapter.InstagramServiceAdapter;

public class InstagramServiceFactory implements IInstagramServiceFactory, Serializable {

	private static final long serialVersionUID = 6314472627240231108L;
	private static final Logger logger = LoggerFactory.getLogger(InstagramServiceFactory.class);
    private static final Class<?> c;
    private static final IInstagramServiceFactory instance;
	static {
		try {
			c = Class.forName(System.getProperty("auth.service.factory"));
			instance = (IInstagramServiceFactory) c.newInstance();
		} catch (ClassNotFoundException e) {
			logger.error("Could not create the Instagram auth service factory", e);
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			logger.error("Could not create the Instagram auth service factory", e);
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			logger.error("Could not create the Instagram auth service factory", e);
			throw new RuntimeException(e);
		}
	}
	private final String clientId = "03af75453b844907b6f0768fdc67918a";
	private final String clientSecret = "395e30846bc9439c8bb69fa79f393a63";
	
	private InstagramServiceFactory() {}
	
	public static IInstagramServiceFactory getInstance() {
		return instance;
	}
	
	@Override
	public IInstagramService create(CharSequence url) {
		String callbackUrl = RequestCycle.get().getUrlRenderer().renderFullUrl(Url.parse(url.toString()));
		InstagramService service = new InstagramAuthService()
          .apiKey(clientId)
          .apiSecret(clientSecret)
          .callback(callbackUrl)
          .build();
		return new InstagramServiceAdapter(service);
	}

}
