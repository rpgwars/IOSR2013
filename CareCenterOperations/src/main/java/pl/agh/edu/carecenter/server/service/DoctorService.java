package pl.agh.edu.carecenter.server.service;

import java.util.List;

import pl.agh.edu.carecenter.server.domain.Activity;
import pl.agh.edu.carecenter.server.domain.ActivityCategory;
import pl.agh.edu.carecenter.server.domain.CarePlan;
import pl.agh.edu.carecenter.server.domain.PatientCarePlan;
import pl.agh.edu.carecenter.server.exceptions.CategoryDoesNotExist;

public interface DoctorService {
	
	List<String> getDegreeList();
	void saveCategory(ActivityCategory category);
	List<ActivityCategory> listCategories(boolean populateActivities); 
	List<Activity> listActivities();
	void saveActivity(Activity activity) throws CategoryDoesNotExist;
	void saveCarePlan(CarePlan carePlan);
	List<CarePlan> listPossibleCarePlans(Integer patientId);
	List<CarePlan> listAssignedCarePlans(Integer patientId);
	void assignCarePlan(PatientCarePlan patientCarePlan, Integer patientId);

}
