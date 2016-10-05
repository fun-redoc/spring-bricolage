package com.rsh.project.controller;

import com.rsh.project.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	private PersonRepository personRepository;
	
	@Autowired
	public HomeController(PersonRepository personRepository){
		this.personRepository = personRepository;
	}
	
	@RequestMapping("/")
	public String home(Model model){
//		model.addAttribute("person", .. currently loged in person ..);
		return "index";
	}
	
}
