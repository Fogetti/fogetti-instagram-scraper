package com.fogetti.webscraping.service;

import org.jinstagram.auth.model.Token;

public class MockInstagramFactory implements IInstagramFactory {
	
	private final IInstagram instagram;

	public MockInstagramFactory(IInstagram instagram) {
		this.instagram = instagram;
	}

	@Override
	public IInstagram create(Token accessToken) {
		return instagram;
	}

}
