package com.rsh.project.controller;

import com.rsh.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/users")
@SuppressWarnings("unused")
public class UserController {

	private UserRepository userRepository;

	@Autowired
	public UserController(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	@RequestMapping("/list")
	public String list(Model model){
		model.addAttribute("users", userRepository.findAll());
		return "user/list";
	}
	
	@RequestMapping("/view")
	public String view(Model model){
		return "user/view";
	}
}
