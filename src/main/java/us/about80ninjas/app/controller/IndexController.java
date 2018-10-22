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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.QueueReference;

import us.about80ninjas.app.model.Visiter;
import us.about80ninjas.app.model.WebHookPayload;
import us.about80ninjas.app.service.AppService;

@Controller
public class IndexController {

	Logger logger = LoggerFactory.getLogger(IndexController.class);
	AppService appService;

	public IndexController(AppService appService) {
		this.appService = appService;
	}

	@GetMapping("/stats")
	public String stats(Model model) {
		model.addAttribute("visitors", appService.getAllVisiter());
		return "stats";
	}

	@GetMapping(value = "/")
	public String index(HttpServletRequest request, Model model) {
		Visiter visiter = visiterMapper(request);
		appService.addVisiter(visiter);
		logger.info("Index hit : " + visiter.getIp());
		model.addAttribute("ip", visiter.getIp());
		return "index";
	}

	@RequestMapping(value = "/", method = RequestMethod.OPTIONS)
	public ResponseEntity options(HttpServletRequest request) {
		Visiter visiter = visiterMapper(request);
		appService.addVisiter(visiter);
		logger.info(visiter.getIp() + " looing for options");
		return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
	}

	@GetMapping("/job/SpringBootApp/build")
	public String lol(HttpServletRequest request) {
		Visiter visiter = visiterMapper(request);
		appService.addVisiter(visiter);
		logger.info(visiter.getIp() + " got rek LOL");
		return "redirect:https://www.youtube.com/embed/DO7Y_Kw4LzU?autoplay=1";
	}

	@PostMapping(value = "/job/SpringBootApp/build")
	public ResponseEntity buildWebHook(@RequestBody WebHookPayload webHookPayload, HttpServletRequest request) {
		String user = webHookPayload.getSender().getLogin();
		logger.info(user + " requesting build");
		if (user.equals("About80Ninjas")) {

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
				return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
			}
			return new ResponseEntity<>(HttpStatus.OK);
		}
		logger.warn(request.getRemoteAddr() + " attempted unauthorized deploy");
		return new ResponseEntity<>(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
	}

	private Visiter visiterMapper(HttpServletRequest request) {
		Visiter visiter = new Visiter();
		visiter.setName(request.getRemoteHost());
		visiter.setIp(request.getRemoteAddr());
		return visiter;
	}

}
