package pl.agh.edu.carecenter.server.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.agh.edu.carecenter.server.service.DoctorService;


@Controller
public class HelloController {
	
	
	@Autowired
	private DoctorService doctorService;
	
	@RequestMapping("/hello")
	public String greetUserAndShowMenu(Map<String,Object> map){
		
		map.put("alarmList", doctorService.listAlarms());
		return "hello";
	}

}
