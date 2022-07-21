package org.naeemiqbal.db;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.h2.util.Bits;
import org.h2.value.ValueTinyint;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;

@Entity
public class Users {

/*
	@Autowired
	BCryptPasswordEncoder pe;// = passwordEncoder();
	*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String userName;
	private String password;
	private String email;
	private Date created;
	private int enabled=1;
	
	public Users() {
		
	}
	
	public Users(String u, String p) {
		this.userName=u;
		setPassword( p);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (StringUtils.hasText(password)) {
		//	if (password.startsWith("$2a$"))
				this.password=password;
			/*else
				this.password=pe.encode(password);
				*/
		}		
		else
			this.password = password; //null
	}	
	
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public int isEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		Gson json = new Gson();
		return json.toJson(this);
		//return "{ \"userame\": \""+ this.userName + "\", \"password\": \"Cannot reveal\", \"id\": " + this.id +" }";
		
	}
}
