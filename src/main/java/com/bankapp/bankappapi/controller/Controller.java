package com.bankapp.bankappapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

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
		//System.out.println("Register entered");

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

		User userObj = userRepository.findByMobileNumberAndPassword(MobileNumber, Password);
		if (userObj == null) {
			return "no users found";
		} else {
			User userObj2 = userRepository.findByMobileNumberAndPasswordAndStatus(MobileNumber, Password, status);
			if (userObj2 == null) {
				return "Your Account is not activated kindly wait for 2 working days to activate your account";
			}
			return "success";
		}
	}

	@PutMapping("admin/login/{mobile_number}/{Password}")
	public String adminLogin(@PathVariable("mobile_number") String MobileNumber,
			@PathVariable("Password") String Password) {
		User user = new User();
		user.setMobileNumber(MobileNumber);
		user.setPassword(Password);
		String type = "Admin";

		User userObj = userRepository.findByMobileNumberAndPassword(MobileNumber, Password);
		if (userObj == null) {
			return "no users found";
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

	@PutMapping("user/accountdetails/{mobile_number}/{Password}")
	public User accountDetails(@PathVariable("mobile_number") String MobileNumber,
			@PathVariable("Password") String Password) {
		User user = new User();
		user.setMobileNumber(MobileNumber);
		user.setPassword(Password);
		User userObj = userRepository.findByMobileNumberAndPassword(MobileNumber, Password);
		return userObj;
	}

}
