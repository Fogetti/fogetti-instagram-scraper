package com.fogetti.webscraping.service;

public interface IInstagramServiceFactory {
	
	IInstagramService create(CharSequence url);

}
