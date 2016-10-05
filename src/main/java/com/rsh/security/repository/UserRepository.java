package com.rsh.security.repository;

import com.rsh.security.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by fun-redoc on 06.10.16.
 */
@Repository
@SuppressWarnings("unused")
public interface UserRepository extends CrudRepository<User, Long> {
    User findByName(String name);
}
