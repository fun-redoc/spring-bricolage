package com.rsh.project.controller;

import com.rsh.project.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/persons")
@SuppressWarnings("unused")
public class PersonController {

	private PersonRepository personRepository;

	@Autowired
	public PersonController(PersonRepository personRepository) {
		super();
		this.personRepository = personRepository;
	}
	
	@RequestMapping("/list")
	public String list(Model model){
		model.addAttribute("persons", personRepository.findAll());
		return "person/list";
	}
	
	@RequestMapping("/view")
	public String view(Model model){
		return "person/view";
	}
}
