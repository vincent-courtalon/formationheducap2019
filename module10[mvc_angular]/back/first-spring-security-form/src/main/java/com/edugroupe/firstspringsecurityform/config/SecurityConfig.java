package com.edugroupe.firstspringsecurityform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity		// active le filtrage par url
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("123456")
													.roles("USER")
			.and()
									 .withUser("admin").password("P@ssw0rd")
									 					.roles("ADMIN");
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		// --- pour ILLUSTRATION, ne pas utiliser en vrai -- //
		return NoOpPasswordEncoder.getInstance();
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/user").hasAnyRole("USER", "ADMIN")  //authenticated()
			.and()
			.authorizeRequests()
				.antMatchers("/admin").hasAnyRole("ADMIN")
			.and()
				.authorizeRequests()
			.antMatchers("/**").permitAll()
				
			.and()
				.formLogin()
			.and()
				.logout();
	}

	
	
}
