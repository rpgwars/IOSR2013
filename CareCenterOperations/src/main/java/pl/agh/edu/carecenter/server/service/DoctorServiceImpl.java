package pl.agh.edu.carecenter.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.agh.edu.carecenter.server.dao.ActivityDAO;
import pl.agh.edu.carecenter.server.dao.DoctorDAO;
import pl.agh.edu.carecenter.server.domain.Activity;
import pl.agh.edu.carecenter.server.domain.ActivityCategory;
import pl.agh.edu.carecenter.server.exceptions.CategoryDoesNotExist;

@Service
public class DoctorServiceImpl implements DoctorService{

	@Autowired
	private DoctorDAO doctorDao; 
	
	@Autowired
	private ActivityDAO activityDao; 
	
	@Override
	public List<String> getDegreeList() {
		return doctorDao.getDegrees();
	}

	@Override
	public void saveCategory(ActivityCategory category) {
		activityDao.saveCategory(category);
		
	}

	@Override
	public List<ActivityCategory> listCategories() {
		return activityDao.listCategories();
	}

	@Override
	public List<Activity> listActivities() {
		return (List<Activity>) activityDao.list(Activity.class);
	}

	
	@Override
	public void saveActivity(Activity activity) throws CategoryDoesNotExist {
		activityDao.save(activity); 
	}
	
	

}
