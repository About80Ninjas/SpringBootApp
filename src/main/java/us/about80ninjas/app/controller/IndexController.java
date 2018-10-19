package us.about80ninjas.app.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

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
	
	@GetMapping(value="/job/SpringBootApp/build")
	public String buildWebHook() {
		URL url;
		try {
			url = new URL("http://localhost:8080/job/SpringBootApp/build?TOKEN=mPj6hYZ5YeDy3C58AHzpz6nsr");
			HttpURLConnection con;
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
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
		}
		return "redirect:index";
	}
	
	
	

}
