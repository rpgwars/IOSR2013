package pl.agh.edu.carecenter.server.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import pl.agh.edu.carecenter.server.domain.Account;
import pl.agh.edu.carecenter.server.domain.CareGroup;
import pl.agh.edu.carecenter.server.domain.Doctor;
import pl.agh.edu.carecenter.server.domain.Patient;
import pl.agh.edu.carecenter.server.exceptions.AccountNotFound;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AccountTest {

	
	@Autowired 
	private AccountDAO accountDao; 


	@Test(expected=AccountNotFound.class)
	public void testAccountExistence1() throws AccountNotFound{
		accountDao.findAccount("noNexistingLogin");
	}
	
	@Test
	public void testAccountExistence2() throws AccountNotFound{
		Account account = accountDao.findAccount("d1");
		assertEquals(account.getName(),"doktor 1");
	}
	
	@Test
	public void testNewAccountNewGroup(){
		
		CareGroup cg = new CareGroup();
		cg.setGroupName("new group");
		accountDao.saveGroup(cg);
		Doctor doctor = new Doctor();
		
		accountDao.save(doctor);
		accountDao.assingGroup(doctor.getId(), cg.getId());
		assertEquals(doctor.getAccountGroupList().size(), 1);
		
	}
	
	@Test
	public void testPossibleAssignemts(){
		
		List<CareGroup> careGroups = accountDao.listGroups();
		Doctor doctor = new Doctor(); 
		doctor.setEmail("newDoctor");
		accountDao.save(doctor);
		
		List<Integer> doctorsGroups = new ArrayList<Integer>(careGroups.size());
		for(CareGroup group : careGroups){
			accountDao.assingGroup(doctor.getId(), group.getId());	
			doctorsGroups.add(group.getId());
		}
		
		Patient patient = new Patient();
		accountDao.save(patient);
		accountDao.assingGroup(patient.getId(), careGroups.get(0).getId());
		List<Integer> patientsGroups = new ArrayList<Integer>();
		patientsGroups.add(careGroups.get(0).getId());
		
		
		assertEquals(0,accountDao.getPossibleAssignGroups(null, doctorsGroups).size());
		assertEquals(2, accountDao.getPossibleAssignGroups(patientsGroups, doctorsGroups).size());
		
	}
	
}
