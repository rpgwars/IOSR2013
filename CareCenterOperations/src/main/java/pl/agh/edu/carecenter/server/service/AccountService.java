package pl.agh.edu.carecenter.server.service;

import java.util.List;

import pl.agh.edu.carecenter.server.domain.Account;
import pl.agh.edu.carecenter.server.domain.CareGroup;
import pl.agh.edu.carecenter.server.domain.Doctor;
import pl.agh.edu.carecenter.server.domain.Patient;
import pl.agh.edu.carecenter.server.exceptions.AccountAlreadyExists;
import pl.agh.edu.carecenter.server.exceptions.AccountNotFound;
import pl.agh.edu.carecenter.server.exceptions.GroupDoesNotExist;

public interface AccountService {
	
	Account getAccountById(Integer id);
	Account getAccountByLogin(String login) throws AccountNotFound;
	void saveAccount(Account account) throws AccountAlreadyExists, GroupDoesNotExist;
	void saveAccount(Account account, String login) throws AccountAlreadyExists, GroupDoesNotExist, AccountNotFound;
	List<Doctor> listDoctors(); 
	void saveGroup(CareGroup group);
	List<CareGroup> listGroups();
	List<Integer> listGroupIds(String loggedInUserName) throws AccountNotFound;
	List<CareGroup> listGroups(String loggedInUserName) throws AccountNotFound;
	List<Patient> listPatients(String loggedInUserName) throws AccountNotFound;
	List<Integer> getPossibleAssignGroups(String doctorLogin, String patientLogin) throws AccountNotFound;
	void assignGroup(Integer groupId, Integer patientId, Integer doctorId) throws AccountNotFound;

}
