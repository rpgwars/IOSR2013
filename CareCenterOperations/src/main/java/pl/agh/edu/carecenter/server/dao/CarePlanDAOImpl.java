package pl.agh.edu.carecenter.server.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.agh.edu.carecenter.server.domain.Activity;
import pl.agh.edu.carecenter.server.domain.ActivityCarePlan;
import pl.agh.edu.carecenter.server.domain.CarePlan;

@Repository
public class CarePlanDAOImpl extends GenericDAOImpl<CarePlan> implements CarePlanDAO{

	@Autowired
	private SessionFactory sessionFactory; 

	@Override
	@Transactional
	public void saveCarePlan(CarePlan carePlan) {
		
		
		
		for(ActivityCarePlan activityCarePlan : carePlan.getActivityCarePlanList()){
			System.out.println("x");
			Integer activityId = activityCarePlan.getActivityId();
			
			Criteria activityCriteria = 
					sessionFactory.getCurrentSession().createCriteria(Activity.class);
			activityCriteria.add(Restrictions.eq("id", activityId));
			
			Activity activity = (Activity)activityCriteria.uniqueResult();
			activityCarePlan.setActivity(activity);
			activity.addActivityCarePlan(activityCarePlan);
		}
		
		sessionFactory.getCurrentSession().save(carePlan);
		
		
	}

	
}
