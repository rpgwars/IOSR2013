package pl.agh.edu.carecenter.server.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import pl.agh.edu.carecenter.android.transferobject.AndroidReport;
import pl.agh.edu.carecenter.server.domain.CarePlan;
import pl.agh.edu.carecenter.server.exceptions.AccountNotFound;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ReportTest {

	@Autowired
	private PatientService patientService; 
	
	@Autowired 
	private DoctorService doctorService;
	
	
	@Autowired
	private AccountService accountService; 
	
	@Test
	public void testReports() throws AccountNotFound{
		
		System.out.println(patientService.getPatientsCarePlans("p1").size());;
		
		int id1 = patientService.getPatientsCarePlans("p1").get(0).getId();
		int id2 = patientService.getPatientsCarePlans("p1").get(1).getId();
		int id3 = patientService.getPatientsCarePlans("p1").get(2).getId();
		int id4 = patientService.getPatientsCarePlans("p2").get(0).getId();
		int id5 = patientService.getPatientsCarePlans("p3").get(0).getId();
		
		AndroidReport androidReport1 = new AndroidReport(); 
		AndroidReport androidReport2 = new AndroidReport();
		AndroidReport androidReport3 = new AndroidReport(); 
		AndroidReport androidReport4 = new AndroidReport();
		AndroidReport androidReport5 = new AndroidReport(); 
		
		androidReport1.setCarePlanId(id1);
		androidReport1.setActivityId(1);
		androidReport1.setDateOfReport(new Date());
		androidReport1.setDone(true);
		androidReport1.setRemarks("bylo ciezko");
		
		androidReport2.setCarePlanId(id2);
		androidReport2.setActivityId(1);
		androidReport2.setDateOfReport(new Date());
		androidReport2.setDone(false);
		androidReport2.setRemarks("nie dalem rady");
		
		androidReport3.setCarePlanId(id3);
		androidReport3.setActivityId(1);
		androidReport3.setDateOfReport(new Date());
		androidReport3.setDone(true);
		androidReport3.setRemarks("bez uwag");
		
		androidReport4.setCarePlanId(id4);
		androidReport4.setActivityId(1);
		androidReport4.setDateOfReport(new Date());
		androidReport4.setDone(true);
		androidReport4.setRemarks("ok");
		
		androidReport5.setCarePlanId(id5);
		androidReport5.setActivityId(1);
		androidReport5.setDateOfReport(new Date());
		androidReport5.setDone(true);
		androidReport5.setRemarks("11111111");
		
		patientService.saveReport(androidReport1);
		patientService.saveReport(androidReport2);
		patientService.saveReport(androidReport3);
		patientService.saveReport(androidReport4);
		patientService.saveReport(androidReport5);
			
		assertEquals(doctorService.listPlanReports(accountService.getAccountByLogin("p1").getId()).size(),2);
		assertEquals(doctorService.listPlanReports(accountService.getAccountByLogin("p1").getId()).size(),2);
		
		List<CarePlan> list = doctorService.listPlanReports(accountService.getAccountByLogin("p1").getId());
		int size = list.get(0).getPatientCarePlanList().size() + list.get(1).getPatientCarePlanList().size();
		assertEquals(size, 3);
		
	}
}
