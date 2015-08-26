package com.fogetti.webscraping;

import org.apache.wicket.markup.html.form.Form;

public class LoginForm extends Form<String> {
	
	private static final long serialVersionUID = -5185996947826161937L;

	public LoginForm(String id) {
		super(id);
	}
	
    @Override
    protected void onInitialize() {
    	super.onInitialize();
		add(new LoginButton("loginButton"));
    }

}
