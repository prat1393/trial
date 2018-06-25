package com.att.user.dao.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "E2E_User_Details")
public class User {

	public User() {
		super();

	}

	public User(String attId, String firstName, String lastName, String userAccess, String createDate) {
		super();
		this.attId = attId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userAccess = userAccess;
		this.createDate = createDate;
	}

	@Id
	private String attId;

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@NotNull
	private String userAccess;

	@NotNull
	private String createDate;

}
