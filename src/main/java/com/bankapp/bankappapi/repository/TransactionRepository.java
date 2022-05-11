package com.bankapp.bankappapi.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.bankapp.bankappapi.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	@SuppressWarnings("unchecked")
	@Transactional
	@Modifying
	Transaction save(Transaction transaction);

}
