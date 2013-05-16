package pl.agh.edu.carecenter.server.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import pl.agh.edu.carecenter.android.transferobject.AndroidPanicAlert;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AlarmTest {
	
	@Autowired
	private DoctorService doctorServie;
	
	@Autowired 
	private PatientService patientService; 
	
	
	@Test
	public void ableToRemoveTest(){
		
		assertTrue(doctorServie.isAbleToRemoveAlarm("d1", 1));
		assertFalse(doctorServie.isAbleToRemoveAlarm("d1", 2));
		
	}
	
	@Test
	public void saveAlarmTest(){
		
		AndroidPanicAlert x = new AndroidPanicAlert();
		x.setUsername("p1");
		patientService.saveAlarm(x);
		assertEquals(doctorServie.listAlarms().size(),5); 
	}

}
