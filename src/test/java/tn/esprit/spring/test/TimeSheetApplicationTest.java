package tn.esprit.spring.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import junit.framework.TestCase;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.services.ITimesheetService;
import org.apache.log4j.Logger;




@SpringBootTest
public class TimeSheetApplicationTest extends TestCase {
	
	private static final Logger l = Logger.getLogger(TimeSheetApplicationTest.class);

	@Autowired
	ITimesheetService timesheetservice;
	
	@Test
	public void testAjoutMission() {
		int k=0;
		
		try {
			
			k=timesheetservice.ajouterMission(new Mission("Mission1","Mission2"));
			boolean test=(k!=0)?true:false;
			assertEquals(test, true);
			
		if(test) {
			l.info("Mission avec id "+k+" added successfully ");
		}
		
		} catch (Exception e) {
			
			System.out.println(e);
		}
	}

}
	
