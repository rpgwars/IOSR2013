package pl.agh.edu.carecenter.server.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.agh.edu.carecenter.server.domain.Activity;
import pl.agh.edu.carecenter.server.domain.CarePlan;
import pl.agh.edu.carecenter.server.domain.PatientCarePlan;
import pl.agh.edu.carecenter.server.domain.Report;

@Repository
public class ReportDAOImp extends GenericDAOImpl<Report> implements ReportDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public void saveReport(Report report) {
		
		Criteria activityCritera = sessionFactory.getCurrentSession().createCriteria(Activity.class)
				.add(Restrictions.eq("id", report.getActivityId()));
		Criteria patientCarePlanCriteria = sessionFactory.getCurrentSession().createCriteria(PatientCarePlan.class).
				add(Restrictions.eq("id", report.getCarePlanId()));
		
		Activity activity = (Activity) activityCritera.uniqueResult();
		PatientCarePlan patientCarePlan = (PatientCarePlan) patientCarePlanCriteria.uniqueResult();
		
		sessionFactory.getCurrentSession().save(report);
		activity.addReport(report);
		patientCarePlan.addReport(report);
		report.setActivity(activity);
		report.setPatientCarePlan(patientCarePlan);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<CarePlan> listPlanReports(Integer patientId) {
		
		Criteria planReportsCriteria = sessionFactory.getCurrentSession().createCriteria(CarePlan.class).
				createAlias("patientCarePlanList", "pcpl").createAlias("pcpl.patient","pat").createAlias("pcpl.reportList", "rl").
					createAlias("rl.activity", "ac");
		
		
		planReportsCriteria.add(Restrictions.eq("pat.id", patientId)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<CarePlan> result = planReportsCriteria.list(); 
		for(CarePlan carePlan : result){
			Hibernate.initialize(carePlan.getPatientCarePlanList());
			for(PatientCarePlan patientCarePlan : carePlan.getPatientCarePlanList())
				Hibernate.initialize(patientCarePlan.getReportList());
		}
			
		return result; 
	}

}
