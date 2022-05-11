package com.bankapp.bankappapi.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bankapp.bankappapi.model.User;
import com.bankapp.bankappapi.repository.AdminRepository;
import com.bankapp.bankappapi.repository.UserRepository;

@Component
public class AdminService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	AdminRepository adminrepository;
	@Autowired
	User user;

	public String adminLogin(String MobileNumber, String Password) throws Exception {
		user.setMobileNumber(MobileNumber);
		user.setPassword(Password);
		String type = "Admin";
		User userObj3 = userRepository.findByMobileNumber(MobileNumber);
		User userObj = userRepository.findByMobileNumberAndPassword(MobileNumber, Password);
		User userObj2 = userRepository.findByMobileNumberAndPasswordAndRole(MobileNumber, Password, type);
		if (userObj3 == null) {
			throw new Exception("No Admin account found");
		} else if (userObj == null) {
			throw new Exception("Invalid Mobile Numer or Passsword");
		} else if (userObj2 == null) {
			throw new Exception("You are not a admin.If you are user try to login in user Login");
		}else {
		return "success";
		}

	}

	public List<User> getAllUsers() {
		String type = "user";
		List<User> userList = adminrepository.findByRole(type);
		return userList;
	}

	public List<User> getAllInactiveUsers() {
		String status = "inactive";
		List<User> userList = adminrepository.findByStatus(status);
		return userList;
	}

	public String activateUsers(String mobileNumber) {
		String status = "Active";
		int row = adminrepository.changeStatus(mobileNumber, status);
		if (row == 1) {
			return "Successfully Activated User Account";
		} else {
			return "No user found";
		}
	}

	public String deleteUserAccount(String mobileNumber) {
		int row = adminrepository.deleteByMobileNumber(mobileNumber);
		if (row == 1) {
			return "User Account successfully deleted";
		} else {
			return "Unable to delete user account";
		}
	}

	public String inactivateUsers(String mobileNumber) {
		String status = "inactive";
		int row = adminrepository.changeStatus(mobileNumber, status);
		if (row == 1) {
			return "Successfully Activated User Account";
		} else {
			return "No user found";
		}
	}
}
