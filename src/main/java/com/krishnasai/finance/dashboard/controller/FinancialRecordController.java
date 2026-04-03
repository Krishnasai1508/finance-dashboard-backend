package com.krishnasai.finance.dashboard.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.krishnasai.finance.dashboard.entity.FinancialRecord;
import com.krishnasai.finance.dashboard.entity.RecordType;
import com.krishnasai.finance.dashboard.service.FinancialRecordService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/records")
public class FinancialRecordController {
	
	@Autowired
	private FinancialRecordService service;
	
//	Create Record
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public FinancialRecord create(@Valid @RequestBody FinancialRecord record) {
		return service.createRecord(record);
	}
	
//	Fetch All Records
	@PreAuthorize("hasAnyRole('ADMIN', 'ANALYST', 'VIEWER')")
	@GetMapping
	public Object getAll(@RequestParam(required = false) RecordType type,
						 @RequestParam(required = false) String category, 
						 
						 @RequestParam(required = false) 
						 @DateTimeFormat(iso = ISO.DATE) LocalDate startDate, 
						 
						 @RequestParam(required = false) 
						 @DateTimeFormat(iso = ISO.DATE) LocalDate endDate,
						 
						 @RequestParam(defaultValue = "0") int page,
						 @RequestParam(defaultValue = "10") int size){
		
		if(type != null || category != null || startDate != null || endDate != null) {
			return service.filterRecords(type, category, startDate, endDate);
		}
		
		return service.getPaginatedRecords(page, size);
	}
	
//	Delete Record by ID
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.deleteRecord(id);
	}
}
