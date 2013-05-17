package pl.agh.edu.carecenter.server.dao;

import java.util.List;

import pl.agh.edu.carecenter.server.domain.CarePlan;
import pl.agh.edu.carecenter.server.domain.Report;

public interface ReportDAO extends GenericDAO<Report>{
	
	void saveReport(Report report);
	List<CarePlan> listPlanReports(Integer patientId);

}
