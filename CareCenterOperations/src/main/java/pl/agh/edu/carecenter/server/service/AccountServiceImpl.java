package pl.agh.edu.carecenter.server.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.agh.edu.carecenter.server.dao.AccountDAO;
import pl.agh.edu.carecenter.server.domain.Account;
import pl.agh.edu.carecenter.server.domain.AccountRole;
import pl.agh.edu.carecenter.server.domain.CareGroup;
import pl.agh.edu.carecenter.server.domain.Doctor;
import pl.agh.edu.carecenter.server.domain.Patient;
import pl.agh.edu.carecenter.server.exceptions.AccountAlreadyExists;
import pl.agh.edu.carecenter.server.exceptions.AccountNotFound;
import pl.agh.edu.carecenter.server.exceptions.GroupDoesNotExist;

@Service
public class AccountServiceImpl implements AccountService, UserDetailsService{

	@Autowired 
	private AccountDAO accountDao; 
	
	
	@Override
	public void saveAccount(Account account) throws AccountAlreadyExists, GroupDoesNotExist{
		
		accountDao.saveAccount(account);
	}
	
	@Override
	public void saveAccount(Account account, String login)
			throws AccountAlreadyExists, GroupDoesNotExist, AccountNotFound {
		
		List<Integer> userGroups = listGroupIds(login);
		if(userGroups.contains(account.getGroupId()))
				accountDao.saveAccount(account);
		else
			throw new GroupDoesNotExist();
		
	}


	@Override
	public List<Doctor> listDoctors() {
		
		return accountDao.listDoctors();
	}


	@Override
	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {
		
		Account domainUser;
		try {
			domainUser = accountDao.findAccount(login);
		} catch (AccountNotFound e) {
			throw new RuntimeException("No such account"); 
		}

		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean nonLocked = true; 
		return new User(
				domainUser.getEmail(),
				domainUser.getPassword(),
				enabled,
				accountNonExpired,
				credentialsNonExpired,
				nonLocked,
				getGrantedAuthorities(domainUser.getAccountRole()));
	}
	
	private static List<GrantedAuthority> getGrantedAuthorities(List<AccountRole> accountRoles){
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>(); 
		for(AccountRole ar : accountRoles){
			list.add(new SimpleGrantedAuthority(ar.getRole()));
			
		}
		
		return list;  
	}


	@Override
	public void saveGroup(CareGroup group) {
		accountDao.saveGroup(group);
		
	}


	@Override
	public List<CareGroup> listGroups() {
		return accountDao.listGroups();
	}


	@Override
	@Transactional(readOnly = true)
	public List<Integer> listGroupIds(String loggedInUserName) throws AccountNotFound {
		
		List<CareGroup> careGroupList = accountDao.getAccountGroups(loggedInUserName);
		List<Integer> careGroupIds = new ArrayList<Integer>(careGroupList.size());
		
		for(CareGroup careGroup : careGroupList)
			careGroupIds.add(careGroup.getId());
		
		return careGroupIds;
	}


	@Override
	public List<Patient> listPatients(String loggedInUserName) throws AccountNotFound {
		List<Integer> patientGroupIds = listGroupIds(loggedInUserName);
		return accountDao.getAllPatientsFromGroups(patientGroupIds);
		
	}


	@Override
	public List<CareGroup> listGroups(String loggedInUserName)
			throws AccountNotFound {
		return accountDao.getAccountGroups(loggedInUserName);
	}

	@Override
	public List<Integer> getPossibleAssignGroups(String doctorLogin, String patientLogin) throws AccountNotFound {
		if(patientLogin == null){
			return getIds(accountDao.getPossibleAssignGroups(null, listGroupIds(doctorLogin)));
		}
		return getIds(accountDao.getPossibleAssignGroups(listGroupIds(patientLogin), listGroupIds(doctorLogin)));
		
	}
	
	private List<Integer> getIds(List<CareGroup> careGroups){
		
		List<Integer> list = new ArrayList<Integer>(careGroups.size());
		for(CareGroup careGroup : careGroups)
			list.add(careGroup.getId());
		return list; 
	}

	@Override
	public Account getAccountById(Integer id) {
		return accountDao.getById(id, Account.class);
	}

	@Override
	public void assignGroup(Integer groupId, Integer patientId, Integer doctorId) throws AccountNotFound {
		
		
		if(patientId == null){
			accountDao.assingGroup(doctorId, groupId);
		}
		else{
			Account doctorAccount = accountDao.getById(doctorId, Account.class);
			Account patientAccount = accountDao.getById(patientId, Account.class);
			
			List<Integer> possibilities = getPossibleAssignGroups(doctorAccount.getEmail(), patientAccount.getEmail());
			if(possibilities.contains(groupId))
				accountDao.assingGroup(patientId, groupId);
			
		}
	}

	@Override
	public Account getAccountByLogin(String login) throws AccountNotFound {
		return accountDao.findAccount(login);
	}


}
