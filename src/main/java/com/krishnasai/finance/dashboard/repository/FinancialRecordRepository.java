package com.krishnasai.finance.dashboard.repository;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.krishnasai.finance.dashboard.entity.FinancialRecord;
import com.krishnasai.finance.dashboard.entity.RecordType;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {
	
//	Fetching the Record based on RecordType
	List<FinancialRecord> findByType(RecordType type);
	
//	Fetching the Record based on Category
	List<FinancialRecord> findByCategory(String category);
	
//	Fetching the Record based on Date(DateBetween from start to end)
	List<FinancialRecord> findByDateBetween(LocalDate start, LocalDate end);
	
//	Fetching the Record based on RecordType, Category, Date(DateBetween from start to end)
	List<FinancialRecord> findByTypeAndCategoryAndDateBetween(
			RecordType recordType, 
			String category, 
			LocalDate start, 
			LocalDate end);
	
	
	@Query("SELECT SUM(f.amount) FROM FinancialRecord f WHERE f.type = 'INCOME'")
	Double getTotalIncome();
	
	@Query("SELECT SUM(f.amount) FROM FinancialRecord f WHERE f.type = 'EXPENSE'")
	Double getTotalExpense();
	
	@Query("SELECT f.category, SUM(f.amount) FROM FinancialRecord f GROUP BY f.category")
	List<Object[]> getCategoryWiseTotals();
	
//	JPQL doesn't support the LIMIT. So, by default we are providing the top 5 FinacialRecord
	List<FinancialRecord> findTop5ByOrderByDateDesc();
	
	
	org.springframework.data.domain.Page<FinancialRecord> findAll(Pageable pageable);
	
}
