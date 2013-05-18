package pl.agh.edu.carecenter.server.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import pl.agh.edu.carecenter.server.domain.Activity;
import pl.agh.edu.carecenter.server.domain.ActivityCarePlan;
import pl.agh.edu.carecenter.server.domain.CarePlan;
import pl.agh.edu.carecenter.server.domain.Patient;
import pl.agh.edu.carecenter.server.domain.PatientCarePlan;
import pl.agh.edu.carecenter.server.exceptions.AccountAlreadyExists;
import pl.agh.edu.carecenter.server.exceptions.CategoryDoesNotExist;
import pl.agh.edu.carecenter.server.exceptions.GroupDoesNotExist;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CarePlanTest {
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private AccountService accountService;
	
	@Test
	public void testPossibleCarePlan(){
		
		Patient patient = new Patient();
		patient.setEmail("newPatient");
		try {
			accountService.saveAccount(patient);
		} catch (AccountAlreadyExists e) {
			fail();
		} catch (GroupDoesNotExist e) {
			fail();
		}
		
		
		int nrOfAssignedCarePlans = 0; 
		
		for(CarePlan carePlan : doctorService.listPossibleCarePlans(patient.getId())){
			assertEquals(doctorService.listAssignedCarePlans(patient.getId()).size(),nrOfAssignedCarePlans);
			assertEquals(3, doctorService.listPossibleCarePlans(patient.getId()).size());
			PatientCarePlan patientCarePlan = new PatientCarePlan(); 
			patientCarePlan.setCarePlanId(carePlan.getId());
			doctorService.assignCarePlan(patientCarePlan, patient.getId());
			nrOfAssignedCarePlans++; 
			
			
		}
		
		
	}
	
	@Test
	public void testCarePlanSave(){
		
		CarePlan carePlan = new CarePlan();
		carePlan.setCarePlanName("myCarePlan");
		Activity activity = new Activity();
		activity.setActivityName("myActivity");
		
		activity.setCategoryId(doctorService.listCategories(false).get(0).getId());
		
		try {
			doctorService.saveActivity(activity);
		} catch (CategoryDoesNotExist e) {
			fail(); 
		}
		
		ActivityCarePlan acp = new ActivityCarePlan(); 
		acp.setActivityId(activity.getId());
		
		List<ActivityCarePlan> acpList = new ArrayList<ActivityCarePlan>(1);
		acpList.add(acp);
		
		carePlan.setActivityCarePlanList(acpList);
		doctorService.saveCarePlan(carePlan);
		
		assertEquals(carePlan.getActivityCarePlanList().get(0).getActivity().getActivityName(),"myActivity"); 
		
	}

}
