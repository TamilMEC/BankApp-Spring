package com.bankapp.bankappapi.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bankapp.bankappapi.model.Transaction;
import com.bankapp.bankappapi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@SuppressWarnings("unchecked")
	User save(User user);

	// Transaction save(Transaction transaction);
	User findByMobileNumberAndPasswordAndStatus(String MobileNumber, String Password, String status);

	User findByMobileNumberAndPasswordAndUser(String MobileNumber, String Password, String type);

	User findByMobileNumberAndPassword(String MobileNumber, String Password);

	List<User> findByuser(String user);

	@Transactional
	@Modifying
	@Query("update banking u set u.amount = :amount where u.mobileNumber=:MobileNumber")
	void changeAmount(@Param("amount") int amount, @Param("MobileNumber") String MobileNumber);

	User findByMobileNumber(String mobileNumber);

	User findByAccountNumber(int accountNumber);

	Transaction save(Transaction transaction);

	@Modifying
	@Query("select u from com.bankapp.bankappapi.model.Transaction u where u.mobileNumber=:mobilenumber")
	List<Transaction> findbymobileNumber(@Param("mobilenumber") String mobilenumber);
	// Transaction findbymobileNumber(String mobileNumber);

	@Transactional
	@Modifying
	@Query("update banking u set u.status=:status where u.mobileNumber=:mobileNumber")
	int changeStatus(@Param("mobileNumber") String mobilenumber, @Param("status") String status);

	@Transactional
	@Modifying
	@Query("delete from com.bankapp.bankappapi.model.User u where u.mobileNumber=:mobileNumber")
	int deleteByMobileNumber(@Param("mobileNumber") String mobileNumber);

}
