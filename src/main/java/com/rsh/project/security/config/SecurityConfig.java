package com.rsh.project.security.config;

import com.rsh.project.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@Profile({"localH2", "localMySql"}) // you will need a more secure profile for production! e.g. without console
@EnableGlobalMethodSecurity( securedEnabled = true )
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@SuppressWarnings("unused")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	public void configureAuthenticationManagerBuilder(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/projects/**").hasRole("PROJECT")
				.anyRequest().authenticated()
                .and()
			.formLogin()
				.loginPage("/login")
				.usernameParameter("username")
				.permitAll()
				.and()
			.logout()
				.logoutSuccessUrl("/login?logout")
				.permitAll();
	}
	
}