package pl.agh.edu.carecenter.server.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.agh.edu.carecenter.server.domain.Account;
import pl.agh.edu.carecenter.server.domain.Doctor;
import pl.agh.edu.carecenter.server.exceptions.AccountAlreadyExists;
import pl.agh.edu.carecenter.server.exceptions.AccountNotFound;


@Repository
public class AccountDAOImpl implements AccountDAO{
	
	@Autowired
	private SessionFactory sessionFactory; 
	
	@Override
	@Transactional(readOnly = true)
	public Account findAccount(String login) throws AccountNotFound {
		
		String command = "from Account where email = :login";
		
		Query query = sessionFactory.getCurrentSession().createQuery(command);
		query.setParameter("login", login);
		
		Account result = (Account)query.uniqueResult();  
				
		if(result == null)
			throw new AccountNotFound();
			
		return result;  
	}

	@Override
	@Transactional
	public void saveAccount(Account account) throws AccountAlreadyExists {

		try {
			findAccount(account.getEmail());
		} catch (AccountNotFound e) {
			sessionFactory.getCurrentSession().save(account);
			return; 
		}
		
		throw new AccountAlreadyExists();
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Doctor> listDoctors() {
		String hqlQuery = "from Doctor";
		Query query = sessionFactory.getCurrentSession().createQuery(hqlQuery);
		return query.list();
	}

}
