package com.bankapp.bankappapi.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bankapp.bankappapi.model.Transaction;
import com.bankapp.bankappapi.model.User;
import com.bankapp.bankappapi.repository.TransactionRepository;
import com.bankapp.bankappapi.repository.UserRepository;

@Component
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TransactionRepository transactionrepository;

	@Autowired
	User user;

//	@Autowired
//	Transaction transaction;

	public User register(String name, int age, String mobileNumber, String gender, int amount, String password) {
		User user = new User();
		user.setName(name);
		user.setAge(age);
		user.setMobileNumber(mobileNumber);
		user.setGender(gender);
		user.setAmount(amount);
		user.setPassword(password);
		user.setStatus("inactive");
		user.setRole("user");
		User userObj = userRepository.save(user);
		return userObj;
	}

	public String login(String MobileNumber, String Password) throws Exception{
		user.setMobileNumber(MobileNumber);
		user.setPassword(Password);
		String status = "Active";
		String type = "user";
		User userObj = userRepository.findByMobileNumberAndPassword(MobileNumber, Password);
		User userObj3 = userRepository.findByMobileNumber(MobileNumber);
		System.out.println(MobileNumber);
		System.out.println(Password);
		if (userObj3 == null) {
			throw new Exception ("No user found.If you are new user register then login");
		} else {
			User userObj4 = userRepository.findByMobileNumberAndPasswordAndRole(MobileNumber, Password, type);
			if (userObj4 == null) {
				throw new Exception ("You are not an user.If you are admin try to login in admin login");
			} else {
				if (userObj == null) {
					throw new Exception("Please enter valid Mobile number or password");
				} else {
					User userObj2 = userRepository.findByMobileNumberAndPasswordAndStatus(MobileNumber, Password,
							status);
					if (userObj2 == null) {
						throw new Exception ("Your Account is not activated kindly wait for 2 working days to activate your account");
					} else {
						return "success";
					}
				}
			}
		}
	}

	public User accountDetails(String MobileNumber, String Password) {
		user.setMobileNumber(MobileNumber);
		user.setPassword(Password);
		User userObj = userRepository.findByMobileNumberAndPassword(MobileNumber, Password);
		System.out.println(userObj);
		return userObj;
	}

	public String withdraw(int amount, String MobileNumber) throws Exception {
		LocalDateTime timestamp = LocalDateTime.now();
		String time = timestamp.toString();
		User user = userRepository.findByMobileNumber(MobileNumber);
		if (user == null) {
			throw new Exception ("no user account found in this account number");
		} else {
			int userAmount = user.getAmount();
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
				throw new Exception ("insufficint account balance");
			} else {
				userRepository.changeAmount(totalamount, MobileNumber);
				Transaction userObj = transactionrepository.save(transaction);
				int currentbalance = userObj.getCurrentbalance();
				return "withdraw successfully! Your Current balance = " + currentbalance + "";
			}
		}
	}

	public String deposit(int amount, String MobileNumber) {
		LocalDateTime timestamp = LocalDateTime.now();
		String time = timestamp.toString();
		User user = userRepository.findByMobileNumber(MobileNumber);
		int userAmount = user.getAmount();
		int accountNumber = user.getAccountNumber();
		int totalamount = userAmount + amount;
		Transaction transaction = new Transaction();
		transaction.setMobileNumber(MobileNumber);
		transaction.setUserAccountNumber(accountNumber);
		transaction.setAmount(amount);
		transaction.setType("Credited");
		transaction.setCurrentbalance(totalamount);
		transaction.setDatetime(time);
		userRepository.changeAmount(totalamount, MobileNumber);
		Transaction userObj = transactionrepository.save(transaction);
		int currentbalance = userObj.getCurrentbalance();
		return "Deposit successfully! Your Current balance = " + currentbalance + "";
	}

	public String amountTransfer(int accountnumber, int amount, String MobileNumber) throws Exception {
		LocalDateTime timestamp = LocalDateTime.now();
		String time = timestamp.toString();
		User row = userRepository.findByAccountNumber(accountnumber);
		if (row == null) {
			throw new Exception ("no user found in this account number");
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
				throw new Exception ("insufficint account balance");
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
				Transaction userObj = transactionrepository.save(transaction);
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
		transactionrepository.save(transaction);
	}

	public List<Transaction> transactionDetails(String mobileNumber) {
		List<Transaction> userObj = userRepository.findUsingmobileNo(mobileNumber);
		return userObj;
	}

}
