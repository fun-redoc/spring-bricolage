package com.rsh.project.controller;

import com.rsh.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	private UserRepository userRepository;
	
	@Autowired
	public HomeController(UserRepository userRepository){
		this.userRepository = userRepository;
	}
	
	@RequestMapping("/")
	public String home(Model model){
//		model.addAttribute("user", .. currently loged in user ..);
		return "index";
	}
	
}
