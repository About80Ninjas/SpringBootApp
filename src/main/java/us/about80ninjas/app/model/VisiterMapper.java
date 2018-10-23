package us.about80ninjas.app.model;

import org.springframework.web.client.RestTemplate;

public class VisiterMapper {
	
	public static final Visiter getIpInfo(String ip) {
		RestTemplate restTemplate = new RestTemplate();
		Visiter visiter = restTemplate.getForObject("http://ipinfo.io/" + ip + "?TOKEN=0ab37b5ad6eefc", Visiter.class);
		return visiter;
	}

}
