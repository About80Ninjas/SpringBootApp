package us.about80ninjas.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


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
	
	

}
