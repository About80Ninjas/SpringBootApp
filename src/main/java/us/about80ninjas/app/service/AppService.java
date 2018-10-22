package us.about80ninjas.app.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import us.about80ninjas.app.dto.VisiterDto;
import us.about80ninjas.app.dto.VisiterDtoInMemImpl;
import us.about80ninjas.app.model.Visiter;

@Service
public class AppService {
	
	VisiterDto visiterDto = new VisiterDtoInMemImpl();
	
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

}
