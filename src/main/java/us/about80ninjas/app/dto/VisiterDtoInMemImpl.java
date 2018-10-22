package us.about80ninjas.app.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.about80ninjas.app.model.Visiter;

public class VisiterDtoInMemImpl implements VisiterDto {

	Map<Integer, Visiter> visiterMap = new HashMap<>();

	@Override
	public void removeVisiter(Visiter visiter) {
		visiterMap.remove(visiter.getId());

	}

	@Override
	public Visiter addVisiter(Visiter visiter) {
		visiter.setId(visiterMap.size() + 1);
		visiterMap.put(visiter.getId(), visiter);
		return visiterMap.get(visiter.getId());
	}

	@Override
	public Visiter updateVisiter(Visiter visiter) {
		visiterMap.put(visiter.getId(), visiter);
		return visiterMap.get(visiter.getId());
	}

	@Override
	public List<Visiter> getAllVisiter() {
		List<Visiter> allVisiter = new ArrayList<Visiter>(visiterMap.values());
		return allVisiter;
	}

	@Override
	public Visiter getVisiterById(int id) {
		Visiter visiter = visiterMap.get(id);
		return visiter;
	}

	@Override
	public List<Visiter> getVisiterByName(String name) {
		List<Visiter> filteredVisiter = new ArrayList<Visiter>();
		for (Visiter visiter : visiterMap.values()) {
			if (visiter.getName().equals(name)) {
				filteredVisiter.add(visiter);
			}
		}
		return filteredVisiter;
	}

	@Override
	public List<Visiter> getVisiterByIp(String ip) {
		List<Visiter> filteredVisiter = new ArrayList<Visiter>();
		for (Visiter visiter : visiterMap.values()) {
			if (visiter.getIp().equals(ip)) {
				filteredVisiter.add(visiter);
			}
		}
		return filteredVisiter;
	}

	@Override
	public List<Visiter> getVisiterByTimeStamp(Timestamp timestamp) {
		List<Visiter> filteredVisiter = new ArrayList<Visiter>();
		for (Visiter visiter : visiterMap.values()) {
			if (visiter.getTimestamp().equals(timestamp)) {
				filteredVisiter.add(visiter);
			}
		}
		return filteredVisiter;
	}

}
