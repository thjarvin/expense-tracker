package com.expense.tracker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class LocalDateConverter extends AbstractBeanField<LocalDate> {

	@Override
	protected LocalDate convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
        LocalDate parse = LocalDate.parse(value, formatter);
        return parse;
	}
	
}
