package com.rsh.project.controller.admin;

import javax.validation.Valid;

import com.rsh.project.domain.Person;
import com.rsh.project.domain.Person;
import com.rsh.project.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import com.github.rjeschke.txtmark.Processor;

@Controller
@Secured( {"ROLE_ADMIN"} )
@SuppressWarnings("unused")
public class AdminController {
	
	private PersonRepository personRepository;
	
	@Autowired
	public AdminController(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	@RequestMapping("/admin/persons")
	public String list(Model model) {
		model.addAttribute("persons", personRepository.findAll());
		return "admin/person/list";
	}
	
	@RequestMapping("/admin/person/{id}")
	public String view(@PathVariable Long id, Model model) {
		model.addAttribute("person", personRepository.findOne(id));
		return "admin/person/view";
	}

	// create | save
	
	@RequestMapping("/admin/person/create")
	public String create(Model model) {
		model.addAttribute("person", new Person());
		return "admin/person/form";
	}
	
	@RequestMapping( value = "/admin/person/save", method = RequestMethod.POST )
	public String save(@Valid Person person, BindingResult bindingResult, Model model) {
				
		if( bindingResult.hasErrors() ){
			return "admin/person/form";
		} else {
			Person savedPerson = personRepository.save(person);
			return "redirect:/admin/person/" + savedPerson.getId();
		}

	}
	
	@RequestMapping("/admin/person/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("person", personRepository.findOne(id));
		return "admin/person/form";
	}


	// Releasenotes
	@RequestMapping("/admin/releasenotes")
	public String releasenotes(Model model) {
		// read releasenotes from File
		InputStream in = this.getClass().getClassLoader()
				.getResourceAsStream("backlog.md");
        String markdown = new BufferedReader(new InputStreamReader(in)).lines().collect(Collectors.joining("\n"));
		String html = Processor.process(markdown);
        model.addAttribute("releasenotes", html);
        return "admin/releasenotes";
	}

}
