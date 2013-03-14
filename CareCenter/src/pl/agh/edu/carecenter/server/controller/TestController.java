package pl.agh.edu.carecenter.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.agh.edu.carecenter.server.service.TestService;

@Controller
public class TestController {

	@Autowired
	TestService test;
	
	@RequestMapping("/login")
	public String login() {
		test.test();
		return "login";
	}


	@RequestMapping(value = "/logout/success")
	public String logoutSuccess() {
		return "redirect:/login.html";
	}


}
