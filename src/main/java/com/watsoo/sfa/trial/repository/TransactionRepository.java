package com.watsoo.sfa.trial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.watsoo.sfa.trial.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	@Query(value = "SELECT * FROM transaction where company_id=?1 and is_active=?2", nativeQuery = true)
	Transaction findByCompanyIdAndIsAcive(Long id, boolean isActive);

	@Query(value = "select * from transaction where date(end_date) = date(?1) and is_active=true", nativeQuery = true)
	List<Transaction> findByEndDate(String expiryDate);

}
