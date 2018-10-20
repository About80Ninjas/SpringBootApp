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




@Controller
public class IndexController {
	
	Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@GetMapping(value="/")
	public String index(HttpServletRequest request, Model model) {
		String ip = request.getRemoteAddr();
		logger.info("Index hit : " + ip);
		model.addAttribute("ip", ip);
		return "index";
	}
	
	@PostMapping(value="/job/SpringBootApp/build")
	public ResponseEntity  buildWebHook(@RequestBody String push) {
		logger.debug(push);
		try {
			JenkinsServer jenkinsServer = new JenkinsServer(new URI("http://192.168.1.200:8080"),"Jordan","D@K0ta!?");
			QueueReference reference =  jenkinsServer.getJob("SpringBootApp").build();
			//hi
			logger.info("Triggering build");
			logger.info(reference.getQueueItemUrlPart());
			jenkinsServer.close();
		} catch (URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*URL url;
		try {
			url = new URL("http://jordan:1195a045431d87150e1eb18ee9c8d27b8f@192.168.1.200:8080/job/SpringBootApp/build?token=mPj6hYZ5YeDy3C58AHzpz6nsr");
			HttpURLConnection con;
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.connect();
			con.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
