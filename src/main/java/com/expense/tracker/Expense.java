package com.expense.tracker;

import lombok.Data;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;
/*
TODO:
	- toteuta niin että lukee tietystä kansiosta sisään kaikki csv-tiedostot mitä sieltä löytyy
	- mieti ja toteuta erilaisia hakuja
*/
@Data
@Entity
public class Expense {
	private @Id @GeneratedValue Long id;
	
	@CsvCustomBindByPosition(position = 0, converter = LocalDateConverter.class)
	private LocalDate time;
	
	@CsvBindByPosition(position = 1)
	private String type;
	
	@CsvCustomBindByPosition(position = 2, converter = DoubleConverter.class)
	private double amount;
	
	@CsvBindByPosition(position = 3)
	private String category;
	
	@CsvBindByPosition(position = 4)
	private String description;
	
	public Expense() {
	}
	
	public Expense(LocalDate time, String type, double amount, String category, String description) {
		super();
		this.time = time;
		this.type = type;
		this.amount = amount;
		this.category = category;
		this.description = description;
	}
}
