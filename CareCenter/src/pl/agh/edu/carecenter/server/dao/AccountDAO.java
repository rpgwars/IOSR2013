package pl.agh.edu.carecenter.server.dao;

import java.util.List;

import pl.agh.edu.carecenter.server.domain.Account;
import pl.agh.edu.carecenter.server.domain.Doctor;
import pl.agh.edu.carecenter.server.exceptions.AccountAlreadyExists;
import pl.agh.edu.carecenter.server.exceptions.AccountNotFound;

public interface AccountDAO {

	Account findAccount(String login) throws AccountNotFound;
	void saveAccount(Account account) throws AccountAlreadyExists; 
	List<Doctor> listDoctors();
}
