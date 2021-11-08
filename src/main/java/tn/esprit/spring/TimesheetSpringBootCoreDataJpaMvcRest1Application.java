package tn.esprit.spring;


import java.nio.file.Paths;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TimesheetSpringBootCoreDataJpaMvcRest1Application {

	public static void main(String[] args) {

	    PropertyConfigurator.configure(Paths.get(".").toAbsolutePath().normalize().toString()+"\\src\\main\\resources\\log4j2.properties");
	    
		SpringApplication.run(TimesheetSpringBootCoreDataJpaMvcRest1Application.class, args);
	}

}
