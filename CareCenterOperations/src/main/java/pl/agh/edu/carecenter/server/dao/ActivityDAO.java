package pl.agh.edu.carecenter.server.dao;

import java.util.List;

import pl.agh.edu.carecenter.server.domain.ActivityCategory;

public interface ActivityDAO {
	
	void saveCategory(ActivityCategory category);
	List<ActivityCategory> listCategories();

}
