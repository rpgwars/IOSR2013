package pl.agh.edu.carecenter.server.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import pl.agh.edu.carecenter.android.transferobject.AndroidCarePlan;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class PatientServiceTest {
	
	@Autowired
	private PatientService patientService; 
	
	@Test
	public void testAndroidCarePlans(){
		
		List<AndroidCarePlan> acp1 = patientService.getPatientsCarePlans("p1");
		List<AndroidCarePlan> acp2 = patientService.getPatientsCarePlans("p2");
		List<AndroidCarePlan> acp3 = patientService.getPatientsCarePlans("p3");
		List<AndroidCarePlan> acp4 = patientService.getPatientsCarePlans("xxx");
		
		assertEquals(acp4.size(),0);
		assertEquals(acp1.size(),1);
		assertEquals(acp2.size(),1);
		assertEquals(acp3.size(),1);
		
		
		AndroidCarePlan androidCarePlan = acp2.get(0);
		assertEquals(androidCarePlan.getRemarks(),"prosze sie nie zmeczyc za bardzo");
		assertEquals(androidCarePlan.getAndroindActivityList().size(),2);
		assertEquals(androidCarePlan.getAndroindActivityList().get(1).getDescription(), "zimnej");
		
		
	}

}
