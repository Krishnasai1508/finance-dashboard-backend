package com.krishnasai.finance.dashboard.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krishnasai.finance.dashboard.entity.FinancialRecord;
import com.krishnasai.finance.dashboard.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	private DashboardService service;
	
	@PreAuthorize("hasAnyRole('ADMIN', 'ANALYST')")
	@GetMapping("/summary")
	public Map<String, Object> summary(){
		return service.getSummary();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'ANALYST')")
	@GetMapping("/category-wise")
	public Map<String, Double> categoryWise(){
		return service.getCategoryWise();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'ANALYST')")
	@GetMapping("/recent")
	public List<FinancialRecord> recent(){
		return service.getRecent();
	}
}
