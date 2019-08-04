package com.expense.tracker;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpenseController {
	private final ExpenseRepository repository;
	
	ExpenseController(ExpenseRepository repository) {
		this.repository = repository;
	}
	
	// curl -v localhost:8080/expenses
	@GetMapping("/expenses")
	  List<Expense> all() {
	    return repository.findAll();
	  }
}
