package com.rsh.project.repository;

import java.util.List;

import com.rsh.project.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends CrudRepository<User, Long> {

	List<User> findAllByOrderByLastNameAscFirstNameAsc();

}
