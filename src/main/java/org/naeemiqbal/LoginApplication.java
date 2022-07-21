package org.naeemiqbal;

import javax.sql.DataSource;

import org.naeemiqbal.db.Users;
import org.naeemiqbal.db.Authorities;
import org.naeemiqbal.db.RoleRepository;
import org.naeemiqbal.db.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@SpringBootApplication
@ServletComponentScan
@ComponentScan
public class LoginApplication implements CommandLineRunner {

	@Autowired
	private UserRepository repository;

	@Autowired
	private RoleRepository rrepository;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		BCryptPasswordEncoder pe = passwordEncoder();
//		repository.save(new Users("naeemiqbal", pe.encode("nmi")));
		repository.save(new Users("admin", pe.encode("nmi")));
		repository.save(new Users("nmi", pe.encode("nmi")));
		rrepository.save(new Authorities("nmi", "ADMIN"));
		rrepository.save(new Authorities("nmi", "USER"));
		rrepository.save(new Authorities("nmi", "ROLE_ADMIN"));
		rrepository.save(new Authorities("nmi", "ROLE_USER"));
		rrepository.save(new Authorities("admin", "ADMIN"));
		rrepository.save(new Authorities("admin", "USER"));
		/*
		 * rrepository.save(new Authorities("naeemiqbal", "ADMIN"));
		 * rrepository.save(new Authorities("naeemiqbal", "USER"));
		 */
	}

	public UserRepository getRepository() {
		return repository;
	}
	/*
	 * @Bean public BCryptPasswordEncoder passwordEncoder() { return new
	 * BCryptPasswordEncoder(); } /*
	 * 
	 * @Bean public JdbcUserDetailsManager jdbcUserDetailsManager() { return new
	 * JdbcUserDetailsManager(dataSource); }
	 */
}
