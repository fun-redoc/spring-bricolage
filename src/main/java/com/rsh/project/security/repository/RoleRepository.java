package com.rsh.project.security.repository;

import com.rsh.project.security.domain.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by fun-redoc on 06.10.16.
 */
@SuppressWarnings("unused")
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
}
