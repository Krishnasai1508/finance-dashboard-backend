package com.krishnasai.finance.dashboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krishnasai.finance.dashboard.entity.FinancialRecord;
import com.krishnasai.finance.dashboard.repository.FinancialRecordRepository;

@Service
public class DashboardService {
	
	@Autowired
	private FinancialRecordRepository repository;
	
	public Map<String, Object> getSummary(){
		
		Double income = Optional.ofNullable(repository.getTotalIncome()).orElse(0.0);
		
		Double expense = Optional.ofNullable(repository.getTotalExpense()).orElse(0.0);
		
		Map<String, Object> response = new HashMap<>();
		
		response.put("totalIncome", income);
		response.put("totalExpense", expense);
		response.put("netBalance", income - expense);
		
		return response;
	}
	
	public Map<String, Double> getCategoryWise(){
		
		List<Object[]> data = repository.getCategoryWiseTotals();
		
		Map<String, Double> result = new HashMap<>();
		
		for(Object[] row : data) {
			String category = (String) row[0];
			Double amount = ((java.math.BigDecimal)(row[1])).doubleValue();
			result.put(category, amount);
		}
		
		return result;
	}
	
	public List<FinancialRecord> getRecent(){
		return repository.findTop5ByOrderByDateDesc();
	}
}
