package pl.agh.edu.carecenter.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.agh.edu.carecenter.android.transferobject.AndroidCarePlan;
import pl.agh.edu.carecenter.server.service.PatientService;

@Controller
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	
	@RequestMapping(value = "/patient/getCarePlans", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public AndroidCarePlan getCarePlans() {
	    
		String userName = "p1";
		System.out.println("przeed");
		List<AndroidCarePlan> list = patientService.getPatientsCarePlans(userName);
		System.out.println("po");
		System.out.println(list.size() +  " xxx");
		return list.get(0);
		
		
	}

}
