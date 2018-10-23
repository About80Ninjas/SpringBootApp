package us.about80ninjas.app.model;

import java.sql.Timestamp;

import org.springframework.web.client.RestTemplate;

public class VisiterMapper {
	
	public static final Visiter getIpInfo(String ip) {
		RestTemplate restTemplate = new RestTemplate();
		Visiter visiter = restTemplate.getForObject("http://ipinfo.io/" + ip + "?TOKEN=0ab37b5ad6eefc", Visiter.class);
		visiter.setTimestamp(new Timestamp(System.currentTimeMillis()));
		return visiter;
	}

}
