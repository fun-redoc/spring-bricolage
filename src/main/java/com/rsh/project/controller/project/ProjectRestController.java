package com.rsh.project.controller.project;

import com.rsh.project.domain.Project;
import com.rsh.project.repository.ProjectRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by fun-redoc on 01.10.16.
 */
@RestController
@RequestMapping("/projects")
@SuppressWarnings("unused")
@Secured( {"ROLE_PROJECT"} )
public class  ProjectRestController {

	private ProjectRestRepository projectRepository;

	@Autowired
	public ProjectRestController(ProjectRestRepository projectRepository) {
		super();
		this.projectRepository = projectRepository;
	}

    @RequestMapping(value="/{id}", method= RequestMethod.GET, produces={"application/json"})
    @ResponseBody
    public Project findById(@PathVariable(value = "id") Long id) {
        return projectRepository.findById(id);
    }

	@RequestMapping(path="/findAll", method= RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public List<Project> findAll() {
		return projectRepository.findAll();
	}

	@RequestMapping(path="/list", method= RequestMethod.GET, produces={"application/json"})
	public HttpEntity<List<Project>> list() {
		List<Project> projects = projectRepository.findAll();
		return new ResponseEntity<>(projects, HttpStatus.OK);
	}

}
