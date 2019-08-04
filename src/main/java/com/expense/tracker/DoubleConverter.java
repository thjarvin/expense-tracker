package com.expense.tracker;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class DoubleConverter extends AbstractBeanField<Double> {

	@Override
	protected Double convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
		return Double.parseDouble(value.replace(",", "."));
	}
	
}
