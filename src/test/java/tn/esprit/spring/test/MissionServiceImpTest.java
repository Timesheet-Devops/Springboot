package tn.esprit.spring.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.services.IMissionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MissionServiceImpTest {
	
	

	private static final Logger l = LogManager.getLogger(MissionServiceImpTest.class);

	
	
	
	@Autowired
	MissionRepository missionRepository ;
	
	@Autowired
	IMissionService imissionService ;
	
	
	
	//Add Mission Test 
		@Test
		public void testaddMission() {
			Mission mission = new Mission("missionTest","missionTest");
			imissionService.ajouterMission(mission);
		assertNotNull(mission.getId());
		l.info("Mission added successfuly ");
		missionRepository.deleteById(mission.getId());
		}
		
		
		
		//Count missions a make sure the return is  not null
		@Test
		public void testcountMission() {
		long nbrms = missionRepository.count();
		assertNotNull(nbrms);
		l.info("Le Nombre des Employes est :");
		l.info(nbrms);
		}
		
		
		
		// Make sure the Database is not Nulls
		@Test
		public void testListMission() {
			Mission mission = new Mission("missionTest","missionTest");
			imissionService.ajouterMission(mission);
		List<Mission> e = (List<Mission>) missionRepository.findAll();
		assertThat(e).size().isPositive();
		l.info( "> 0");
		missionRepository.deleteById(mission.getId());
		}
		
		
		
		
	
		
		/*@Test
		public void deleteMission() {
			testaddMission();
					int id = 12;
					imissionService.deleteMission(id);
				}*/
				

	
}