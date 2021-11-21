package com.convertcash.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection="user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	private String name;
	@Indexed(unique = true)
	private String email;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;	
	
	private Wallet wallet;
	
	public User() {
	}

	public User(String id, String name, String email, String password, Wallet wallet) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.wallet = wallet;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@NotBlank
	@Size(max =20)
	public String getName() {
		return name;
		
	}
	public void setName(String name) {
		this.name = name;
	}
	@Size(max=50)
	@Email(message = "E-mail inválido!")
	@NotEmpty(message = "Favor inserir um e-mail válido.")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Size(max =20)
	@NotBlank(message = "Favor inserir uma senha válida.")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return id == other.id;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	
}