package tn.esprit.spring.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.services.IEmployeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeServiceImplTest {
	
	private static final long DEFAULT_TIMEOUT = 10000;
	private static final Logger l = LogManager.getLogger(EmployeServiceImplTest.class);

	@Autowired
	IEmployeService es;
	
	@Autowired
	EmployeRepository er;
	
	//test de la methode ajout
	@Test(timeout = DEFAULT_TIMEOUT)
	public void testaddemploye() {
		Employe employe = new Employe("Amin","BenCHeikh","amin.bencheikh@esprit.tn",true,Role.INGENIEUR);
	es.ajouterEmploye(employe);
	assertNotNull(employe.getId());
	l.info("Employe added successfuly ");
	er.deleteById(employe.getId());
	l.info("Employe deleted successfuly ");
	}
	

	

	@Test
	public void testListEmploye() {
	List<Employe> e = (List<Employe>) er.findAll();
		if (e.isEmpty()){
			l.info("Employes list is empty: ");
		}else{
			l.info("Employes list");
		}
	}
	
	//test de la methode count "le retour de la methode ne doit pas etre null".
	@Test
	public void testcountEmploye() {
	List<Employe> e = (List<Employe>) er.findAll();
	assertNotNull(e);
	}
	

	// test du suppression de l'employee
	@Test(timeout = 5000)
	public void testDeleteEmploye() {
		Employe emp = new Employe();
		assertNotNull(emp);
		er.deleteAll();
		l.info("deleted successfuly" );
	}

}