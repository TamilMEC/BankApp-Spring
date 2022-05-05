package com.bankapp.bankappapi.repository;

import java.util.List;   
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.bankapp.bankappapi.model.User;

@Component
@Repository
public interface AdminRepository extends JpaRepository<User, Integer> {

	List<User> findByuser(String user);

	@Transactional
	@Modifying
	@Query("update com.bankapp.bankappapi.model.User u set u.status=:status where u.mobileNumber=:mobileNumber")
	int changeStatus(@Param("mobileNumber") String mobilenumber, @Param("status") String status);

	@Transactional
	@Modifying
	@Query("delete from com.bankapp.bankappapi.model.User u where u.mobileNumber=:mobileNumber")
	int deleteByMobileNumber(@Param("mobileNumber") String mobileNumber);

}
