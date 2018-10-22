package us.about80ninjas.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import us.about80ninjas.app.model.Visiter;
import us.about80ninjas.app.service.AppService;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
	
	AppService appService;
	
	public RestController(AppService appService) {
		this.appService = appService;
	}
	
	@GetMapping(value="/vis/all", produces = "application/json")
	public List<Visiter> getallVisiters(){
		return appService.getAllVisiter();
	}
	
	@GetMapping(value="vis/{id}", produces = "application/json")
    public Visiter getVisiterById(@PathVariable int id) {
        return appService.getVisiterById(id);
    }

}
