package com.fogetti.webscraping.service;

import java.io.Serializable;

import org.jinstagram.Instagram;
import org.jinstagram.auth.model.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fogetti.webscraping.service.adapter.InstagramAdapter;

public class InstagramFactory implements IInstagramFactory, Serializable {
	
	private static final long serialVersionUID = 4428036893107793131L;
	private static final Logger logger = LoggerFactory.getLogger(InstagramServiceFactory.class);
    private static final Class<?> c;
    private static final IInstagramFactory instance;
	static {
		try {
			c = Class.forName(System.getProperty("instagram.factory"));
			instance = (IInstagramFactory) c.newInstance();
		} catch (ClassNotFoundException e) {
			logger.error("Could not create the Instagram factory", e);
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			logger.error("Could not create the Instagram factory", e);
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			logger.error("Could not create the Instagram factory", e);
			throw new RuntimeException(e);
		}
	}

	private InstagramFactory() {}
	
	public static IInstagramFactory getInstance() {
		return instance;
	}

	@Override
	public IInstagram create(Token accessToken) {
		return new InstagramAdapter(new Instagram(accessToken));
	}

}
