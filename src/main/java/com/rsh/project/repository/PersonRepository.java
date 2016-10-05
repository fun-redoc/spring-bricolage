package com.rsh.project.repository;

import java.util.List;

import com.rsh.project.domain.Person;
import com.rsh.project.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "person", path = "person")
public interface PersonRepository extends CrudRepository<Person, Long> {

	List<Person> findAllByOrderByLastNameAscFirstNameAsc();

}
