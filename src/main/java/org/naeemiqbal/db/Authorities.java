package org.naeemiqbal.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Authorities {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long Id;

	private String authority;
	private String userName;

	public Authorities() {
	
	}

	public Authorities( String userName,String role) {
		super();
		this.authority = role;
		this.userName = userName;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getRole() {
		return authority;
	}

	public void setRole(String role) {
		this.authority = role;
	}

	public String getUsername() {
		return userName;
	}

	public void setUsername(String username) {
		this.userName = username;
	}

}
