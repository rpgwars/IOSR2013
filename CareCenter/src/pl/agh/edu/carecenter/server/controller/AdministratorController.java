package pl.agh.edu.carecenter.server.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.agh.edu.carecenter.server.domain.AccountRole;
import pl.agh.edu.carecenter.server.domain.CareGroup;
import pl.agh.edu.carecenter.server.domain.Doctor;
import pl.agh.edu.carecenter.server.exceptions.AccountAlreadyExists;
import pl.agh.edu.carecenter.server.exceptions.AccountNotFound;
import pl.agh.edu.carecenter.server.exceptions.GroupDoesNotExist;
import pl.agh.edu.carecenter.server.service.AccountService;
import pl.agh.edu.carecenter.server.service.DoctorService;

 
@Controller
public class AdministratorController {
	
	
	
	@Autowired
	private AccountService accountService; 
	
	@Autowired
	private DoctorService doctorService;
	
	@RequestMapping("/administration/addDoctor")
	public String listDoctors(Map<String,Object> map){
		
		List<Doctor> doctorList = accountService.listDoctors(); 
		map.put("doctorList", doctorList);
		map.put("doctor", new Doctor());
		map.put("degreeList", doctorService.getDegreeList());
		map.put("groupList", accountService.listGroups());
		return "addDoctor"; 
	}
	
	@RequestMapping(value = "/administration/addDoctor", method=RequestMethod.POST)
	public ModelAndView addDoctor(@Valid Doctor doctor, BindingResult result){
		
		ModelAndView mav = new ModelAndView("addDoctor");
		if(!doctor.getPassword().equals(doctor.getRepeatedPassword()))
			result.addError(new FieldError("doctor","repeatedPassword","passwords are diffrent"));
		
		if(result.hasErrors()){
			return prepareMaV(doctor);
		}
		else{

			try{
				doctor.addAccountRole(new AccountRole("ROLE_DOCTOR"));
				accountService.saveAccount(doctor);
			}
			catch(AccountAlreadyExists exception){
				result.addError(new FieldError("doctor","email","email already registered"));
				return prepareMaV(doctor);
			} catch (GroupDoesNotExist e) {
				result.addError(new FieldError("doctor","groupId","this group does not exist"));
				return prepareMaV(doctor);
			}

			
		}
					
		mav.setViewName("redirect:/administration/addDoctor.html");
		return mav; 
		 
	}
	
	private ModelAndView prepareMaV(Doctor doctor){
		ModelAndView mav = new ModelAndView("addDoctor");
		mav.addObject("doctor", doctor);
		mav.addObject("doctorList", accountService.listDoctors());
		mav.addObject("degreeList", doctorService.getDegreeList());
		mav.addObject("groupList", accountService.listGroups());
		return mav;
	}
	
	@RequestMapping("/administration/addGroup")
	public String listGroups(Map<String,Object> map){
		List<CareGroup> groupList = accountService.listGroups();
		map.put("groupList", groupList);
		map.put("group", new CareGroup());
		return "addGroup";
	}
	
	@RequestMapping(value = "/administration/addGroup", method=RequestMethod.POST)
	public ModelAndView addGroup(@Valid CareGroup group, BindingResult result){
		
		ModelAndView mav = new ModelAndView(); 
		if(group.getGroupName().length() > 0){
			accountService.saveGroup(group);
			mav.setViewName("redirect:/administration/addGroup.html");
			
		}
		else{
			mav.addObject("group", group);
			mav.addObject("groupList",accountService.listGroups());
			result.addError(new FieldError("group","groupName","name of the group is too short"));
			mav.setViewName("addGroup");
		}
		
		return mav;
	}
	
	@RequestMapping("/administration/{doctorId}/assignGroup")
	public String showPossibleGroups(Map<String,Object> map, @PathVariable Integer doctorId) throws AccountNotFound{
		
		List<Integer> possibleGroups = 
				accountService.getPossibleAssignGroups(accountService.getAccountById(doctorId).getEmail(),null);
		map.put("possibleGroups", possibleGroups);
		return "assignGroup";
	}
	
	@RequestMapping("/administration/{doctorId}/assignGroup/{groupId}")
	public String assignGroupToDoctor(@PathVariable Integer doctorId, @PathVariable Integer groupId) throws AccountNotFound{
		
		accountService.assignGroup(groupId, null, doctorId);
		return "redirect:/administration/addDoctor.html";
	}
	
	
	
	@InitBinder("doctor")
	protected void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Date.class, null, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
		
	}

}
