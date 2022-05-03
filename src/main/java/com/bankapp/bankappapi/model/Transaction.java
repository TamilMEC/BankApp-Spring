package com.bankapp.bankappapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "mobilenumber")
	private String mobileNumber;

	@Column(name = "useraccountnumber")
	private int userAccountNumber;

	@Column(name = "amount")
	private int amount;

	@Column(name = "type")
	private String type;

	@Column(name = "accountnumber")
	private int accountNumber;

	@Column(name = "currentbalance")
	private int currentbalance;

	@Column(name = "dateandtime")
	private String datetime;
}
