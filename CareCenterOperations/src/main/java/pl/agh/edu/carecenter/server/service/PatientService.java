package pl.agh.edu.carecenter.server.service;

import java.util.List;

import pl.agh.edu.carecenter.android.transferobject.AndroidCarePlan;
import pl.agh.edu.carecenter.android.transferobject.AndroidPanicAlert;
import pl.agh.edu.carecenter.android.transferobject.AndroidReport;


public interface PatientService {

	List<AndroidCarePlan> getPatientsCarePlans(String username);
	void saveAlarm(AndroidPanicAlert alert);
	void saveReport(AndroidReport report);
	
}
