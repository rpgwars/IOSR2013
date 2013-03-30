package pl.agh.edu.carecenter.server.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.agh.edu.carecenter.server.domain.Account;
import pl.agh.edu.carecenter.server.domain.Doctor;
import pl.agh.edu.carecenter.server.exceptions.AccountAlreadyExists;
import pl.agh.edu.carecenter.server.exceptions.AccountNotFound;


@Repository
public class AccountDAOImpl extends GenericDAOImpl<Account> implements AccountDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional(readOnly = true)
	public Account findAccount(String login) throws AccountNotFound {
		
		Criteria accountCriteria = sessionFactory.getCurrentSession().createCriteria(Account.class);
		accountCriteria.add(Restrictions.eq("email", login));

		Account result = (Account) accountCriteria.uniqueResult();  
				
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
			super.save(account);
			return; 
		}
		
		throw new AccountAlreadyExists();
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Doctor> listDoctors() {
		
		Criteria doctorListCriteria = sessionFactory.getCurrentSession().createCriteria(Doctor.class);
		return doctorListCriteria.list();
	}

}
