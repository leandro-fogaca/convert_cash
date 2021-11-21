package com.convertcash.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.convertcash.domain.User;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String email;
	private String password;
	
	public UserDTO() {
	}
	
	public UserDTO(User obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
		password = obj.getPassword();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@NotEmpty(message = "Favor inserir um nome válido.")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@NotEmpty(message = "Favor inserir um e-mail válido.")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@NotEmpty(message = "Favor inserir uma senha válida.")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
