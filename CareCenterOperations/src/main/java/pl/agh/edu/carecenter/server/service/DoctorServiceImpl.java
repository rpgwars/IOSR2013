package pl.agh.edu.carecenter.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.agh.edu.carecenter.server.dao.AccountDAO;
import pl.agh.edu.carecenter.server.dao.ActivityDAO;
import pl.agh.edu.carecenter.server.dao.AlarmDAO;
import pl.agh.edu.carecenter.server.dao.CarePlanDAO;
import pl.agh.edu.carecenter.server.dao.DoctorDAO;
import pl.agh.edu.carecenter.server.dao.ReportDAO;
import pl.agh.edu.carecenter.server.domain.Account;
import pl.agh.edu.carecenter.server.domain.Activity;
import pl.agh.edu.carecenter.server.domain.ActivityCategory;
import pl.agh.edu.carecenter.server.domain.Alarm;
import pl.agh.edu.carecenter.server.domain.CareGroup;
import pl.agh.edu.carecenter.server.domain.CarePlan;
import pl.agh.edu.carecenter.server.domain.PatientCarePlan;
import pl.agh.edu.carecenter.server.domain.Report;
import pl.agh.edu.carecenter.server.exceptions.AccountNotFound;
import pl.agh.edu.carecenter.server.exceptions.CategoryDoesNotExist;

@Service
public class DoctorServiceImpl implements DoctorService{

	@Autowired
	private DoctorDAO doctorDao; 
	
	@Autowired
	private ActivityDAO activityDao; 
	
	@Autowired
	private CarePlanDAO carePlanDao; 
	
	@Autowired
	private AlarmDAO alarmDao; 
	
	@Autowired
	private AccountDAO accountDAO;  
	
	@Autowired
	private ReportDAO reportDao;
	
		
	@Override
	public List<String> getDegreeList() {
		return doctorDao.getDegrees();
	}

	@Override
	public void saveCategory(ActivityCategory category) {
		activityDao.saveCategory(category);
		
	}

	@Override
	public List<ActivityCategory> listCategories(boolean populateActivities) {
		return activityDao.listCategories(populateActivities);
	}

	@Override
	public List<Activity> listActivities() {
		return (List<Activity>) activityDao.list(Activity.class);
	}

	
	@Override
	public void saveActivity(Activity activity) throws CategoryDoesNotExist {
		activityDao.saveActivity(activity); 
	}

	@Override
	public void saveCarePlan(CarePlan carePlan) {
		carePlanDao.saveCarePlan(carePlan);
	}

	@Override
	public List<CarePlan> listPossibleCarePlans(Integer patientId) {
		return carePlanDao.listPossibleCarePlans(patientId);
	}

	@Override
	public List<CarePlan> listAssignedCarePlans(Integer patientId) {
		return carePlanDao.listAssignedCarePlans(patientId);
	}

	@Override
	public void assignCarePlan(PatientCarePlan patientCarePlan,
			Integer patientId) {
		
		Integer carePlanId = patientCarePlan.getCarePlanId();
		carePlanDao.savePatientCarePlan(patientCarePlan, carePlanId, patientId);
		
	}

	@Override
	public void removeAlarm(Integer alarmId) {
		Alarm alarm = alarmDao.getById(alarmId, Alarm.class);
		alarmDao.remove(alarm);
		
	}

	@Override
	public List<Alarm> listAlarms() {
		return (List<Alarm>) alarmDao.list(Alarm.class);
	}

	@Override
	public boolean isAbleToRemoveAlarm(String username, Integer alarmId) {
		try {
			
			List<CareGroup> doctorCareGroupList = accountDAO.getAccountGroups(username);
			List<CareGroup> patientCareGroupList = accountDAO.getAccountGroups(alarmDao.getPatientByAlarmId(alarmId).getEmail());
			for(CareGroup doctorCareGroup : doctorCareGroupList)
				for(CareGroup patientCareGroup : patientCareGroupList)
					if(doctorCareGroup.getId().equals(patientCareGroup.getId()))
							return true; 
		} catch (AccountNotFound e) {
			return false; 
		}
		return false;
	}

	@Override
	public List<CarePlan> listPlanReports(Integer patientId) {
		return reportDao.listPlanReports(patientId);
	}
	
	

}
