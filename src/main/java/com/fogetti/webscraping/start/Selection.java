package com.fogetti.webscraping.start;

import java.io.Serializable;

public class Selection implements Serializable {

	private static final long serialVersionUID = 8965082009566877411L;

	private String mediaId;
	private String request;

	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}

}
