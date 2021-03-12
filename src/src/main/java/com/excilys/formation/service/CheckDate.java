package src.main.java.com.excilys.formation.service;

import java.time.LocalDate;

import java.util.regex.Pattern;

public class CheckDate {
	
	public static final String PATTERN_DATE = "[0-9]{4}-[0-1][0-9]-[0-3][0-9]";
	
	public static LocalDate dateValide(String date) throws Exception {
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
