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
	//private static final Logger LOGGER=LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserService userService;


	@PostMapping("user/register")
	public User registerdetails(@RequestBody User user) {
		User userDetails = userService.register(user.getName(),user.getAge(),user.getMobileNumber(),user.getGender(),user.getAmount(),user.getPassword());
	System.out.println(userDetails);
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

	@PostMapping("user/accountdetails")
	public   User accountDetails(@RequestBody User user) {
		System.out.println(user);
		User obj = userService.accountDetails(user.getMobileNumber(), user.getPassword());
		System.out.println(obj);
		return obj;
	}

	@PostMapping("user/withdraw")
	public Message withdraw(@RequestBody User user) {
		String message2 = userService.withdraw(user.getAmount(),user.getMobileNumber());
		System.out.println(user.getAmount());
		System.out.println(user.getMobileNumber());
		System.out.println(message2);
		Message message = new Message(message2);
		return message;
	}

	
	@PostMapping("user/deposit")
	public Message deposit(@RequestBody User user) {
		String message3 = userService.deposit(user.getAmount(),user.getMobileNumber());
		Message message = new Message(message3);
		return message;
	}

	@PostMapping("user/amounttransfer")
	public Message amountTransfer(@RequestBody User user) {
		String message4 = userService.amountTransfer(user.getAccountNumber(),user.getAmount(),user.getMobileNumber());
		Message message = new Message(message4);
		return message;

	}

	@PostMapping("user/transaction")
	public List<Transaction> transactionDetails(@RequestBody User user) {
		System.out.println(user);
		List<Transaction> userObj2 = userService.transactionDetails(user.getMobileNumber());
		return userObj2;
	}

}
