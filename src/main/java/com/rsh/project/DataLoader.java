package com.rsh.project;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;

import com.rsh.project.domain.*;
import com.rsh.project.repository.ProjectRepository;
import com.rsh.project.repository.ProjectPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rsh.project.repository.PersonRepository;

@Component
@SuppressWarnings("unused")
public class DataLoader {

	private PersonRepository personRepository;
	private ProjectRepository projectRepository;
	private ProjectPersonRepository projectPersonRepository;
	
	@Autowired
	public DataLoader( PersonRepository personRepository,
					  ProjectRepository projectRepository,
					  ProjectPersonRepository projectPersonRepository){
		this.personRepository = personRepository;
		this.projectRepository = projectRepository;
		this.projectPersonRepository = projectPersonRepository;
	}

	// TODO only in local profiles
	@PostConstruct
	private void loadData(){
		
        projectPersonRepository.deleteAll();
		personRepository.deleteAll();
		projectRepository.deleteAll();

		// create an person
		Person person1 = new Person("Roland","Stellmach","rlndstllmch@gmail.com");
		Person person2 = new Person("Roland1","Stellmach","fun.redoc@gmail.com");
        Person person3 = new Person("Dnalor","Stellmach","fun.redoc@gmail.com");
		personRepository.save(person1);
		personRepository.save(person2);

		//create some projects and assign to person
        Calendar day1 = Calendar.getInstance();
        day1.set(2016,6,1);
        Calendar day2 = Calendar.getInstance();
        day2.set(2017,6,1);
        Calendar day3 = Calendar.getInstance();
        day3.set(2017,6,2);
        Calendar day4 = Calendar.getInstance();
        day4.set(2017,11,30);

        List<Period> periods = new ArrayList<>();
        periods.add(new Period(day1, day2, 50L));
        periods.add(new Period(day3, day4, 50L));

        Project project1 = new Project();
        project1.setName("project1");
        project1.setStartDate(day1);
        project1.setEndDate(day4);
        project1.setDescription("project 1 description");
        //List<ProjectPerson> projectAssignments1 = new ArrayList<>();
        //projectAssignments1.add(projectPerson1);
        //project1.setProjectPersonList(projectAssignments1);
        projectRepository.save(project1);

        Project project2 = new Project();
        project2.setName("project2");
        project2.setStartDate(day1);
        project2.setEndDate(day4);
        project2.setDescription("project 2 description");
        //List<ProjectPerson> projectAssignments2 = new ArrayList<>();
        //projectAssignments2.add(projectPerson2);
        //project1.setProjectPersonList(projectAssignments2);
        projectRepository.save(project2);

		ProjectPerson projectPerson1 = new ProjectPerson();
		projectPerson1.setProject(project2);
        projectPerson1.setPerson(person2);
        projectPerson1.setPeriods(periods);
        projectPersonRepository.save(projectPerson1);

        ProjectPerson projectPerson2 = new ProjectPerson();
        projectPerson2.setProject(project1);
        projectPerson2.setPerson(person1);
        projectPerson2.setPeriods(periods);
        projectPersonRepository.save(projectPerson2);

        ProjectPerson projectPerson3 = new ProjectPerson();
        projectPerson3.setProject(project1);
        projectPerson3.setPerson(person2);
        projectPerson3.setPeriods(periods);
        projectPersonRepository.save(projectPerson3);
	}

}