package pl.agh.edu.carecenter.server.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.InitBinder;

import pl.agh.edu.carecenter.server.domain.AccountRole;
import pl.agh.edu.carecenter.server.domain.Activity;
import pl.agh.edu.carecenter.server.domain.ActivityCarePlan;
import pl.agh.edu.carecenter.server.domain.ActivityCategory;
import pl.agh.edu.carecenter.server.domain.CarePlan;
import pl.agh.edu.carecenter.server.domain.Doctor;
import pl.agh.edu.carecenter.server.domain.Patient;
import pl.agh.edu.carecenter.server.exceptions.AccountAlreadyExists;
import pl.agh.edu.carecenter.server.exceptions.AccountNotFound;
import pl.agh.edu.carecenter.server.exceptions.CategoryDoesNotExist;
import pl.agh.edu.carecenter.server.exceptions.GroupDoesNotExist;
import pl.agh.edu.carecenter.server.service.AccountService;
import pl.agh.edu.carecenter.server.service.DoctorService;
import pl.agh.edu.carecenter.server.validator.ActivityCategoryValidator;
import pl.agh.edu.carecenter.server.validator.ActivityValidator;
import pl.agh.edu.carecenter.server.validator.CarePlanValidator;


@Controller
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired 
	private AccountService accountService; 
	
	@RequestMapping("/doctor/addPatient")
	public String listPatients(Map<String,Object> map) throws AccountNotFound{
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		
		List<Patient> patientList = accountService.listPatients(userName); 
		map.put("patientList", patientList);
		map.put("patient", new Patient());
		map.put("groupList", accountService.listGroups(userName));
		return "addPatient"; 
	}
	
	
	@RequestMapping(value = "/doctor/addPatient", method=RequestMethod.POST)
	public ModelAndView addDoctor(@Valid Patient patient, BindingResult result) throws AccountNotFound{
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		
		ModelAndView mav = new ModelAndView("addPatient");
		if(!patient.getPassword().equals(patient.getRepeatedPassword()))
			result.addError(new FieldError("patient","repeatedPassword","passwords are diffrent"));
		
		if(result.hasErrors()){
			return prepareMaV(patient,userName);
		}
		else{

			try{
				patient.addAccountRole(new AccountRole("ROLE_PATIENT"));
				accountService.saveAccount(patient,userName);
			}
			catch(AccountAlreadyExists exception){
				result.addError(new FieldError("patient","email","email already registered"));
				return prepareMaV(patient,userName);
			} catch (GroupDoesNotExist e) {
				result.addError(new FieldError("doctor","groupId","this group does not exist"));
				return prepareMaV(patient,userName);
			}

			
		}
					
		mav.setViewName("redirect:/doctor/addPatient.html");
		return mav; 
		 
	}
	
	private ModelAndView prepareMaV(Patient patient, String userName) throws AccountNotFound{
		ModelAndView mav = new ModelAndView("addPatient");
		mav.addObject("patient", patient);
		mav.addObject("patientList", accountService.listPatients(userName));
		mav.addObject("groupList", accountService.listGroups(userName));
		return mav;
	}
	
	
	
	
	@RequestMapping("/doctor/addCategory")
	public String listGroups(Map<String,Object> map){
		List<ActivityCategory> categoryList = doctorService.listCategories(false);
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
			mav.addObject("categoryList",doctorService.listCategories(false));
			mav.setViewName("addCategory");
		}
		
		return mav;
	}
	
	@RequestMapping("/doctor/addActivity")
	public String listActivities(Map<String,Object> map){
		
		List<ActivityCategory> categoryList = doctorService.listCategories(false);
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
				mav.addObject("categoryList",doctorService.listCategories(false));
				mav.addObject("activityList",doctorService.listActivities());
				mav.addObject("activity",activity);
				mav.setViewName("addActivity");
				
			}
			
			
		}
		else{
			mav.addObject("categoryList",doctorService.listCategories(false));
			mav.addObject("activityList",doctorService.listActivities());
			mav.addObject("activity",activity);
			mav.setViewName("addActivity");
		}
		
		return mav;
		
	}
	@RequestMapping("/doctor/addCarePlan")
	public String createCarePlan(Map<String,Object> map){
		
		List<ActivityCategory> categoryList = doctorService.listCategories(true);
		map.put("categoryList", categoryList);
		map.put("carePlan", new CarePlan());
		
		return "addCarePlan"; 
	}
	
	@RequestMapping(value="/doctor/addCarePlan", method=RequestMethod.POST)
	public ModelAndView addCarePlan(@Valid CarePlan carePlan, BindingResult bindingResult){
		
		ModelAndView mav = new ModelAndView(); 
		if(carePlan.getActivityCarePlanList().size() == 0 || bindingResult.hasErrors()){
			System.out.println("size size " + carePlan.getActivityCarePlanList().size());
			System.out.println("nazwa " +carePlan.getCarePlanName());
			mav.setViewName("addCarePlan");
			mav.addObject("categoryList",doctorService.listCategories(true));
			mav.addObject("carePlan",carePlan);
			return mav;
		}
		
		doctorService.saveCarePlan(carePlan);
		mav.setViewName("redirect:/doctor/addCarePlan.html");
		return mav; 	

		
	}
	
	@RequestMapping("/doctor/{patientId}/assignGroup")
	public String showPossibleGroups(Map<String,Object> map, @PathVariable Integer patientId) throws AccountNotFound{
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String doctorLogin = authentication.getName();
		
		List<Integer> possibleGroups = 
				accountService.getPossibleAssignGroups(doctorLogin, accountService.getAccountById(patientId).getEmail());
		map.put("possibleGroups", possibleGroups);
		return "assignGroup";
	}
	
	@RequestMapping("/doctor/{patientId}/assignGroup/{groupId}")
	public String assignGroupToDoctor(@PathVariable Integer patientId, @PathVariable Integer groupId) throws AccountNotFound{
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		accountService.assignGroup(groupId, patientId, accountService.getAccountByLogin(userName).getId());
		return "redirect:/doctor/addPatient.html";
	}
	
	@InitBinder("patient")
	protected void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Date.class, null, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));

	}
	
	@InitBinder("carePlan")
	protected void initCarePlanBinder(WebDataBinder binder){
		binder.setValidator(new CarePlanValidator());
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
