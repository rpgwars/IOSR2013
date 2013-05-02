package pl.agh.edu.carecenter.server.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.agh.edu.carecenter.server.domain.Activity;
import pl.agh.edu.carecenter.server.domain.ActivityCarePlan;
import pl.agh.edu.carecenter.server.domain.CarePlan;
import pl.agh.edu.carecenter.server.domain.Patient;
import pl.agh.edu.carecenter.server.domain.PatientCarePlan;

@Repository
public class CarePlanDAOImpl extends GenericDAOImpl<CarePlan> implements CarePlanDAO{

	@Autowired
	private SessionFactory sessionFactory; 

	@Override
	@Transactional
	public void saveCarePlan(CarePlan carePlan) {
		
		sessionFactory.getCurrentSession().save(carePlan);
		
		for(ActivityCarePlan activityCarePlan : carePlan.getActivityCarePlanList()){
			Integer activityId = activityCarePlan.getActivityId();
			
			Criteria activityCriteria = 
					sessionFactory.getCurrentSession().createCriteria(Activity.class);
			activityCriteria.add(Restrictions.eq("id", activityId));
			
			Activity activity = (Activity)activityCriteria.uniqueResult();
			activityCarePlan.setActivity(activity);
			activity.addActivityCarePlan(activityCarePlan);
			
			activityCarePlan.setCarePlan(carePlan);
		}
		
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<CarePlan> listPossibleCarePlans(Integer patientId) {
		
		Criteria possibleCarePlanCriteria = sessionFactory.getCurrentSession().createCriteria(CarePlan.class);
		List<Integer> patientsCarePlansIds = getPatientsCarePlans(patientId);
		if(patientsCarePlansIds.size() != 0)
			possibleCarePlanCriteria.add(Restrictions.not(Restrictions.in("id", patientsCarePlansIds)));
		
		List<CarePlan> possibleCarePlanList = possibleCarePlanCriteria.list();
		
		for(CarePlan carePlan : possibleCarePlanList){
			Hibernate.initialize(carePlan.getActivityCarePlanList());
			for(ActivityCarePlan activityCarePlan : carePlan.getActivityCarePlanList())
				Hibernate.initialize(activityCarePlan.getActivity());
		}
		
		return possibleCarePlanList; 
		
	}
	
	
	@Transactional(readOnly=true)
	private List<Integer> getPatientsCarePlans(Integer patientId){
		
		Criteria patientsCarePlansCriteria = sessionFactory.getCurrentSession().createCriteria(CarePlan.class);
		
		patientsCarePlansCriteria.createAlias("patientCarePlanList", "pcpl").
			createAlias("pcpl.patient", "pat").add(Restrictions.eq("pat.id", patientId));
		
		List<CarePlan> patientsCarePlanList = patientsCarePlansCriteria.list();
		List<Integer> patientsCarePlanIdList = new ArrayList<Integer>(patientsCarePlanList.size());
		
		for(CarePlan carePlan : patientsCarePlanList)
			patientsCarePlanIdList.add(carePlan.getId());
		
		return patientsCarePlanIdList;
			
	}

	@Override
	@Transactional(readOnly=true)
	public List<CarePlan> listAssignedCarePlans(Integer patientId) {
		Criteria patientsCarePlansCriteria = sessionFactory.getCurrentSession().createCriteria(CarePlan.class);
		
		patientsCarePlansCriteria.createAlias("patientCarePlanList", "pcpl").
			createAlias("pcpl.patient", "pat").add(Restrictions.eq("pat.id", patientId));
		
		List<CarePlan> assignedCarePlanList = patientsCarePlansCriteria.list(); 
		
		for(CarePlan carePlan : assignedCarePlanList){
			Hibernate.initialize(carePlan.getActivityCarePlanList());
			for(ActivityCarePlan activityCarePlan : carePlan.getActivityCarePlanList())
				Hibernate.initialize(activityCarePlan.getActivity());
		}
		
		return assignedCarePlanList;
	}

	@Override
	@Transactional
	public void savePatientCarePlan(PatientCarePlan patientCarePlan, Integer carePlanId, Integer patientId) {
		
		Criteria patientCriteria = sessionFactory.getCurrentSession().createCriteria(Patient.class);
		patientCriteria.add(Restrictions.eq("id", patientId));
		Patient patient = (Patient) patientCriteria.uniqueResult();
		
		Criteria carePlanCriteria = sessionFactory.getCurrentSession().createCriteria(CarePlan.class);
		carePlanCriteria.add(Restrictions.eq("id", carePlanId));
		CarePlan carePlan = (CarePlan) carePlanCriteria.uniqueResult();
		
		sessionFactory.getCurrentSession().save(patientCarePlan);
		
		patientCarePlan.setCarePlan(carePlan);
		patientCarePlan.setPatient(patient);
		patient.addPatientCarePlan(patientCarePlan);
		carePlan.addPatientCarePlan(patientCarePlan);
		
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<PatientCarePlan> getPatientsCarePlans(String username) {
		
		Criteria patientCarePlansCriteria = sessionFactory.getCurrentSession().createCriteria(PatientCarePlan.class);
		
		patientCarePlansCriteria.createAlias("patient", "pat").
			createAlias("carePlan", "cp").createAlias("cp.activityCarePlanList", "acpl").
				createAlias("acpl.activity", "act");
		
		patientCarePlansCriteria.add(Restrictions.eq("pat.email", username)).
			setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		List<PatientCarePlan> patientCarePlanList = patientCarePlansCriteria.list();
		for(PatientCarePlan patientCarePlan : patientCarePlanList){
			Hibernate.initialize(patientCarePlan.getCarePlan());
			
			CarePlan carePlan = patientCarePlan.getCarePlan();
			Hibernate.initialize(carePlan.getActivityCarePlanList());
			for(ActivityCarePlan activityCarePlan : carePlan.getActivityCarePlanList())
				Hibernate.initialize(activityCarePlan.getActivity());
			
		}
		
		return patientCarePlanList;
	}

	
}
