package com.stably.shortproject.form;

import java.util.Date;

public class URLShorten {
	private String originalURL;
	private String expiredDate;
	private String shortenURL;
	
	/**
	 * @return the originalURL
	 */
	public String getOriginalURL() {
		return originalURL;
	}
	/**
	 * @param originalURL the originalURL to set
	 */
	public void setOriginalURL(String originalURL) {
		this.originalURL = originalURL;
	}
	/**
	 * @return the expiredDate
	 */
	public String getExpiredDate() {
		return expiredDate; 
	}
	/**
	 * @param expiredDate the expiredDate to set
	 */
	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}
	/**
	 * @return the shortenURL
	 */
	public String getShortenURL() {
		return shortenURL;
	}
	/**
	 * @param message the shortenURL to set
	 */
	public void setShortenURL(String shortenURL) {
		this.shortenURL = shortenURL;
	}

}
