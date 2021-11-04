package tn.esprit.spring.services;
import java.util.List;
import tn.esprit.spring.entities.Mission;

public interface IMissionService {

	long ajouterMission(Mission mission);

	void deleteMission(int id);

	List<Mission> getMissions();

	long getMissionNumber();

	Mission MissionUpadate(Mission Miss);
	
}
