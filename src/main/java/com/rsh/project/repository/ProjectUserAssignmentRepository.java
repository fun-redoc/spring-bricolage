package com.rsh.project.repository;

import com.rsh.project.domain.ProjectUserAssignment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fun-redoc on 01.10.16.
 */
@RepositoryRestResource(collectionResourceRel = "projectUserAssignment", path = "projectUserAssignment")
@Repository
public interface ProjectUserAssignmentRepository extends CrudRepository<ProjectUserAssignment, Long> {
    ProjectUserAssignment findById(Long id);
    List<ProjectUserAssignment> findByUserId(Long id);
    List<ProjectUserAssignment> findByProjectId(Long id);
}
