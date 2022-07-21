package org.naeemiqbal;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private DataSource dataSource;

	/* Create 2 users for demo
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
/*		auth.inMemoryAuthentication().withUser("user").password("{noop}nmi").roles("USER").and()
		.withUser("admin").password("{noop}nmi").roles("USER", "ADMIN").and()
		.withUser("nmi").password("{noop}nmi").roles("USER");* /
		auth.jdbcAuthentication().dataSource(dataSource);
		
	}
	*/
	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) 
	  throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder);
/*	    auth.jdbcAuthentication()
	      .dataSource(dataSource)
	      .usersByUsernameQuery("select username,password,enabled "
	        + "from users "
	        + "where username = ?")
	      .authoritiesByUsernameQuery("select username,authority "
	        + "from authorities "
	        + "where username = ?");*/
	}

	// Secure the endpoins with HTTP Basic authentication
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				// HTTP Basic authentication
				.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.GET, "/books/**").hasRole("USER")
				.antMatchers(HttpMethod.POST, "/books").hasRole("ADMIN").antMatchers(HttpMethod.PUT, "/books/**")
				.hasRole("ADMIN").antMatchers(HttpMethod.PATCH, "/books/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/books/**").hasRole("ADMIN")
				.antMatchers("/filter/**").hasAnyRole("ADMIN","USER")
				.antMatchers("/", "/public/**", "/h2-console/**").permitAll()
				
				.and().csrf().disable().formLogin().disable();

		http.csrf().ignoringAntMatchers("/h2-console/**");
		http.headers().frameOptions().sameOrigin();
	}
	

	@Autowired
	BCryptPasswordEncoder passwordEncoder;


	@Bean
	public JdbcUserDetailsManager jdbcUserDetailsManager() {
		return new JdbcUserDetailsManager(dataSource);
	}
}
