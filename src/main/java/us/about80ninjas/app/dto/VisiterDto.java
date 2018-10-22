package us.about80ninjas.app.dto;

import java.sql.Timestamp;
import java.util.List;

import us.about80ninjas.app.model.Visiter;

public interface VisiterDto {
	public void removeVisiter(Visiter visiter);
	public Visiter addVisiter(Visiter visiter);
	public Visiter updateVisiter(Visiter visiter);
	public List<Visiter> getAllVisiter();
	public Visiter getVisiterById(int id);
	public List<Visiter> getVisiterByName(String name);
	public List<Visiter> getVisiterByIp(String ip);
	public List<Visiter> getVisiterByTimeStamp(Timestamp timestamp);

}
