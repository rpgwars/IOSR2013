package pl.agh.edu.carecenter.server.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.agh.edu.carecenter.server.domain.Account;
import pl.agh.edu.carecenter.server.domain.AccountGroup;
import pl.agh.edu.carecenter.server.domain.CareGroup;
import pl.agh.edu.carecenter.server.domain.Doctor;
import pl.agh.edu.carecenter.server.domain.Patient;
import pl.agh.edu.carecenter.server.exceptions.AccountAlreadyExists;
import pl.agh.edu.carecenter.server.exceptions.AccountNotFound;
import pl.agh.edu.carecenter.server.exceptions.GroupDoesNotExist;


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
	public void saveAccount(Account account) throws AccountAlreadyExists, GroupDoesNotExist {

		try {
			findAccount(account.getEmail());
		} catch (AccountNotFound e) {
			
			if(account.getGroupId() != null){
				Criteria groupCriteria = sessionFactory.getCurrentSession().createCriteria(CareGroup.class);
				groupCriteria.add(Restrictions.eq("id", account.getGroupId()));
				CareGroup group = (CareGroup) groupCriteria.uniqueResult();
				if(group != null){
					AccountGroup ag = new AccountGroup();
					ag.setAccount(account);
					ag.setCareGroup(group);
					group.addAccountGroup(ag);
					account.addAccountGroup(ag);
				}
				else{
					throw new GroupDoesNotExist();
				}
					
			}
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

	@Override
	@Transactional
	public void saveGroup(CareGroup group) {
		sessionFactory.getCurrentSession().save(group);
	}

	@Override
	@Transactional
	public List<CareGroup> listGroups() {
		
		return (List<CareGroup>) super.list(CareGroup.class);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<CareGroup> getAccountGroups(String userName) throws AccountNotFound {
		Account account = findAccount(userName);
		Hibernate.initialize(account.getAccountGroupList());
		List<CareGroup> accountGroupList = new ArrayList<CareGroup>();
		for(AccountGroup accountGroup : account.getAccountGroupList()){
			accountGroupList.add(accountGroup.getCareGroup());
		}
		 
		return accountGroupList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Patient> getAllPatientsFromGroups(List<Integer> groupIds) {
		if(groupIds.size() == 0)
			return new ArrayList<Patient>(0);
		
		Criteria patientFromGroupCriteria = 
				sessionFactory.getCurrentSession().createCriteria(Patient.class).
					createAlias("accountGroupList","acg").createAlias("acg.careGroup","gr").
						add(Restrictions.in("gr.id", groupIds));
		List<Patient> result = patientFromGroupCriteria.list();
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CareGroup> getPossibleAssignGroups(List<Integer> patientsGroups, List<Integer> doctorsGroups) {
		
		Criteria careGroupCriteria = sessionFactory.getCurrentSession().createCriteria(CareGroup.class);
				
		//select from all groups -> admin assigns doctors
		if(patientsGroups == null){
			if(doctorsGroups.size() > 0)
				careGroupCriteria.add(Restrictions.not(Restrictions.in("id", doctorsGroups)));
			
		}
		//select from doctors groups -> doctor assigns patient
		else{
			if(doctorsGroups.size() == 0)
				return new ArrayList<CareGroup>(0);
			Criterion inDoctorsGroupsCriterion  = Restrictions.in("id", doctorsGroups);
			if(patientsGroups.size() > 0){
				Criterion notInPatientsGroupsCriterion = Restrictions.not(Restrictions.in("id", patientsGroups));
				careGroupCriteria.add(Restrictions.and(inDoctorsGroupsCriterion,notInPatientsGroupsCriterion));
			}
			else
				careGroupCriteria.add(inDoctorsGroupsCriterion);
		}
		
		return careGroupCriteria.list();
	}

	@Override
	@Transactional
	public void assingGroup(Integer accountId, Integer groupId) {
		
		Criteria accountGropuCritera = sessionFactory.getCurrentSession().createCriteria(AccountGroup.class);
		accountGropuCritera.add(
				Restrictions.and(Restrictions.eq("careGroup.id", groupId), Restrictions.eq("account.id", accountId)));
		
		if(accountGropuCritera.uniqueResult() != null){
			
			return; 
		}
		
		Account account = getById(accountId, Account.class);
		AccountGroup accountGroup = new AccountGroup();
		Criteria groupCriteria = sessionFactory.getCurrentSession().createCriteria(CareGroup.class);
		groupCriteria.add(Restrictions.eq("id", groupId));
		CareGroup careGroup = (CareGroup) groupCriteria.uniqueResult();
		
		account.addAccountGroup(accountGroup);
		accountGroup.setAccount(account);
		accountGroup.setCareGroup(careGroup);
		careGroup.addAccountGroup(accountGroup);
		
	}

}
