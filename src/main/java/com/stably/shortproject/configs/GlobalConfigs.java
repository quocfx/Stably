package com.stably.shortproject.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:globalconfig.properties")
public class GlobalConfigs {
	
	@Value("${globalconfig.host}")
	private String host;
	
	@Value("${globalconfig.expiredafter}")
	private int expiredHour;

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @return the expiredHour
	 */
	public int getExpiredHour() {
		return expiredHour;
	}

}
