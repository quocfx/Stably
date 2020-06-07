package com.stably.shortproject.controller;

import java.net.URI;
import java.time.Duration;
import java.util.Date;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.stably.shortproject.configs.GlobalConfigs;
import com.stably.shortproject.form.URLShorten;
import com.stably.shortproject.model.MyURL;
import com.stably.shortproject.service.MyURLService;

@Controller
public class MyURLHandleController {
	@Autowired
	MyURLService myURLService;
	@Autowired
	GlobalConfigs globalConfigs;		
	@Value("${server.port}")
	private String serverPort;
	
	private final String URL_ERROR_PAGE = "/error/urlerror";
	
	@GetMapping("/")
    public String index(Model model) {
		URLShorten urlShortenModel = new URLShorten();
		urlShortenModel.setOriginalURL("https://www.google.com/search?q=stably+vietnam&oq=stably+vietnam");
		model.addAttribute("urlshortenModel", urlShortenModel);
        return "urlshorten";
    }
	
	@GetMapping(URL_ERROR_PAGE)
    public String URLError(Model model) {		
        return "urlerror";
    }
	
	@PostMapping("/")
	public String handleUrl(Model model, @ModelAttribute("urlshortenModel") URLShorten myFormObj) {
		String originalURL = myFormObj.getOriginalURL();
		MyURL myURL = initNewMyUrl(originalURL);
		String shortenId = myURLService.saveOrUpdate(myURL);
		String shortenURL = globalConfigs.getHost() + ":" + serverPort + "/" +  shortenId;
		myFormObj.setShortenURL(shortenURL);
		myFormObj.setExpiredDate(myURL.getExpiredDate().toLocaleString());
        return "urlshorten";
    }
	
	@GetMapping(value = "{shortUrl}")
	public ResponseEntity<Void> redirectShortURL(@PathVariable String shortUrl) {
		String originalURL = "";
		try {
			originalURL = myURLService.getOriginalURLByShortURL(shortUrl);
		} catch (EntityNotFoundException ex) {
			// If original link not found or is expired, redirect to urlerror page
			String errorURL = globalConfigs.getHost() + ":" + serverPort + URL_ERROR_PAGE;
			return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(errorURL)).build();
		}
		return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(originalURL)).build();
    }
	
	// Init new My Url object from original URL value
	// TODO: This method is placed here because we need to return expired date to the user
	private MyURL initNewMyUrl(String originalUrl) {
		MyURL myURL = new MyURL();
		myURL.setOriginalURL(originalUrl);
		Date createdDate = new Date();
		myURL.setCreatedDate(createdDate);
		// The expired date will be after created date 1 hour
		Date expiredDate = Date.from(createdDate.toInstant().plus(Duration.ofHours(globalConfigs.getExpiredHour())));
		myURL.setExpiredDate(expiredDate);
		return myURL;
	}
}
