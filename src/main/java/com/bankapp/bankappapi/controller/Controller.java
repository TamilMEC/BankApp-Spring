package com.bankapp.bankappapi.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.bankappapi.model.Transaction;
import com.bankapp.bankappapi.model.User;
import com.bankapp.bankappapi.repository.UserRepository;

@RestController
public class Controller {

	@Autowired
	UserRepository userRepository;

	@GetMapping("user/register/{name}/{age}/{mobileNumber}/{gender}/{amount}/{password}")
	public User registerdetails(@PathVariable("name") String name, @PathVariable("age") int age,
			@PathVariable("mobileNumber") String mobileNumber, @PathVariable("gender") String gender,
			@PathVariable("amount") int amount, @PathVariable("password") String password) {
		User user = new User();
		user.setName(name);
		user.setAge(age);
		user.setMobileNumber(mobileNumber);
		user.setGender(gender);
		user.setAmount(amount);
		user.setPassword(password);
		user.setStatus("inactive");
		user.setUser("user");
		User userObj = userRepository.save(user);
		return userObj;

	}

	@PutMapping("user/login/{mobile_number}/{Password}")
	public String login(@PathVariable("mobile_number") String MobileNumber, @PathVariable("Password") String Password) {
		User user = new User();
		user.setMobileNumber(MobileNumber);
		user.setPassword(Password);
		String status = "Active";
		String type = "user";

		User userObj = userRepository.findByMobileNumberAndPassword(MobileNumber, Password);
		User userObj3 = userRepository.findByMobileNumber(MobileNumber);
		if (userObj3 == null) {
			return "No user found.If you are new user register then login";
		} else {
			User userObj4 = userRepository.findByMobileNumberAndPasswordAndUser(MobileNumber, Password, type);
			if (userObj4 == null) {
				return "You are not an user.If you are admin try to login in admin login";
			} else {
				if (userObj == null) {
					return "Please enter valid Mobile number or password";
				} else {
					User userObj2 = userRepository.findByMobileNumberAndPasswordAndStatus(MobileNumber, Password,
							status);
					if (userObj2 == null) {
						return "Your Account is not activated kindly wait for 2 working days to activate your account";
					}
					return "success";
				}
			}
		}
	}

	@GetMapping("admin/login/{mobile_number}/{Password}")
	public String adminLogin(@PathVariable("mobile_number") String MobileNumber,
			@PathVariable("Password") String Password) {
		User user = new User();
		user.setMobileNumber(MobileNumber);
		user.setPassword(Password);
		String type = "Admin";
		User userObj3 = userRepository.findByMobileNumber(MobileNumber);
		if (userObj3 == null) {
			return "No Admin account found";
		}
		User userObj = userRepository.findByMobileNumberAndPassword(MobileNumber, Password);
		if (userObj == null) {
			return "Invalid Mobile Numer or Passsword";
		} else {
			User userObj2 = userRepository.findByMobileNumberAndPasswordAndUser(MobileNumber, Password, type);
			if (userObj2 == null) {
				return "You are not a admin.If you are user try to login in user Login";
			}
			return "success";
		}
	}

	@GetMapping("admin/list")
	public List<User> getAllUsers() {
		String type = "user";
		List<User> userList = userRepository.findByuser(type);
		return userList;
	}

	@GetMapping("user/accountdetails/{mobile_number}/{Password}")
	public User accountDetails(@PathVariable("mobile_number") String MobileNumber,
			@PathVariable("Password") String Password) {
		User user = new User();
		user.setMobileNumber(MobileNumber);
		user.setPassword(Password);
		User userObj = userRepository.findByMobileNumberAndPassword(MobileNumber, Password);
		return userObj;
	}

	@GetMapping("user/withdraw/{amount}/{mobilenumber}")
	public String withdraw(@PathVariable("amount") int amount, @PathVariable("mobilenumber") String MobileNumber) {
		// User userobj =
		LocalDateTime timestamp = LocalDateTime.now();
		String time = timestamp.toString();
		User user = userRepository.findByMobileNumber(MobileNumber);
		int userAmount = user.getAmount();
		System.out.println(userAmount);
		int accountNumber = user.getAccountNumber();
		int totalamount = userAmount - amount;

		Transaction transaction = new Transaction();
		transaction.setMobileNumber(MobileNumber);
		transaction.setUserAccountNumber(accountNumber);
		transaction.setAmount(amount);
		transaction.setType("Debited");
		transaction.setCurrentbalance(totalamount);
		transaction.setDatetime(time);

		if (amount > userAmount) {
			return "insufficint account balance";
		} else {
			userRepository.changeAmount(totalamount, MobileNumber);
			Transaction userObj = userRepository.save(transaction);
			int currentbalance = userObj.getCurrentbalance();
			return "withdraw successfully! Your Current balance = " + currentbalance + "";
		}
	}

	@GetMapping("user/deposit/{amount}/{mobilenumber}")
	public String deposit(@PathVariable("amount") int amount, @PathVariable("mobilenumber") String MobileNumber) {
		LocalDateTime timestamp = LocalDateTime.now();
		String time = timestamp.toString();
		User user = userRepository.findByMobileNumber(MobileNumber);
		int userAmount = user.getAmount();
		System.out.println(userAmount);
		int accountNumber = user.getAccountNumber();
		int totalamount = userAmount + amount;

		Transaction transaction = new Transaction();
		transaction.setMobileNumber(MobileNumber);
		transaction.setUserAccountNumber(accountNumber);
		transaction.setAmount(amount);
		transaction.setType("Credited");
		transaction.setCurrentbalance(totalamount);
		transaction.setDatetime(time);

		if (amount > userAmount) {
			return "insufficint account balance";
		} else {
			userRepository.changeAmount(totalamount, MobileNumber);
			Transaction userObj = userRepository.save(transaction);
			int currentbalance = userObj.getCurrentbalance();
			return "Deposit successfully! Your Current balance = " + currentbalance + "";
		}
	}

	@GetMapping("user/amounttransfer/{accountnumber}/{amount}/{mobilenumber}")
	public String amountTransfer(@PathVariable("accountnumber") int accountnumber, @PathVariable("amount") int amount,
			@PathVariable("mobilenumber") String MobileNumber) {
		LocalDateTime timestamp = LocalDateTime.now();
		String time = timestamp.toString();
		User row = userRepository.findByAccountNumber(accountnumber);
		if (row == null) {
			return "no user found in this account number";
		} else {
			// Object to get sender amount to update in sender account
			User user = userRepository.findByMobileNumber(MobileNumber);
			int userAmount = user.getAmount();
			int accountNumber = user.getAccountNumber();
			int totalamount = userAmount - amount;

			// get receiver amount to update in database
			User user2 = userRepository.findByAccountNumber(accountnumber);
			String mobile = user2.getMobileNumber();
			int receiveramount = user2.getAmount();
			int totalamounttoreceiver = amount + receiveramount;

			if (amount > userAmount) {
				return "insufficint account balance";
			} else {
				// update amount in sender account
				userRepository.changeAmount(totalamount, MobileNumber);
				// update amount in receiver account
				userRepository.changeAmount(totalamounttoreceiver, mobile);

				// note transacton for sender
				Transaction transaction = new Transaction();
				transaction.setMobileNumber(MobileNumber);
				transaction.setUserAccountNumber(accountNumber);
				transaction.setAmount(amount);
				transaction.setType("Transfered");
				transaction.setCurrentbalance(totalamount);
				transaction.setDatetime(time);
				transaction.setAccountNumber(accountnumber);
				Transaction userObj = userRepository.save(transaction);
				// note transacton for receiver
				reciverTransaction(accountnumber, amount, accountNumber, time);
				int currentbalance = userObj.getCurrentbalance();
				return "Transfered successfully! Your Current balance = " + currentbalance + "";
			}
		}

	}

	public void reciverTransaction(int accountNumber, int amount2, int accountNumber2, String time) {

		User user = userRepository.findByAccountNumber(accountNumber);
		String mobileNumber = user.getMobileNumber();
		int amount = user.getAmount();
		// note transaction for receiver
		Transaction transaction = new Transaction();
		transaction.setMobileNumber(mobileNumber);
		transaction.setUserAccountNumber(accountNumber);
		transaction.setAmount(amount2);
		transaction.setType("Received");
		transaction.setAccountNumber(accountNumber2);
		transaction.setCurrentbalance(amount + amount2);
		transaction.setDatetime(time);
		userRepository.save(transaction);

	}

	@GetMapping("user/transaction/{mobileNumber}")
	public List<Transaction> transactionDetails(@PathVariable("mobileNumber") String mobileNumber) {

		List<Transaction> userObj = userRepository.findbymobileNumber(mobileNumber);
		System.out.println(userObj);
		return userObj;

	}

	@GetMapping("admin/activateuser/{mobileNumber}")
	public String activateUser(@PathVariable("mobileNumber") String mobileNumber) {
		String status = "Activate";
		int row = userRepository.changeStatus(mobileNumber, status);
		if (row == 1) {
			return "Successfully Activated User Account";
		} else {
			return "No user found";
		}

	}

	@DeleteMapping("account/delete/{moileNumber}")
	public String deleteUserAccount(@PathVariable("moileNumber") String mobileNumber) {
		int row = userRepository.deleteByMobileNumber(mobileNumber);
		if (row == 1) {
			return "User Account successfully deleted";
		} else {
			return "Unable to delete user account";
		}
	}

}
