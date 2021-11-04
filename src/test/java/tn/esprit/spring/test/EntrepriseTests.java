package tn.esprit.spring.test;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.EntrepriseServiceImpl;
//AMIN WORK TEST
@RunWith(SpringRunner.class)
@SpringBootTest
public class EntrepriseTests {

	private static final Logger l= LogManager.getLogger(EntrepriseTests.class);

	
	@Autowired
	public EntrepriseServiceImpl entrepriseServiceImpl;
	
	@Test
	public void testerEntreprise() {
		Entreprise e1= new Entreprise("MEDIANET", "Tunis");
		entrepriseServiceImpl.ajouterEntreprise(e1);
		entrepriseServiceImpl.getEntrepriseById(e1.getId());
		entrepriseServiceImpl.getAllDepartementsNamesByEntreprise(e1.getId());
		entrepriseServiceImpl.deleteEntrepriseById(e1.getId());
		
	}
	
	
	@After("execution(* tn.esprit.spring.service.*.*(..))")
	public void logMethodExit(JoinPoint joinPoint){
		String name= joinPoint.getSignature().getName();
		String msg= "Out of method : " +name;
		l.info(msg);
	}
	@Around("execution(* tn.esprit.spring.service.*.*(..))")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable {
	long start = System.currentTimeMillis();
	Object obj = pjp.proceed();
	long elapsedTime = System.currentTimeMillis() - start;
	if (elapsedTime > 3000) {
		l.fatal("This process takes more than 4 secondes to execute the function");
	}
	String msg= "Method execution time : " + elapsedTime + " milliseconds.";
	l.info(msg);
		return obj;
	}
}