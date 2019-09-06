package com.edugroupe.springsecurityjpaform.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.edugroupe.springsecurityjpaform.security.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder getPasswordEncoder() { 
		//return NoOpPasswordEncoder.getInstance();
		return new  BCryptPasswordEncoder();
		
	}

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/user").hasAnyRole("USER", "ADMIN") // authenticated()
			.and().authorizeRequests().antMatchers("/admin").hasAnyRole("ADMIN")
			.and().authorizeRequests().antMatchers("/**").permitAll()
			.and().formLogin().and().logout();
	}

}
