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

	public String adminLogin(String MobileNumber, String Password) {
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

	public List<User> getAllUsers() {
		String type = "user";
		List<User> userList = adminrepository.findByuser(type);
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
}
