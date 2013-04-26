package pl.agh.edu.carecenter.server.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.InitBinder;

import pl.agh.edu.carecenter.server.domain.Activity;
import pl.agh.edu.carecenter.server.domain.ActivityCategory;
import pl.agh.edu.carecenter.server.exceptions.CategoryDoesNotExist;
import pl.agh.edu.carecenter.server.service.DoctorService;
import pl.agh.edu.carecenter.server.validator.ActivityCategoryValidator;
import pl.agh.edu.carecenter.server.validator.ActivityValidator;


@Controller
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	
	@RequestMapping("/doctor/addCategory")
	public String listGroups(Map<String,Object> map){
		List<ActivityCategory> categoryList = doctorService.listCategories();
		map.put("categoryList", categoryList);
		map.put("activityCategory", new ActivityCategory());
		return "addCategory";
	}
	
	@RequestMapping(value = "/doctor/addCategory", method=RequestMethod.POST)
	public ModelAndView addGroup(@Valid ActivityCategory activityCategory, BindingResult result){
		
		ModelAndView mav = new ModelAndView(); 
		if(!result.hasErrors()){
			doctorService.saveCategory(activityCategory);
			mav.setViewName("redirect:/doctor/addCategory.html");
			
			
		}
		else{
			mav.addObject("activityCategory", activityCategory);
			mav.addObject("categoryList",doctorService.listCategories());
			mav.setViewName("addCategory");
		}
		
		return mav;
	}
	
	@RequestMapping("/doctor/addActivity")
	public String listActivities(Map<String,Object> map){
		
		List<ActivityCategory> categoryList = doctorService.listCategories();
		List<Activity> activityList = doctorService.listActivities();
		map.put("categoryList", categoryList);
		map.put("activityList", activityList);
		map.put("activity", new Activity());
		return "addActivity";
	}
	
	@RequestMapping(value = "/doctor/addActivity", method = RequestMethod.POST)
	public ModelAndView addActivity(@Valid Activity activity, BindingResult result){
		
		
		ModelAndView mav = new ModelAndView(); 
		if(!result.hasErrors()){
			try {
				
				doctorService.saveActivity(activity);
				mav.setViewName("redirect:/doctor/addActivity.html");
				
			} catch (CategoryDoesNotExist e) {
				result.addError(new FieldError("activity", "categoryId", "category does not exist"));
				mav.addObject("categoryList",doctorService.listCategories());
				mav.addObject("activityList",doctorService.listActivities());
				mav.addObject("activity",activity);
				mav.setViewName("addActivity");
				
			}
			
			
		}
		else{
			mav.addObject("categoryList",doctorService.listCategories());
			mav.addObject("activityList",doctorService.listActivities());
			mav.addObject("activity",activity);
			mav.setViewName("addActivity");
		}
		
		return mav;
		
	}
	
	
	@InitBinder("category")
	protected void initCategoryBinder(WebDataBinder binder){
		binder.setValidator(new ActivityCategoryValidator());
	}
	
	@InitBinder("activity")
	protected void initActivityBinder(WebDataBinder binder){
		binder.setValidator(new ActivityValidator());
	}

}
