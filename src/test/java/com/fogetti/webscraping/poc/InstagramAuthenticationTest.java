package com.fogetti.webscraping.poc;

import java.util.Scanner;

import org.jinstagram.Instagram;
import org.jinstagram.auth.InstagramAuthService;
import org.jinstagram.auth.model.Token;
import org.jinstagram.auth.model.Verifier;
import org.jinstagram.auth.oauth.InstagramService;
import org.jinstagram.entity.users.basicinfo.UserInfo;

/**
 * @author Sachin Handiekar (sachin.handiekar@gmail.com)
 */
public class InstagramAuthenticationTest {

	private static final Token EMPTY_TOKEN = null;

	public static void main(String[] args) throws Exception {

		String clientId = "03af75453b844907b6f0768fdc67918a";
		String clientSecret = "395e30846bc9439c8bb69fa79f393a63";

		String callbackUrl = "http://reveal-it.appspot.com/oauthtest";

		InstagramService service = new InstagramAuthService()
                                           .apiKey(clientId)
                                           .apiSecret(clientSecret)
				                           .callback(callbackUrl)
                                           .build();

		String authorizationUrl = service.getAuthorizationUrl(EMPTY_TOKEN);

		System.out.println("** Instagram Authorization ** \n\n");

		System.out.println("Copy & Paste the below Authorization URL in your browser...");
		System.out.println("Authorization URL : " + authorizationUrl);

		Scanner sc = new Scanner(System.in);

		String verifierCode;

		System.out.print("Your Verifier Code : ");
		verifierCode = sc.next();

		System.out.println();

		Verifier verifier = new Verifier(verifierCode);
		Token accessToken = service.getAccessToken(EMPTY_TOKEN, verifier);

		System.out.println("Access Token :: " + accessToken.getToken());
		Instagram instagram = new Instagram(accessToken);

		UserInfo userInfo = instagram.getCurrentUserInfo();

		System.out.println("***** User Info ******");
		System.out.println("Username : " + userInfo.getData().getUsername());
		System.out.println("First Name : " + userInfo.getData().getFirst_name());
		System.out.println("Last Name : " + userInfo.getData().getLast_name());
		System.out.println("Website : " + userInfo.getData().getWebsite());

	}

}