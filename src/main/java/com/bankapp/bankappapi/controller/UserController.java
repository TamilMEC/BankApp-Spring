package com.bankapp.bankappapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.bankappapi.Dto.Message;
import com.bankapp.bankappapi.model.Transaction;
import com.bankapp.bankappapi.model.User;
import com.bankapp.bankappapi.service.UserService;

@Component
@RestController
public class UserController {
	// private static final Logger
	// LOGGER=LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserService userService;

	@PostMapping("user/register")
	public User registerdetails(@RequestBody User user) {
		User userDetails = userService.register(user.getName(), user.getAge(), user.getMobileNumber(), user.getGender(),
				user.getAmount(), user.getPassword());
		System.out.println(userDetails);
		return userDetails;
	}

	@PostMapping("user/login")
	public ResponseEntity<?> login(@RequestBody User user) {
		try {
			Message message = new Message(userService.login(user.getMobileNumber(), user.getPassword()));
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (Exception e) {
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("user/accountdetails")
	public User accountDetails(@RequestBody User user) {
		System.out.println(user.getMobileNumber());
		System.out.println(user.getPassword());
		User obj = userService.accountDetails(user.getMobileNumber(), user.getPassword());
		System.out.println(obj);
		return obj;
	}

	@PostMapping("user/withdraw")
	public ResponseEntity withdraw(@RequestBody User user) {
		try {
			Message message = new Message(userService.withdraw(user.getAmount(), user.getMobileNumber()));
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (Exception e) {
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("user/deposit")
	public  ResponseEntity deposit(@RequestBody User user) {
		try {
			Message message = new Message(userService.deposit(user.getAmount(), user.getMobileNumber()));
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (Exception e) {
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		}

	@PostMapping("user/amounttransfer")
	public  ResponseEntity amountTransfer(@RequestBody User user) {	
		try {
			Message message = new Message( userService.amountTransfer(user.getAccountNumber(), user.getAmount(), user.getMobileNumber()));
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (Exception e) {
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("user/transaction")
	public List<Transaction> transactionDetails(@RequestBody User user) {
		System.out.println(user);
		List<Transaction> userObj2 = userService.transactionDetails(user.getMobileNumber());
		return userObj2;
	}

}
