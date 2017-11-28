package com.fortech.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fortech.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		//@formatter:off
		http
			.authorizeRequests()
				.antMatchers("/home", "/register").permitAll()
				.antMatchers("/employees/all", "/employees/inactive", "/employees/add", "employees/active",
							 "/products/booked", "/products/all", "/products", "/bookings/all", "/bookings/history", 
							 "/users/**")
							.hasRole("ADMIN")
				.antMatchers("/employees/active", "/employees/details/{id}", 
							"/products/available", "/bookings/{}/active", "/bookings/{}/history",
							"/bookings/delete/{}", "bookings/{}/products/{}")
							.hasAnyRole("USER", "ADMIN")
			.and()
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/home")
				.permitAll()
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
			.and()
			.exceptionHandling().accessDeniedPage("/forbidden");
		
		//@formatter:on
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}

}
