package pl.agh.edu.carecenter.server.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.agh.edu.carecenter.server.domain.Account;
import pl.agh.edu.carecenter.server.domain.AccountGroup;
import pl.agh.edu.carecenter.server.domain.Activity;
import pl.agh.edu.carecenter.server.domain.ActivityCategory;
import pl.agh.edu.carecenter.server.domain.CareGroup;
import pl.agh.edu.carecenter.server.exceptions.AccountAlreadyExists;
import pl.agh.edu.carecenter.server.exceptions.AccountNotFound;
import pl.agh.edu.carecenter.server.exceptions.CategoryDoesNotExist;
import pl.agh.edu.carecenter.server.exceptions.GroupDoesNotExist;

@Repository
public class ActivityDAOImpl extends GenericDAOImpl<Activity> implements ActivityDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public void saveCategory(ActivityCategory category) {
		sessionFactory.getCurrentSession().save(category);
	}

	@Override
	@Transactional(readOnly=true)
	public List<ActivityCategory> listCategories(boolean populateActivities) {
		List<ActivityCategory> categoryList = 
				(List<ActivityCategory>) super.list(ActivityCategory.class);
		if(populateActivities)
			for(ActivityCategory category : categoryList){
				Hibernate.initialize(category.getActivityList());
			}
		return categoryList;
	}

	@Override
	@Transactional
	public void saveActivity(Activity activity) throws CategoryDoesNotExist {
		
		Criteria categoryCriteria = sessionFactory.getCurrentSession().createCriteria(ActivityCategory.class);
		categoryCriteria.add(Restrictions.eq("id", activity.getCategoryId()));
		ActivityCategory category = (ActivityCategory) categoryCriteria.uniqueResult();
		if(category != null){
			activity.setActivityCategory(category);
			category.addActivity(activity);
		}
		else 
		{
			throw new CategoryDoesNotExist();
		}
		
		super.save(activity);
	}

}


//public void saveAccount(Account account) throws AccountAlreadyExists, GroupDoesNotExist {
//
//	try {
//		findAccount(account.getEmail());
//	} catch (AccountNotFound e) {
//		
//		if(account.getGroupId() != null){
//			Criteria groupCriteria = sessionFactory.getCurrentSession().createCriteria(CareGroup.class);
//			groupCriteria.add(Restrictions.eq("id", account.getGroupId()));
//			CareGroup group = (CareGroup) groupCriteria.uniqueResult();
//			if(group != null){
//				AccountGroup ag = new AccountGroup();
//				ag.setAccount(account);
//				ag.setGroup(group);
//				group.addAccountGroup(ag);
//				account.addAccountGroup(ag);
//			}
//			else{
//				throw new GroupDoesNotExist();
//			}
//				
//		}
//		super.save(account);
//		return; 
//	}
//	
//	throw new AccountAlreadyExists();
//	
//}