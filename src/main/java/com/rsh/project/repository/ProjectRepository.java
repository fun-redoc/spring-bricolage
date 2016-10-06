package com.rsh.project.repository;

import com.rsh.project.domain.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by fun-redoc on 01.10.16.
 */
@RepositoryRestResource(collectionResourceRel = "project", path = "project")
@Repository
public interface ProjectRepository extends CrudRepository<Project, Long>, QueryDslPredicateExecutor<Project> {
    List<Project> findAll();
    Project findById(Long id);
//    Project findByName(String name);
//    List<Project> findByUserIdOrderByName(Long id);
//    List<Project> findByUserIdOrderByStartDateAsc(Long id);

//    @Transactional(timeout = 10)
//    <S extends Project> S save(S entity);

    // select * from project as p, project_user_assignment as a, person as u where u.id = 1 and a.user_id = u.id and a.project_id = p.id
    @Query("SELECT p FROM Project p JOIN p.projectPersonList a JOIN a.person u WHERE u.id = :id ")
    List<Project> findByPersonId(@Param("id") Long id);
}
