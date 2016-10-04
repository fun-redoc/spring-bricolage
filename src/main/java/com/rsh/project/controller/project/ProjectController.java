package com.rsh.project.controller.project;

import com.rsh.project.domain.Period;
import com.rsh.project.domain.Project;
import com.rsh.project.domain.ProjectUserAssignment;
import com.rsh.project.repository.ProjectRepository;
import com.rsh.project.repository.ProjectUserAssignmentRepository;
import com.rsh.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/projects")
@Secured( {"ROLE_PROJECT"} )
@SuppressWarnings("unused")
public class ProjectController {

	private ProjectRepository projectRepository;
	ProjectUserAssignmentRepository projectUserAssignmentRepository;
    private UserRepository userRepository;

	@Autowired
    @SuppressWarnings("unused")
	public ProjectController(ProjectRepository projectRepository,
							 ProjectUserAssignmentRepository projectUserAssignmentRepository,
							 UserRepository userRepository) {
		super();
		this.projectRepository = projectRepository;
		this.projectUserAssignmentRepository = projectUserAssignmentRepository;
        this.userRepository = userRepository;
	}

	@RequestMapping("/list")
    @SuppressWarnings("unused")
	public String list(Model model) {
        model.addAttribute("projects", projectRepository.findAll());
        return "project/list";
	}
	
	@RequestMapping("/view/{id}")
    @SuppressWarnings("unused")
	public String view(@PathVariable(value="id") Long id, Model model){
		model.addAttribute("project", projectRepository.findById(id));
		return "project/view";
	}
	
	@RequestMapping("/byUser/{id}")
    @SuppressWarnings("unused")
	public String byUser(@PathVariable(value="id") Long id, Model model){
		List<Project> ps = projectRepository.findByUserId(id);
		model.addAttribute("projects", ps);
		return "project/list";
	}

	// create | save

	@RequestMapping("/create")
    @SuppressWarnings("unused")
	public String create(Model model) {
		model.addAttribute("project", new Project());
		model.addAttribute("users", userRepository.findAllByOrderByLastNameAscFirstNameAsc());
		return "project/form";
	}

	@RequestMapping( value = "/save", method = RequestMethod.POST )
    @SuppressWarnings("unused")
	public String save(@Valid Project project, BindingResult bindingResult, Model model) {

		if( bindingResult.hasErrors() ){
			model.addAttribute("users", userRepository.findAllByOrderByLastNameAscFirstNameAsc());
			return "project/form";
		} else {
			Project savedProject = projectRepository.save(project);
			return "redirect:/projects/view/" + savedProject.getId();
		}

	}

	@RequestMapping("/edit/{id}")
    @SuppressWarnings("unused")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("project", projectRepository.findOne(id));
		model.addAttribute("users", userRepository.findAllByOrderByLastNameAscFirstNameAsc());
		return "project/form";
	}

	// Roseter
	@RequestMapping(value = "/{id}/roster", method = RequestMethod.GET)
	public String rosterList(@PathVariable Long id, Model model) {
        model.addAttribute("project", projectRepository.findOne(id));
		model.addAttribute("roster", projectUserAssignmentRepository.findByProjectId(id));
		return "project/roster/list";
	}

    @RequestMapping("/{projectId}/roster/add")
    @SuppressWarnings("unused")
    public String createAssignment(@PathVariable Long projectId, Model model) {
        model.addAttribute("project", projectRepository.findOne(projectId));
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("roster", new ProjectUserAssignment());
        return "project/roster/form";
    }

    @RequestMapping("/{projectId}/roster/{rosterId}/add")
    @SuppressWarnings("unused")
    public String createAssignmentPeriod(@PathVariable Long projectId, @PathVariable Long rosterId, Model model) {
        model.addAttribute("roster", projectUserAssignmentRepository.findOne(rosterId));
        model.addAttribute("period", new Period());
        return "project/roster/period/form";
    }
}
