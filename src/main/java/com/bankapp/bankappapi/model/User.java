package com.bankapp.bankappapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity(name = "banking")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_number")
	private int accountNumber;

	@Column(name = "name")
	private String name;

	@Column(name = "age")
	private int age;

	@Column(name = "mobile_number")
	private String mobileNumber;

	@Column(name = "gender")
	private String gender;

	@Column(name = "amount")
	private int amount;

	@Column(name = "password")
	private String password;

	@Column(name = "role")
	private String role;

	@Column(name = "status")
	private String status;
}