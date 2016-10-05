package com.rsh.project.repository;

import com.rsh.project.domain.ProjectPerson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fun-redoc on 01.10.16.
 */
@RepositoryRestResource(collectionResourceRel = "projectPerson", path = "projectPerson")
@Repository
public interface ProjectPersonRepository extends CrudRepository<ProjectPerson, Long> {
    ProjectPerson findById(Long id);
    List<ProjectPerson> findByPersonId(Long id);
    List<ProjectPerson> findByProjectId(Long id);
}
