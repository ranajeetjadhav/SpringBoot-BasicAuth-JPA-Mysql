/**
 * 
 */
package com.example.easynotes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * @author e1077874
 *
 */

@Configuration
@EnableWebSecurity
@PropertySource("classpath:/config/config.properties")
public class SpringSecurityConfig  extends WebSecurityConfigurerAdapter {

	@Autowired
	Environment environment;
	
	@Autowired
	AuthenticationEntryPoint customAuthenticationEntryPoint;
	
	@Override
	protected void  configure(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeRequests()	
		.antMatchers("/api/**").authenticated()
		.and().httpBasic()
		.authenticationEntryPoint(customAuthenticationEntryPoint);
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser(environment.getProperty("security.user.name")).password(environment.getProperty("security.user.password")).roles("USER");
	}

}
