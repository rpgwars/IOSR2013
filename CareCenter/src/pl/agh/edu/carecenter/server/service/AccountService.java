package pl.agh.edu.carecenter.server.service;

import java.util.List;

import pl.agh.edu.carecenter.server.domain.Account;
import pl.agh.edu.carecenter.server.domain.Doctor;
import pl.agh.edu.carecenter.server.exceptions.AccountAlreadyExists;

public interface AccountService {
	
	void saveAccount(Account account) throws AccountAlreadyExists;
	List<Doctor> listDoctors(); 

}
