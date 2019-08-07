package com.expense.tracker;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {
	CsvReader csvReader = new CsvReader();

	@Bean
	CommandLineRunner initDatabase(ExpenseRepository repository) {
		return args -> {
			for (Expense expense : csvReader.getExpenses()) {
				log.info("Preloading " + repository.save(expense));
			}
			//log.info("Preloading " + repository.save(new Expense(new Date(), "Tulo", 3500.40f, "Palkka", "")));
			//log.info("Preloading " + repository.save(new Expense(new Date(), "Meno", 23.35f, "Ruokakauppa", "Prisma")));
		};
	}
}