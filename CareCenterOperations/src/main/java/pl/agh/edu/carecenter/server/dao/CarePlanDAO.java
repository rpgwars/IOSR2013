package pl.agh.edu.carecenter.server.dao;

import java.util.List;

import pl.agh.edu.carecenter.server.domain.CarePlan;
import pl.agh.edu.carecenter.server.domain.PatientCarePlan;

public interface CarePlanDAO extends GenericDAO<CarePlan>{
	
	void saveCarePlan(CarePlan carePlan);
	List<CarePlan> listPossibleCarePlans(Integer patientId);
	List<CarePlan> listAssignedCarePlans(Integer patientId);
	void savePatientCarePlan(PatientCarePlan patientCarePlan, Integer carePlanId, Integer patientId);

}
