package pl.agh.edu.carecenter.server.service;

import java.util.List;

import pl.agh.edu.carecenter.android.transferobject.AndroidCarePlan;


public interface PatientService {

	List<AndroidCarePlan> getPatientsCarePlans(String username);
	
}
