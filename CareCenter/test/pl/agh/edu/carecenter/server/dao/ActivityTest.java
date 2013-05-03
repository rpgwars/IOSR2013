package pl.agh.edu.carecenter.server.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import pl.agh.edu.carecenter.server.domain.Activity;
import pl.agh.edu.carecenter.server.domain.ActivityCategory;
import pl.agh.edu.carecenter.server.exceptions.CategoryDoesNotExist;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ActivityTest {
	
	@Autowired
	private ActivityDAO activityDao;
	
	@Test
	public void testCategorySave(){
		
		activityDao.saveCategory(new ActivityCategory());
		assertEquals(activityDao.listCategories(false).size(),4);
		
		
	}
	
	@Test(expected=CategoryDoesNotExist.class)
	public void testActivitySave() throws CategoryDoesNotExist{
		
		Activity activity = new Activity(); 
		activity.setCategoryId(10002);
		activityDao.saveActivity(activity);
		
	}

}
