package com.fogetti.webscraping;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;
import org.jinstagram.auth.model.Token;

public class InstagramSession extends WebSession {

	private static final long serialVersionUID = -6545029919267237999L;
	private Token accessToken;
	
	public InstagramSession(Request request) {
		super(request);
	}

	public Token getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(Token accessToken) {
		this.accessToken = accessToken;
	}
	
	public static InstagramSession get() {
        return (InstagramSession)Session.get();
    }

}
