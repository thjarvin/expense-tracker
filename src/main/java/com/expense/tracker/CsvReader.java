package com.expense.tracker;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CsvReader {
	
	public CsvReader() {
	}
	
	public List<Expense> getExpenses() throws Exception {
		//URL systemResource = ClassLoader.getSystemResource("/home/timo/Lataukset/Penno_2019-08.csv");
	    //Path path = Paths.get(systemResource.toURI()); 
		Path path = Paths.get("/home/timo/Lataukset/Penno_2019-08.csv");
	    return expenseBeanBuilder(path, Expense.class);
	}
	
	public List<Expense> expenseBeanBuilder(Path path, Class clazz) throws Exception {
	     CsvTransfer csvTransfer = new CsvTransfer();
	     ColumnPositionMappingStrategy ms = new ColumnPositionMappingStrategy();
	     ms.setType(clazz);
	 
	     Reader reader = Files.newBufferedReader(path);
	     CsvToBean cb = new CsvToBeanBuilder(reader)
	       .withType(clazz)
	       .withMappingStrategy(ms)
	       .withSeparator(';')
	       .withSkipLines(1)
	       .build();
	 
	    csvTransfer.setCsvList(cb.parse());
	    reader.close();
	    return csvTransfer.getCsvList();
	}
}
