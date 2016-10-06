package com.rsh.project.security.repository;

import com.rsh.project.security.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by fun-redoc on 06.10.16.
 */
@SuppressWarnings("unused")
public interface UserRepository extends CrudRepository<User, Long> {
    User findByName(String name);
}
