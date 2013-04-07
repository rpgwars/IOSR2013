package pl.agh.edu.carecenter.server.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.agh.edu.carecenter.server.domain.ActivityCategory;
import pl.agh.edu.carecenter.server.service.DoctorService;


@Controller
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	
	@RequestMapping("/doctor/addCategory")
	public String listGroups(Map<String,Object> map){
		List<ActivityCategory> categoryList = doctorService.listCategories();
		map.put("categoryList", categoryList);
		map.put("category", new ActivityCategory());
		return "addCategory";
	}
	
	@RequestMapping(value = "/doctor/addCategory", method=RequestMethod.POST)
	public ModelAndView addGroup(@Valid ActivityCategory category, BindingResult result){
		
		ModelAndView mav = new ModelAndView(); 
		if(category.getCategoryName().length() > 0){
			doctorService.saveCategory(category);
			mav.setViewName("redirect:/doctor/addCategory.html");
			
		}
		else{
			mav.addObject("category", category);
			mav.addObject("categoryList",doctorService.listCategories());
			result.addError(new FieldError("category","categoryName","name of the category is too short"));
			mav.setViewName("addCategory");
		}
		
		return mav;
	}
	
	

}
