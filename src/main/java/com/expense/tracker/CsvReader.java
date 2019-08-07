package com.expense.tracker;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CsvReader {
	private static final String CSV_DIR_NAME = "/home/timo/Asiakirjat/Test/csv";
	
	public CsvReader() {
	}
	
	public List<Expense> getExpenses() throws Exception {
		List<Expense> expenses = new ArrayList<>();
		
		for (Path path : getPaths(CSV_DIR_NAME)) {
			expenses.addAll(expenseBeanBuilder(path, Expense.class));
		}
		
	    return expenses;
	}
	
	private List<Expense> expenseBeanBuilder(Path path, Class clazz) throws Exception {
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
	
	private List<Path> getPaths(String dirName) {
		List<Path> paths = new ArrayList<>();
		File fileName = new File(dirName);
        File[] fileList = fileName.listFiles();
        
        for (File file: fileList) {
        	if (file.isFile() && file.getAbsolutePath().endsWith(".csv")) {
        		paths.add(Paths.get(file.getAbsolutePath()));
        		log.info("Added path '" + file.getAbsolutePath() + "'.");
        	} else {
        		log.warn("Path '" + file.getAbsolutePath() + "' not added.");
        	}
        }
        return paths;
	}
}
