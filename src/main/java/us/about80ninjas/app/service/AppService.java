package us.about80ninjas.app.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.QueueReference;

import us.about80ninjas.app.dto.VisiterDto;
import us.about80ninjas.app.dto.VisiterDtoInMemImpl;
import us.about80ninjas.app.model.Visiter;
import us.about80ninjas.app.model.WebHookPayload;

@Service
public class AppService {
	
	VisiterDto visiterDto = new VisiterDtoInMemImpl();
	Logger logger = LoggerFactory.getLogger(AppService.class);
	
	public void removeVisiter(Visiter visiter) {
		visiterDto.removeVisiter(visiter);
	}
	public Visiter addVisiter(Visiter visiter) {
		return visiterDto.addVisiter(visiter);
		
	}
	public Visiter updateVisiter(Visiter visiter) {
		return visiterDto.updateVisiter(visiter);
	}
	public List<Visiter> getAllVisiter(){
		return visiterDto.getAllVisiter();
	}
	public Visiter getVisiterById(int id) {
		return visiterDto.getVisiterById(id);
	}
	public List<Visiter> getVisiterByName(String name){
		return visiterDto.getVisiterByName(name);
	}
	public List<Visiter> getVisiterByIp(String ip){
		return visiterDto.getVisiterByIp(ip);
	}
	public List<Visiter> getVisiterByTimeStamp(Timestamp timestamp){
		return visiterDto.getVisiterByTimeStamp(timestamp);
	}
	public boolean isAuthorised(WebHookPayload webHookPayload, HttpServletRequest request) {
		String user = webHookPayload.getSender().getLogin();
		logger.info(user + " requesting build");
		if (user.equals("About80Ninjas")) {
			return true;
		}
		logger.warn(request.getRemoteAddr() + " attempted unauthorized deploy");
		return false;
	}
	public boolean requestBuild() {
		try {
			JenkinsServer jenkinsServer = new JenkinsServer(new URI("http://192.168.1.200:8080"), "bot",
					"VTaPug5ghyrDGozYq5fLlT1T7");
			QueueReference reference = jenkinsServer.getJob("SpringBootApp").build();
			logger.info("Triggering build");
			logger.info(reference.getQueueItemUrlPart());
			jenkinsServer.close();
		} catch (URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
