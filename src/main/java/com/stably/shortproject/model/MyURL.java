package com.stably.shortproject.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "URLS")
public class MyURL {
	@Id
    @GeneratedValue
    private int id;
	
	private String originalURL;
	private Date createdDate;
	private Date expiredDate;
	
	/**
	 * Default constructor for MyURL 
	 */
	public MyURL() {
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

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
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the expiredDate
	 */
	public Date getExpiredDate() {
		return expiredDate;
	}

	/**
	 * @param expiredDate the expiredDate to set
	 */
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}
}
