package pl.agh.edu.carecenter.server.dao;

import java.util.List;

import pl.agh.edu.carecenter.server.domain.Activity;
import pl.agh.edu.carecenter.server.domain.ActivityCategory;
import pl.agh.edu.carecenter.server.exceptions.CategoryDoesNotExist;

public interface ActivityDAO extends GenericDAO<Activity> {
	
	void saveCategory(ActivityCategory category);
	List<ActivityCategory> listCategories(boolean populateActivities);
	void saveActivity(Activity activity) throws CategoryDoesNotExist;

}
