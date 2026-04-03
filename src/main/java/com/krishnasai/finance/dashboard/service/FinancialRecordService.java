package com.krishnasai.finance.dashboard.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.krishnasai.finance.dashboard.entity.FinancialRecord;
import com.krishnasai.finance.dashboard.entity.RecordType;
import com.krishnasai.finance.dashboard.repository.FinancialRecordRepository;

@Service
public class FinancialRecordService {
	
	@Autowired
	private FinancialRecordRepository repository;
	
//	Saving the Record
	public FinancialRecord createRecord(FinancialRecord financialRecord) {
		return repository.save(financialRecord);
	}
	
//	Fetching the Record
	public List<FinancialRecord> getAllRecords() {
		return repository.findAll();
	}
	
//	Filtering the Record
	public List<FinancialRecord> filterRecords(RecordType type, String category, LocalDate start, LocalDate end){
		if(type != null && category !=  null && start != null && end != null) {
			return repository.findByTypeAndCategoryAndDateBetween(type, category, start, end);
		}
		if(type != null) {
			return repository.findByType(type);
		}
		if(category != null) {
			return repository.findByCategory(category);
		}
		if(start != null && end != null) {
			return repository.findByDateBetween(start, end);
		}
		
		return repository.findAll();
	}
	
//	Deleting the Record
	public void deleteRecord(Long id) {
		repository.deleteById(id);
	}
	
	
	public Page<FinancialRecord> getPaginatedRecords(int page, int size){
		return repository.findAll(PageRequest.of(page, size));
	}
	
	
}
