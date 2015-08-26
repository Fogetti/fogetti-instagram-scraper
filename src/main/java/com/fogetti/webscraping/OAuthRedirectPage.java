package com.fogetti.webscraping;

import org.apache.wicket.markup.html.pages.RedirectPage;

public class OAuthRedirectPage extends RedirectPage {

	private static final long serialVersionUID = 4870663455201115142L;
	
	public OAuthRedirectPage(CharSequence url, int waitBeforeRedirectInSeconds) {
		super(url, waitBeforeRedirectInSeconds);
	}

}
