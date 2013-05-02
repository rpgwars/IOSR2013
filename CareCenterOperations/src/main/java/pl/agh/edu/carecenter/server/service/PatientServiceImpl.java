package pl.agh.edu.carecenter.server.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.agh.edu.carecenter.android.transferobject.AndroidActivity;
import pl.agh.edu.carecenter.android.transferobject.AndroidCarePlan;
import pl.agh.edu.carecenter.server.dao.CarePlanDAO;
import pl.agh.edu.carecenter.server.domain.Activity;
import pl.agh.edu.carecenter.server.domain.ActivityCarePlan;
import pl.agh.edu.carecenter.server.domain.CarePlan;
import pl.agh.edu.carecenter.server.domain.PatientCarePlan;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private CarePlanDAO carePlanDao; 
	
	@Override
	public List<AndroidCarePlan> getPatientsCarePlans(String username) {
		
		List<PatientCarePlan> patientCarePlanList = carePlanDao.getPatientsCarePlans(username);
		List<AndroidCarePlan> androidCarePlans = new ArrayList<AndroidCarePlan>(patientCarePlanList.size());
		
		for(PatientCarePlan patientCarePlan : patientCarePlanList){
			AndroidCarePlan androidCarePlan = new AndroidCarePlan();
			
			CarePlan carePlan = patientCarePlan.getCarePlan();
			
			androidCarePlan.setId(carePlan.getId());
			androidCarePlan.setStartDate(patientCarePlan.getStartDate());
			androidCarePlan.setEndDate(patientCarePlan.getEndDate());
			androidCarePlan.setRemarks(patientCarePlan.getRemarks());
			
			List<ActivityCarePlan> activityCarePlanList = carePlan.getActivityCarePlanList();
			List<AndroidActivity> androidActivityList = new ArrayList<AndroidActivity>(activityCarePlanList.size()); 
			
			for(ActivityCarePlan activityCarePlan : activityCarePlanList){
				AndroidActivity androidActivity = new AndroidActivity(); 
				Activity activity = activityCarePlan.getActivity();
				
				androidActivity.setId(activity.getId());
				androidActivity.setDescription(activity.getDescription());
				androidActivity.setName(activity.getActivityName());
				androidActivity.setDayOfWeek(activityCarePlan.getDayOfWeek());
				androidActivity.setHourOfDay(activityCarePlan.getHourOfDay());
				
				androidActivityList.add(androidActivity);
			}
			
			androidCarePlans.add(androidCarePlan);
		}
		
		return androidCarePlans;
	}

}
