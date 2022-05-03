package com.bankapp.bankappapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.bankappapi.model.User;
import com.bankapp.bankappapi.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	AdminService adminService;

	@GetMapping("admin/login/{mobile_number}/{Password}")
	public String adminLogin(@PathVariable("mobile_number") String MobileNumber,
			@PathVariable("Password") String Password) {
		String message = adminService.adminLogin(MobileNumber, Password);
		return message;
	}

	@GetMapping("admin/list")
	public List<User> getAllUsers() {

		List<User> userList = adminService.getAllUsers();
		return userList;
	}

	@GetMapping("admin/activateuser/{mobileNumber}")
	public String activateUser(@PathVariable("mobileNumber") String mobileNumber) {
		String message = adminService.activateUsers(mobileNumber);
		return message;

	}

	@DeleteMapping("account/delete/{moileNumber}")
	public String deleteUserAccount(@PathVariable("moileNumber") String mobileNumber) {
		String message = adminService.deleteUserAccount(mobileNumber);
		return message;
	}

}
