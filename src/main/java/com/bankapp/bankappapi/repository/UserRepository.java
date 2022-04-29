package com.bankapp.bankappapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapp.bankappapi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@SuppressWarnings("unchecked")
	User save(User user);

	User findByMobileNumberAndPasswordAndStatus(String MobileNumber, String Password, String status);

	User findByMobileNumberAndPasswordAndUser(String MobileNumber, String Password, String status);

	User findByMobileNumberAndPassword(String MobileNumber, String Password);

	List<User> findByuser(String user);

}
