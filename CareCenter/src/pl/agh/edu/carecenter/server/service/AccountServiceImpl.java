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

import pl.agh.edu.carecenter.server.dao.AccountDAO;
import pl.agh.edu.carecenter.server.domain.Account;
import pl.agh.edu.carecenter.server.domain.AccountRole;
import pl.agh.edu.carecenter.server.domain.Doctor;
import pl.agh.edu.carecenter.server.exceptions.AccountAlreadyExists;
import pl.agh.edu.carecenter.server.exceptions.AccountNotFound;

@Service
public class AccountServiceImpl implements AccountService, UserDetailsService{

	@Autowired
	private AccountDAO accountDao; 
	
	
	@Override
	public void saveAccount(Account account) throws AccountAlreadyExists {
		
		accountDao.saveAccount(account);
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
		boolean locked = false; 
		

		return new User(
				domainUser.getEmail(),
				domainUser.getPassword(),
				enabled,
				accountNonExpired,
				credentialsNonExpired,
				locked,
				getGrantedAuthorities(domainUser.getAccountRole()));
	}
	
	private static List<GrantedAuthority> getGrantedAuthorities(List<AccountRole> accountRoles){
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>(); 
		for(AccountRole ar : accountRoles){
			list.add(new SimpleGrantedAuthority(ar.getRole()));
		}
		
		return list;  
	}


}
