package com.fogetti.webscraping.service;

import org.jinstagram.auth.model.OAuthRequest;
import org.jinstagram.auth.model.Token;
import org.jinstagram.auth.model.Verifier;

public interface IInstagramService {

	Token getAccessToken(Token requestToken, Verifier verifier);
	String getAuthorizationUrl(Token requestToken);
	Token getRequestToken();
	String getVersion();
	void signRequest(Token accessToken, OAuthRequest request);
}
