package pl.agh.edu.carecenter.server.service;

import java.util.List;

import pl.agh.edu.carecenter.android.transferobject.AndroidCarePlan;
import pl.agh.edu.carecenter.android.transferobject.AndroidPanicAlert;


public interface PatientService {

	List<AndroidCarePlan> getPatientsCarePlans(String username);
	void saveAlarm(AndroidPanicAlert alert);
	
}
