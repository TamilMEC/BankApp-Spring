package com.bankapp.bankappapi.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bankapp.bankappapi.model.Transaction;
import com.bankapp.bankappapi.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@SuppressWarnings("unchecked")
	User save(User user);

	User findByMobileNumberAndPasswordAndStatus(String MobileNumber, String Password, String status);

	User findByMobileNumberAndPasswordAndRole(String MobileNumber, String Password, String type);

	User findByMobileNumberAndPassword(String MobileNumber, String Password);
	
	@Transactional
	@Modifying
	@Query("update banking u set u.amount = :amount where u.mobileNumber=:MobileNumber")
	void changeAmount(@Param("amount") int amount, @Param("MobileNumber") String MobileNumber);

	User findByMobileNumber(String mobileNumber);

	User findByAccountNumber(int accountNumber);
	
	

	
	@Query("select u from com.bankapp.bankappapi.model.Transaction u where u.mobileNumber=:mobilenumber")
	List<Transaction> findUsingmobileNo(@Param("mobilenumber") String mobilenumber);

}