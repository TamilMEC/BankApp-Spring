package com.bankapp.bankappapi.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.bankappapi.Dto.Message;
import com.bankapp.bankappapi.model.Transaction;
import com.bankapp.bankappapi.model.User;
import com.bankapp.bankappapi.service.UserService;


@RestController
public class UserController {
	//private static final Logger LOGGER=LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserService userService;

//	@GetMapping("user/register/{name}/{age}/{mobileNumber}/{gender}/{amount}/{password}")
//	public User registerdetails(@PathVariable("name") String name, @PathVariable("age") int age,
//			@PathVariable("mobileNumber") String mobileNumber, @PathVariable("gender") String gender,
//			@PathVariable("amount") int amount, @PathVariable("password") String password) {
//		User userDetails = userService.register(name, age, mobileNumber, gender, amount, password);
//		return userDetails;
//	}
	@PostMapping("user/register")
	public User registerdetails(@RequestBody User user) {
		User userDetails = userService.register(user.getName(),user.getAge(),user.getMobileNumber(),user.getGender(),user.getAmount(),user.getPassword());
		return userDetails;
	}

	@PostMapping("user/login")
	public ResponseEntity login(@RequestBody User user) {
		String message1;
		
		try{
			
			message1 = userService.login(user.getMobileNumber(), user.getPassword());
			System.out.println(message1);
			System.out.println();
			Message message = new Message("Success");
			return new ResponseEntity<>(message, HttpStatus.OK);
		}catch(Exception e) {
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
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
