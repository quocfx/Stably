package com.stably.shortproject.service;

import java.util.Date;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stably.shortproject.model.MyURL;
import com.stably.shortproject.repository.MyURLRepository;

@Service
public class MyURLService {
	@Autowired
	MyURLRepository myURLRepository;
	@Autowired
	BaseConversion baseConversion;			
	
	public MyURL getMyURLById(int id) {
        return myURLRepository.findById(id).orElse(null);
    }
	
	public String getOriginalURLByShortURL(String shortURL) {
		if (shortURL == null || shortURL.equals("")) {
			return "";
		}
		int URLId = (int)baseConversion.decode(shortURL);
		MyURL myURL = getMyURLById(URLId);
		if (myURL == null) {
			throw new EntityNotFoundException("Link not found!");
		}
		// If current URL is expired
		if (myURL.getExpiredDate() != null && myURL.getExpiredDate().before(new Date())){
			// Remove it
			myURLRepository.delete(myURL);
            throw new EntityNotFoundException("Link expired!");
        }
		
		return myURL.getOriginalURL();
	}
	
	public String saveOrUpdate(MyURL myURL) {
		// Save the new record and return new id value
		int newId = myURLRepository.save(myURL).getId();
		// Convert it to base 62 value
		return baseConversion.encode(newId);
    }
}