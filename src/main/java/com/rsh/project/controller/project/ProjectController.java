package com.rsh.project.controller.project;

import com.rsh.project.domain.Period;
import com.rsh.project.domain.Project;
import com.rsh.project.domain.ProjectPerson;
import com.rsh.project.repository.ProjectRepository;
import com.rsh.project.repository.ProjectPersonRepository;
import com.rsh.project.repository.PersonRepository;
import lombok.val;
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
	ProjectPersonRepository projectPersonRepository;
    private PersonRepository personRepository;

	@Autowired
    @SuppressWarnings("unused")
	public ProjectController(ProjectRepository projectRepository,
							 ProjectPersonRepository projectPersonRepository,
							 PersonRepository personRepository) {
		super();
		this.projectRepository = projectRepository;
		this.projectPersonRepository = projectPersonRepository;
        this.personRepository = personRepository;
	}

	@RequestMapping("")
    @SuppressWarnings("unused")
	public String list(Model model) {
        model.addAttribute("projects", projectRepository.findAll());
        return "project/list";
	}
	
	@RequestMapping("/{id}")
    @SuppressWarnings("unused")
	public String view(@PathVariable(value="id") Long id, Model model){
		model.addAttribute("project", projectRepository.findById(id));
		return "project/view";
	}
	
	@RequestMapping("/byPerson/{id}")
    @SuppressWarnings("unused")
	public String byPerson(@PathVariable(value="id") Long id, Model model){
		List<Project> ps = projectRepository.findByPersonId(id);
		model.addAttribute("projects", ps);
		return "project/list";
	}

	// create | save

	@RequestMapping(value="", params="create")
    @SuppressWarnings("unused")
	public String create(Model model) {
		model.addAttribute("project", new Project());
		model.addAttribute("persons", personRepository.findAllByOrderByLastNameAscFirstNameAsc());
		return "project/form";
	}

	@RequestMapping( value = "", method = RequestMethod.POST )
    @SuppressWarnings("unused")
	public String save(@Valid Project project, BindingResult bindingResult, Model model) {

		if( bindingResult.hasErrors() ){
			model.addAttribute("persons", personRepository.findAllByOrderByLastNameAscFirstNameAsc());
			return "project/form";
		} else {
			Project savedProject = projectRepository.save(project);
			return "redirect:/projects/" + savedProject.getId();
		}

	}

	@RequestMapping(value="/{id}", params="edit")
    @SuppressWarnings("unused")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("project", projectRepository.findOne(id));
		model.addAttribute("persons", personRepository.findAllByOrderByLastNameAscFirstNameAsc());
		return "project/form";
	}

	// Roster
	@RequestMapping(value = "/{id}/roster", method = RequestMethod.GET)
	public String rosterList(@PathVariable Long id, Model model) {
        model.addAttribute("project", projectRepository.findOne(id));
		model.addAttribute("roster", projectPersonRepository.findByProjectId(id));
		return "project/roster/list";
	}

    @RequestMapping(value="/{projectId}/roster", params="create")
    @SuppressWarnings("unused")
    public String createAssignment(@PathVariable Long projectId, Model model) {
		val project = projectRepository.findOne(projectId);
        model.addAttribute("project", project);
        model.addAttribute("persons", personRepository.findAll());
		val projectPersonAssignment = ProjectPerson.builder().project(project) .build();
        model.addAttribute("roster", new ProjectPerson());
        return "project/roster/form";
    }

    @RequestMapping(value="/{projectId}/roster/{rosterId}/period", params="create")
    @SuppressWarnings("unused")
    public String createAssignmentPeriod(@PathVariable Long projectId, @PathVariable Long rosterId, Model model) {
        model.addAttribute("roster", projectPersonRepository.findOne(rosterId));
        model.addAttribute("period", new Period());
        return "project/roster/period/form";
    }

    @RequestMapping(value="{projectId}/roster/{rosterId}/period", method = RequestMethod.POST)
    @SuppressWarnings("unused")
    public String assignementPeriodSave(@PathVariable Long projectId, @PathVariable Long rosterId, @Valid Period period, BindingResult bindingResult, Model model) {
        if( bindingResult.hasErrors() ){
            model.addAttribute("roster", projectPersonRepository.findOne(rosterId));
            return "project/roster/period/form";
        } else {
            val roster = projectPersonRepository.findOne(rosterId);
            roster.getPeriods().add(period);
            ProjectPerson savedRoster = projectPersonRepository.save(roster);
            return "redirect:/projects/" + savedRoster.getProject().getId() + "/roster";
        }
    }
}
