package com.fogetti.webscraping;

import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.markup.html.form.Button;
import org.jinstagram.auth.model.Token;

import com.fogetti.webscraping.service.IInstagramService;
import com.fogetti.webscraping.service.IInstagramServiceFactory;
import com.fogetti.webscraping.service.InstagramServiceFactory;

final class LoginButton extends Button {
	
	private static final long serialVersionUID = -3199257710371010342L;
	
	private final Token EMPTY_TOKEN = null;
	private final IInstagramServiceFactory factory = InstagramServiceFactory.getInstance();
	private final IInstagramService service = factory.create(urlFor(OAuthCallbackPage.class,null));

	LoginButton(String id) {
		super(id);
	}

	@Override
	public void onSubmit() {
		throw new RestartResponseAtInterceptPageException(new OAuthRedirectPage(service.getAuthorizationUrl(EMPTY_TOKEN), 1));
	}
	
}