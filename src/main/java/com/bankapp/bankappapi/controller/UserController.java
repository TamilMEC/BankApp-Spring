package com.bankapp.bankappapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.bankappapi.model.Transaction;
import com.bankapp.bankappapi.model.User;
import com.bankapp.bankappapi.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("user/register/{name}/{age}/{mobileNumber}/{gender}/{amount}/{password}")
	public User registerdetails(@PathVariable("name") String name, @PathVariable("age") int age,
			@PathVariable("mobileNumber") String mobileNumber, @PathVariable("gender") String gender,
			@PathVariable("amount") int amount, @PathVariable("password") String password) {
		User userDetails = userService.register(name, age, mobileNumber, gender, amount, password);
		return userDetails;
	}

	@PutMapping("user/login/{mobile_number}/{Password}")
	public String login(@PathVariable("mobile_number") String MobileNumber, @PathVariable("Password") String Password) {
		String message1;
		try {
			message1 = userService.login(MobileNumber, Password);
		} catch (Exception e) {
			message1 = e.getMessage();
		}
		return message1;
	}

	@GetMapping("user/accountdetails/{mobile_number}/{Password}")
	public User accountDetails(@PathVariable("mobile_number") String MobileNumber,
			@PathVariable("Password") String Password) {
		User obj = userService.accountDetails(MobileNumber, Password);
		return obj;
	}

	@GetMapping("user/withdraw/{amount}/{mobilenumber}")
	public String withdraw(@PathVariable("amount") int amount, @PathVariable("mobilenumber") String MobileNumber) {
		String message2 = userService.withdraw(amount, MobileNumber);
		return message2;
	}

	@GetMapping("user/deposit/{amount}/{mobilenumber}")
	public String deposit(@PathVariable("amount") int amount, @PathVariable("mobilenumber") String MobileNumber) {
		String message3 = userService.deposit(amount, MobileNumber);
		return message3;
	}

	@GetMapping("user/amounttransfer/{accountnumber}/{amount}/{mobilenumber}")
	public String amountTransfer(@PathVariable("accountnumber") int accountnumber, @PathVariable("amount") int amount,
			@PathVariable("mobilenumber") String MobileNumber) {
		String message4 = userService.amountTransfer(accountnumber, amount, MobileNumber);
		return message4;

	}

	@GetMapping("user/transaction/{mobileNumber}")
	public List<Transaction> transactionDetails(@PathVariable("mobileNumber") String mobileNumber) {

		List<Transaction> userObj2 = userService.transactionDetails(mobileNumber);
		return userObj2;
	}

}
