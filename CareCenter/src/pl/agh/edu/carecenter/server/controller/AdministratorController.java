package pl.agh.edu.carecenter.server.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.agh.edu.carecenter.server.domain.AccountRole;
import pl.agh.edu.carecenter.server.domain.Doctor;
import pl.agh.edu.carecenter.server.exceptions.AccountAlreadyExists;
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
		return "addDoctor"; 
	}
	
	@RequestMapping(value = "/administration/addDoctor", method=RequestMethod.POST)
	public ModelAndView addDoctor(@Valid Doctor doctor, BindingResult result){
		
		ModelAndView mav = new ModelAndView("addDoctor");
		if(!doctor.getPassword().equals(doctor.getRepeatedPassword()))
			result.addError(new FieldError("doctor","repeatedPassword","passwords are diffrent"));
		
		if(result.hasErrors()){
			mav.addObject("doctor", doctor);
			mav.addObject("doctorList", accountService.listDoctors());
			mav.addObject("degreeList",doctorService.getDegreeList());
			return mav;
		}
		else{

			try{
				doctor.addAccountRole(new AccountRole("ROLE_DOCTOR"));
				accountService.saveAccount(doctor);
			}
			catch(AccountAlreadyExists exception){
				result.addError(new FieldError("doctor","email","email already registered"));
				mav.addObject("doctor", doctor);
				mav.addObject("doctorList", accountService.listDoctors());
				mav.addObject("degreeList",doctorService.getDegreeList());
				return mav;
			}

			
		}
					
		mav.setViewName("redirect:/administration/addDoctor.html");
		return mav; 
		 
	}
	
	@InitBinder("doctor")
	protected void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Date.class, null, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
		
		//binder.setValidator(new validator);
	}

}
