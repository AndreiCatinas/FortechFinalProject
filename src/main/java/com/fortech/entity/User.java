package com.fortech.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fortech.validation.PasswordMatch;

@Entity
@Table(name = "user")
@PasswordMatch(message = "{password.mismatch}")
@DynamicInsert
public class User {

	@Id
	@GeneratedValue
	private int id;

	@NotNull(message = "dfsgdg")
	@Size(min= 8, max = 20, message = "{username.size}")
	private String username;

	@Transient
	@Size (min = 8, max = 20, message = "{password.size}")
	private String plainPassword;
	
	@Transient
	private String repeatPassword;

	private String password;

	private String role;

	@Column(name = "create_date")
	private String createDate;

	public User() {

	}

	public User(String username, String password, String role) {
		setPassword(password);
		setUsername(username);
		setRole(role);
	}

	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.password = new BCryptPasswordEncoder().encode(plainPassword);
		this.plainPassword = plainPassword;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
}
