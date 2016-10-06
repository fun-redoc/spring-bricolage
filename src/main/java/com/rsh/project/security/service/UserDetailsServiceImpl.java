package com.rsh.project.security.service;

import com.rsh.project.security.controller.UserDetailsImpl;
import com.rsh.project.security.domain.User;
import com.rsh.project.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by fun-redoc on 06.10.16.
 */

    @Service
    @SuppressWarnings("unused")
    public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByName(s);
        if(user==null) throw new UsernameNotFoundException(s);
        return new UserDetailsImpl(user);
    }
}
