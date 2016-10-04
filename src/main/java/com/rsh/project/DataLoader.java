package com.rsh.project;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;

import com.rsh.project.domain.*;
import com.rsh.project.repository.ProjectRepository;
import com.rsh.project.repository.ProjectUserAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rsh.project.repository.UserRepository;

@Component
@SuppressWarnings("unused")
public class DataLoader {

	private UserRepository userRepository;
	private ProjectRepository projectRepository;
	private ProjectUserAssignmentRepository projectUserAssignmentRepository;
	
	@Autowired
	public DataLoader( UserRepository userRepository,
					  ProjectRepository projectRepository,
					  ProjectUserAssignmentRepository projectUserAssignmentRepository){
		this.userRepository = userRepository;
		this.projectRepository = projectRepository;
		this.projectUserAssignmentRepository = projectUserAssignmentRepository;
	}

	// TODO only in local profiles
	@PostConstruct
	private void loadData(){
		
        projectUserAssignmentRepository.deleteAll();
		userRepository.deleteAll();
		projectRepository.deleteAll();

		// create an user
		User user1 = new User("Roland","Stellmach","rlndstllmch@gmail.com");
		User user2 = new User("Roland1","Stellmach","fun.redoc@gmail.com");
        User user3 = new User("Dnalor","Stellmach","fun.redoc@gmail.com");
		userRepository.save(user1);
		userRepository.save(user2);

		//create some projects and assign to user
        Calendar day1 = Calendar.getInstance();
        day1.set(2016,6,1);
        Calendar day2 = Calendar.getInstance();
        day2.set(2017,6,1);
        Calendar day3 = Calendar.getInstance();
        day3.set(2017,6,2);
        Calendar day4 = Calendar.getInstance();
        day4.set(2017,11,30);

        List<Period> periods = new ArrayList<>();
        periods.add(new Period(day1, day2, 50));
        periods.add(new Period(day3, day4, 50));

        Project project1 = new Project();
        project1.setName("project1");
        project1.setStartDate(day1);
        project1.setEndDate(day4);
        project1.setDescription("project 1 description");
        //List<ProjectUserAssignment> projectAssignments1 = new ArrayList<>();
        //projectAssignments1.add(projectUserAssignment1);
        //project1.setProjectUserAssignmentList(projectAssignments1);
        projectRepository.save(project1);

        Project project2 = new Project();
        project2.setName("project2");
        project2.setStartDate(day1);
        project2.setEndDate(day4);
        project2.setDescription("project 2 description");
        //List<ProjectUserAssignment> projectAssignments2 = new ArrayList<>();
        //projectAssignments2.add(projectUserAssignment2);
        //project1.setProjectUserAssignmentList(projectAssignments2);
        projectRepository.save(project2);

		ProjectUserAssignment projectUserAssignment1 = new ProjectUserAssignment();
		projectUserAssignment1.setProject(project2);
        projectUserAssignment1.setUser(user2);
        projectUserAssignment1.setPeriods(periods);
        projectUserAssignmentRepository.save(projectUserAssignment1);

        ProjectUserAssignment projectUserAssignment2 = new ProjectUserAssignment();
        projectUserAssignment2.setProject(project1);
        projectUserAssignment2.setUser(user1);
        projectUserAssignment2.setPeriods(periods);
        projectUserAssignmentRepository.save(projectUserAssignment2);

        ProjectUserAssignment projectUserAssignment3 = new ProjectUserAssignment();
        projectUserAssignment3.setProject(project1);
        projectUserAssignment3.setUser(user2);
        projectUserAssignment3.setPeriods(periods);
        projectUserAssignmentRepository.save(projectUserAssignment3);
	}

}