package com.fogetti.webscraping.service;

import org.jinstagram.auth.model.Token;

public interface IInstagramFactory {

	IInstagram create(Token accessToken);
}
