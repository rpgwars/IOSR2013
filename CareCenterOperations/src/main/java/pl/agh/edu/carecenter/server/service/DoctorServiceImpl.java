package pl.agh.edu.carecenter.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.agh.edu.carecenter.server.dao.ActivityDAO;
import pl.agh.edu.carecenter.server.dao.DoctorDAO;
import pl.agh.edu.carecenter.server.domain.ActivityCategory;

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
	
	

}
