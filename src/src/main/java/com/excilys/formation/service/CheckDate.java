package src.main.java.com.excilys.formation.service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Pattern;

public class CheckDate {
	
	public static final String PATTERN_DATE = "[0-9]{4}-[0-1][0-9]-[0-3][0-9]";
	
	public static LocalDate dateValide(String date) {
		Optional<LocalDate> dateLocalDate = null;
		if (Pattern.matches(PATTERN_DATE, date)) {
			try {
				dateLocalDate = Optional.ofNullable(LocalDate.parse(date));
			} catch (Exception e) {
				System.out.println("Invalid Date Retrait");
			}
		}
		return dateLocalDate.orElse(null);
	}
}
