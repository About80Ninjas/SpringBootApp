package us.about80ninjas.app.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.QueueReference;

import us.about80ninjas.app.model.WebHookPayload;

@Controller
public class IndexController {

	//hi
	Logger logger = LoggerFactory.getLogger(IndexController.class);
	private static final String PASSWORD = System.getenv("password");

	@GetMapping(value = "/")
	public String index(HttpServletRequest request, Model model) {
		String ip = request.getRemoteAddr();
		logger.info("Index hit : " + ip);
		model.addAttribute("ip", ip);
		return "index";
	}

	@GetMapping("/job/SpringBootApp/build")
	public String lol(HttpServletRequest request) {
		logger.info(request.getRemoteAddr() + " got rek LOL");
		return "redirect:https://www.youtube.com/embed/DO7Y_Kw4LzU?autoplay=1";
	}
	
	
	@PostMapping(value = "/job/SpringBootApp/build")
	public ResponseEntity buildWebHook(@RequestBody WebHookPayload webHookPayload, HttpServletRequest request) {
		String user = webHookPayload.getSender().getLogin();
		logger.info(user + " requesting build");
		if (user.equals("About80Ninjas")) {

			try {
				JenkinsServer jenkinsServer = new JenkinsServer(new URI("http://192.168.1.200:8080"), "Jordan",
						PASSWORD);
				QueueReference reference = jenkinsServer.getJob("SpringBootApp").build();
				logger.info("Triggering build");
				logger.info(reference.getQueueItemUrlPart());
				jenkinsServer.close();
			} catch (URISyntaxException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
			}
			return new ResponseEntity<>(HttpStatus.OK);
		}
		logger.warn(request.getRemoteAddr() + " attempted unauthorized deploy");
		return new ResponseEntity<>(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
	}

}
