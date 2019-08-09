package com.expense.tracker;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CsvReader {
	private static final String CSV_DIR_NAME = "/home/timo/Asiakirjat/Test/csv";
	
	public CsvReader() {
	}
	
	public List<Expense> getExpenses() throws Exception {
		return getPaths(CSV_DIR_NAME).
				map(x -> expenseBeanBuilder(x, Expense.class)).
				flatMap(x -> x.stream()).
				collect(Collectors.toList());
	}
	
	private List<Expense> expenseBeanBuilder(Path path, Class clazz) {
	     CsvTransfer csvTransfer = new CsvTransfer();
	     ColumnPositionMappingStrategy ms = new ColumnPositionMappingStrategy();
	     ms.setType(clazz);
	     Reader reader = null;
	     
	     try {
	    	 reader = Files.newBufferedReader(path);
	    	 CsvToBean cb = new CsvToBeanBuilder(reader)
	    			 .withType(clazz)
	    			 .withMappingStrategy(ms)
	    			 .withSeparator(';')
	    			 .withSkipLines(1)
	    			 .build();
	 
	    	 csvTransfer.setCsvList(cb.parse());
	    	 reader.close();
	     } catch(Exception x) {
	    	 throw new RuntimeException("Failed to read csv file.", x);
	     } finally {
	    	 try {
				reader.close();
			} catch (IOException x) {
				throw new RuntimeException("Failed to close reader.", x);
			}
	     }
	     
	    return csvTransfer.getCsvList();
	}
	
	private Stream<Path> getPaths(String dirName) {
		File fileName = new File(dirName);
        File[] fileList = fileName.listFiles();
        return Arrays.stream(fileList).
        		filter(x -> x.getAbsolutePath().endsWith(".csv")).
        		map(x -> Paths.get(x.getAbsolutePath()));
	}
}
