package com.fogetti.webscraping;

import org.apache.wicket.markup.html.WebPage;

public class LoginPage extends WebPage {

	private static final long serialVersionUID = 4753371951760810739L;

    @Override
    protected void onInitialize() {
        super.onInitialize();

        if (get("form") == null) {
            setLoginForm("form");
        }
    }

	void setLoginForm(String componentId) {
		add(new LoginForm(componentId));
	}

}
