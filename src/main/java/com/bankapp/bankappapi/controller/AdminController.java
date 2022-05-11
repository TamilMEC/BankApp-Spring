package com.bankapp.bankappapi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.bankappapi.Dto.Message;
import com.bankapp.bankappapi.model.User;
import com.bankapp.bankappapi.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	AdminService adminService;

	@PostMapping("admin/login")
	public  ResponseEntity adminLogin(@RequestBody User user) {
	try{
			
		String message1 = adminService.adminLogin( user.getMobileNumber(), user.getPassword());
		System.out.println(message1);
			Message message = new Message("Success");
			return new ResponseEntity<>(message, HttpStatus.OK);
		}catch(Exception e) {
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	
	}

	@GetMapping("admin/listusers")
	public List<User> getAllUsers() {
		System.out.println("running");
		List<User> userList = adminService.getAllUsers();
		
		System.out.println(userList);
		System.out.println("success");
		return userList;
	}
	
	@GetMapping("admin/listinactiveusers")
	public List<User> getAllInactiveUsers() {
		System.out.println("running");
		List<User> userList = adminService.getAllInactiveUsers();
		System.out.println(userList);
		System.out.println("success");
		return userList;
	}

	@PostMapping("admin/activateuser")
	public String activateUser(@RequestBody User user) {
		System.out.println(user);
		String message = adminService.activateUsers(user.getMobileNumber());
		System.out.println(message);
		return message;
	}

	@DeleteMapping("account/delete")
	public String deleteUserAccount(@RequestBody User user) {
		String message = adminService.deleteUserAccount(user.getMobileNumber());
		return message;
	}

}
