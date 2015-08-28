package com.fogetti.webscraping.service.adapter;

import java.io.Serializable;

import org.jinstagram.auth.InstagramApi;
import org.jinstagram.auth.model.OAuthConfig;
import org.jinstagram.auth.model.OAuthRequest;
import org.jinstagram.auth.model.Token;
import org.jinstagram.auth.model.Verifier;
import org.jinstagram.auth.oauth.InstagramService;

import com.fogetti.webscraping.service.IInstagramService;

public class InstagramServiceAdapter extends InstagramService implements IInstagramService, Serializable {

	private static final long serialVersionUID = 9127103229507980263L;
	private final InstagramService delegate;
	
	public InstagramServiceAdapter(InstagramApi api, OAuthConfig config, InstagramService delegate) {
		super(api, config);
		this.delegate = delegate;
	}
	
	public InstagramServiceAdapter(InstagramService delegate) {
		super(null, null);
		this.delegate = delegate;
		
	}

	@Override
	public Token getAccessToken(Token requestToken, Verifier verifier) {
		return delegate.getAccessToken(requestToken, verifier);
	}
	
	@Override
	public String getAuthorizationUrl(Token requestToken) {
		return delegate.getAuthorizationUrl(requestToken);
	}
	
	@Override
	public Token getRequestToken() {
		return delegate.getRequestToken();
	}
	
	@Override
	public String getVersion() {
		return delegate.getVersion();
	}
	
	@Override
	public void signRequest(Token accessToken, OAuthRequest request) {
		delegate.signRequest(accessToken, request);
	}

}
