package pl.agh.edu.carecenter.server.dao;

import pl.agh.edu.carecenter.server.domain.CarePlan;

public interface CarePlanDAO extends GenericDAO<CarePlan>{
	
	void saveCarePlan(CarePlan carePlan);

}
