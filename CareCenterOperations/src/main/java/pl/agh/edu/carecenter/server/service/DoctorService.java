package pl.agh.edu.carecenter.server.service;

import java.util.List;

import pl.agh.edu.carecenter.server.domain.ActivityCategory;

public interface DoctorService {
	
	List<String> getDegreeList();
	void saveCategory(ActivityCategory category);
	List<ActivityCategory> listCategories(); 

}
