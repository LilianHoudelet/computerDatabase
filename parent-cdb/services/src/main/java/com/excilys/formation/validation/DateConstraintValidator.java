package com.excilys.formation.validation;

import java.time.LocalDate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.excilys.formation.dto.AddComputerDTO;

@Component
public class DateConstraintValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return AddComputerDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AddComputerDTO computer = (AddComputerDTO) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "","Empty Name");
		
		if (computer.getDateSortie() != null && !computer.getDateSortie().isBlank()) {
			System.out.println("date Sortie");
			if (computer.getDateRetrait() != null && !computer.getDateRetrait().isBlank()
					&& LocalDate.parse(computer.getDateRetrait()).isBefore(LocalDate.parse(computer.getDateSortie()))) {

				errors.rejectValue("dateRetrait", "", "Date de Retrait ne peux être avant la date de Sortie");
			}
		}
		
	}

}
