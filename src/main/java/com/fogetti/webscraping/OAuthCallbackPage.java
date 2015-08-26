package com.fogetti.webscraping;

import org.apache.wicket.markup.html.pages.RedirectPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;
import org.jinstagram.auth.model.Token;
import org.jinstagram.auth.model.Verifier;

import com.fogetti.webscraping.service.IInstagramService;
import com.fogetti.webscraping.service.IInstagramServiceFactory;
import com.fogetti.webscraping.service.InstagramFactory;
import com.fogetti.webscraping.service.InstagramServiceFactory;
import com.fogetti.webscraping.start.StartPage;

public class OAuthCallbackPage extends RedirectPage {

	private static final long serialVersionUID = -6151138606182232234L;
	private final Token EMPTY_TOKEN = null;
	private final IInstagramServiceFactory factory = InstagramServiceFactory.getInstance();
	private final IInstagramService service = factory.create(urlFor(OAuthCallbackPage.class,null));
	
	public OAuthCallbackPage(PageParameters parameters) {
		super(new StartPage(InstagramFactory.getInstance()), 1);

		StringValue value = parameters.get("code");
		String verifierCode = value.toString();
		Verifier verifier = new Verifier(verifierCode );
		Token accessToken = service.getAccessToken(EMPTY_TOKEN, verifier);
		InstagramSession.get().setAccessToken(accessToken);
	}

}
