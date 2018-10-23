package us.about80ninjas.app.controller;


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

import us.about80ninjas.app.model.VisiterMapper;
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
		Visiter visiter = VisiterMapper.getIpInfo(request.getRemoteAddr());
		appService.addVisiter(visiter);
		logger.info("Index hit : " + visiter.getIp());
		model.addAttribute("ip", visiter.getIp());
		return "index";
	}

	@RequestMapping(value = "/", method = RequestMethod.OPTIONS)
	public ResponseEntity options(HttpServletRequest request) {
		Visiter visiter = VisiterMapper.getIpInfo(request.getRemoteAddr());
		appService.addVisiter(visiter);
		logger.info(visiter.getIp() + " looing for options");
		return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
	}

	@GetMapping("/job/SpringBootApp/build")
	public String lol(HttpServletRequest request) {
		Visiter visiter = VisiterMapper.getIpInfo(request.getRemoteAddr());
		appService.addVisiter(visiter);
		logger.info(visiter.getIp() + " got rek LOL");
		return "redirect:https://www.youtube.com/embed/DO7Y_Kw4LzU?autoplay=1";
	}

	@PostMapping(value = "/job/SpringBootApp/build")
	public ResponseEntity buildWebHook(@RequestBody WebHookPayload webHookPayload, HttpServletRequest request) {
		appService.addVisiter(VisiterMapper.getIpInfo(request.getRemoteAddr()));
		if (!(appService.isAuthorised(webHookPayload, request))) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		if(!(appService.requestBuild())) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}


}
