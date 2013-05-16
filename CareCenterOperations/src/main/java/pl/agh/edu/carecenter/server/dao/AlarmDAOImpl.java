package pl.agh.edu.carecenter.server.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.agh.edu.carecenter.server.domain.Alarm;
import pl.agh.edu.carecenter.server.domain.Patient;

@Repository
public class AlarmDAOImpl extends GenericDAOImpl<Alarm> implements AlarmDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public void saveAlarm(Alarm alarm, String username){
		
		Criteria patientCriteria = sessionFactory.getCurrentSession().createCriteria(Patient.class);
		patientCriteria.add(Restrictions.eq("email", username));
		Patient patient = (Patient) patientCriteria.uniqueResult();
		
		patient.addAlarm(alarm);
		alarm.setPatient(patient);
		sessionFactory.getCurrentSession().save(alarm);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Patient getPatientByAlarmId(Integer alarmId) {
		
		Criteria patientCriteria = sessionFactory.getCurrentSession().createCriteria(Patient.class).createAlias("alarmList", "al").
		add(Restrictions.eq("al.id", alarmId));
		
		return (Patient) patientCriteria.uniqueResult();
	}

}
