package com.fogetti.webscraping.poc;

import java.util.Scanner;

import org.scribe.model.OAuthConfig;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

public class InstagramAuthenticationTest2 {
	
	private static final Token EMPTY_TOKEN = null;

	public static void main(String[] args) {
		String clientId = "03af75453b844907b6f0768fdc67918a";
		String clientSecret = "395e30846bc9439c8bb69fa79f393a63";
		String callback = "http://localhost:9000/";
		OAuthConfig config = new OAuthConfig(clientId, clientSecret, callback, null, null, null);
		InstagramApi api = new InstagramApi();
		OAuthService service = api.createService(config);
		
		Scanner in = new Scanner(System.in);

	    System.out.println("=== Foursquare2's OAuth Workflow ===");
	    System.out.println();

	    // Obtain the Authorization URL
	    System.out.println("Fetching the Authorization URL...");
	    String authorizationUrl = service.getAuthorizationUrl(EMPTY_TOKEN);
	    System.out.println("Got the Authorization URL!");
	    System.out.println("Now go and authorize Scribe here:");
	    System.out.println(authorizationUrl);
	    System.out.println("And paste the authorization code here");
	    System.out.print(">>");
	    Verifier verifier = new Verifier(in.nextLine());
	    System.out.println();
	    
	    // Trade the Request Token and Verfier for the Access Token
	    System.out.println("Trading the Request Token for an Access Token...");
	    Token accessToken = service.getAccessToken(EMPTY_TOKEN, verifier);
	    System.out.println("Got the Access Token!");
	    System.out.println("(if your curious it looks like this: " + accessToken + " )");
	    System.out.println();

	    System.out.println();
	    System.out.println("Thats it man! Go and build something awesome with Scribe! :)");
	}
}
