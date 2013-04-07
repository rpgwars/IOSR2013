package pl.agh.edu.carecenter.server.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.agh.edu.carecenter.server.domain.Activity;
import pl.agh.edu.carecenter.server.domain.ActivityCategory;

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
	public List<ActivityCategory> listCategories() {
		return (List<ActivityCategory>) super.list(ActivityCategory.class);
	}

}
