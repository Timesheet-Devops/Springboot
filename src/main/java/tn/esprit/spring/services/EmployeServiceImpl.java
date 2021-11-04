package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class EmployeServiceImpl implements IEmployeService {

	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;

	private static final Logger l = LogManager.getLogger(EmployeServiceImpl.class);

	public int ajouterEmploye(Employe employe) {
		employeRepository.save(employe);
		return employe.getId();
	}

	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		Optional<Employe> employeopt = employeRepository.findById(employeId);
		Employe employe = employeopt.orElseGet(Employe::new);
		employe.setEmail(email);
		employeRepository.save(employe);

	}

	@Transactional
	public void affecterEmployeADepartement(int employeId, int depId) {
		Optional<Departement> depManagedEntityopt = deptRepoistory.findById(depId);
		Departement depManagedEntity = depManagedEntityopt.orElseGet(Departement::new);
		Optional<Employe> employeManagedEntityopt = employeRepository.findById(employeId);
		Employe employeManagedEntity = employeManagedEntityopt.orElseGet(Employe::new);

		if (depManagedEntity.getEmployes() == null) {

			List<Employe> employes = new ArrayList<>();
			employes.add(employeManagedEntity);
			depManagedEntity.setEmployes(employes);
		} else {

			depManagedEntity.getEmployes().add(employeManagedEntity);

		}

	}

	@Transactional
	public void desaffecterEmployeDuDepartement(int employeId, int depId) {
		Optional<Departement> depopt = deptRepoistory.findById(depId);
		Departement dep = depopt.orElseGet(Departement::new);
		int employeNb = dep.getEmployes().size();
		for (int index = 0; index < employeNb; index++) {
			if (dep.getEmployes().get(index).getId() == employeId) {
				dep.getEmployes().remove(index);
				break;// a revoir
			}
		}
	}

	public int ajouterContrat(Contrat contrat) {
		contratRepoistory.save(contrat);
		return contrat.getReference();
	}

	public void affecterContratAEmploye(int contratId, int employeId) {
		try {
		Optional<Contrat> contratManagedEntityopt = contratRepoistory.findById(contratId);
		Contrat contratManagedEntity = contratManagedEntityopt.orElseGet(Contrat::new);
		Optional<Employe> employeManagedEntityopt = employeRepository.findById(employeId);
		Employe employeManagedEntity = employeManagedEntityopt.orElseGet(Employe::new);

		contratManagedEntity.setEmploye(employeManagedEntity);
		contratRepoistory.save(contratManagedEntity);
		    
		}catch(NullPointerException npe){
			l.error(npe);
		}

	}

	public String getEmployePrenomById(int employeId) {
		Optional<Employe> employeManagedEntityopt = employeRepository.findById(employeId);
		Employe employeManagedEntity = employeManagedEntityopt.orElseGet(Employe::new);

		return employeManagedEntity.getPrenom();
	}

	public void deleteEmployeById(int employeId) {
		Optional<Employe> employeopt = employeRepository.findById(employeId);
		Employe employe = employeopt.orElseGet(Employe::new);

		// Desaffecter l'employe de tous les departements
		// c'est le bout master qui permet de mettre a jour
		// la table d'association
		if (employe != null) {
		for (Departement dep : employe.getDepartements()) {
			dep.getEmployes().remove(employe);
		}
		employeRepository.delete(employe);

		} else {
			l.info("Employe Not Exist");
		}

	}

	public void deleteContratById(int contratId) {
		Optional<Contrat> contratManagedEntityopt = contratRepoistory.findById(contratId);
		Contrat contratManagedEntity = contratManagedEntityopt.orElseGet(Contrat::new);
		if (contratManagedEntity != null) {
			contratRepoistory.delete(contratManagedEntity);
		} else {
			l.error("Contrat Not Exist");
		}

	}

	public int getNombreEmployeJPQL() {
		return employeRepository.countemp();
	}

	public List<String> getAllEmployeNamesJPQL() {
		return employeRepository.employeNames();

	}

	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		return employeRepository.getAllEmployeByEntreprisec(entreprise);
	}

	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);

	}

	public void deleteAllContratJPQL() {
		l.debug("Start Deleting Contracts");
		employeRepository.deleteAllContratJPQL();
		l.debug("Stop Deleting Contracts");
	}

	public float getSalaireByEmployeIdJPQL(int employeId) {
		return employeRepository.getSalaireByEmployeIdJPQL(employeId);
	}

	public Double getSalaireMoyenByDepartementId(int departementId) {
		return employeRepository.getSalaireMoyenByDepartementId(departementId);
	}

	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		return timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
	}

	public List<Employe> getAllEmployes() {
		return (List<Employe>) employeRepository.findAll();
	}

}