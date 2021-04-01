package com.excilys.formation.service;

import java.time.LocalDate;

import java.util.regex.Pattern;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CheckDate {
	
	public static final String PATTERN_DATE = "[0-9]{4}-[0-1][0-9]-[0-3][0-9]";
	
	public LocalDate dateValide(String date) throws Exception {
		LocalDate dateLocalDate = null;
		if (Pattern.matches(PATTERN_DATE, date)) {
			try {
				dateLocalDate = LocalDate.parse(date);
			} catch (Exception e) {
				throw new Exception("Invalid Date");
			}
		}
		return dateLocalDate;
	}
}
