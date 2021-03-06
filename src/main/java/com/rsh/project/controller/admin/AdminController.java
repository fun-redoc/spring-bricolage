package com.rsh.project.controller.admin;

import javax.validation.Valid;

import com.rsh.project.security.domain.Role;
import com.rsh.project.security.domain.User;
import com.rsh.project.security.repository.RoleRepository;
import com.rsh.project.security.repository.UserRepository;
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
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	
	@Autowired
	public AdminController(UserRepository userRepository,
							RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}
	
	@RequestMapping("/admin/users")
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "admin/user/list";
	}
	
	@RequestMapping("/admin/users/{id}")
	public String view(@PathVariable Long id, Model model) {
		model.addAttribute("user", userRepository.findOne(id));
		return "admin/user/view";
	}

	// create | save
	
	@RequestMapping(value = "/admin/users", params = "create")
	public String create(Model model) {
		model.addAttribute("user", new User());
		return "admin/user/form";
	}

	@RequestMapping(value = "/admin/users/{id}", params="delete")
	public String delete(@PathVariable Long id,  Model model) {
		userRepository.delete(id);
		return "redirect:/admin/users";
	}

	@RequestMapping( value = "/admin/users", method = RequestMethod.POST )
	public String save(@Valid User user, BindingResult bindingResult, Model model) {
				
		if( bindingResult.hasErrors() ){
			return "admin/user/form";
		} else {
            // TODO add Role, for this make a Role Factory for allowed Roles
			Role role = roleRepository.findByName("ROLE_PROJECT");
            user.getRoles().add(role);
			User savedUser = userRepository.save(user);
			return "redirect:/admin/users";
		}

	}
	
	@RequestMapping("/admin/user/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("user", userRepository.findOne(id));
		return "admin/user/form";
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
