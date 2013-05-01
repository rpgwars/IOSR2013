package pl.agh.edu.carecenter.server.dao;

import java.util.List;

import pl.agh.edu.carecenter.server.domain.Account;
import pl.agh.edu.carecenter.server.domain.CareGroup;
import pl.agh.edu.carecenter.server.domain.Doctor;
import pl.agh.edu.carecenter.server.domain.Patient;
import pl.agh.edu.carecenter.server.exceptions.AccountAlreadyExists;
import pl.agh.edu.carecenter.server.exceptions.AccountNotFound;
import pl.agh.edu.carecenter.server.exceptions.GroupDoesNotExist;

public interface AccountDAO extends GenericDAO<Account>{

	Account findAccount(String login) throws AccountNotFound;
	void saveAccount(Account account) throws AccountAlreadyExists, GroupDoesNotExist; 
	List<Doctor> listDoctors();
	void saveGroup(CareGroup group);
	List<CareGroup> listGroups();
	List<CareGroup> getAccountGroups(String userName) throws AccountNotFound;
	List<Patient> getAllPatientsFromGroups(List<Integer> groupIds);
	List<CareGroup> getPossibleAssignGroups(List<Integer> patientsGroups, List<Integer> doctorsGroups);
	void assingGroup(Integer accountId, Integer groupId);
}
