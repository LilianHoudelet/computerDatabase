package com.excilys.formation.validation;

import java.time.LocalDate;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.excilys.formation.model.Computer;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ValidationComputer {

	public boolean isComputerValid(Computer computer) {

		if (computer.getName() == null || computer.getName().isBlank()) {

			return false;
		}
		if (computer.getDateSortie() != null) {

			if (computer.getDateRetrait() != null && computer.getDateRetrait().isBefore(computer.getDateSortie())) {

				return false;
			}
		}

		return true;
	}

	public boolean isComputerValid(String computerName, String dateSortie, String dateRetrait) {

		if (computerName == null || computerName.isBlank()) {
			System.out.println("name");
			return false;
		}

		if (dateSortie != null && !dateSortie.isBlank()) {
			System.out.println("date Sortie");
			if (dateRetrait != null && !dateRetrait.isBlank()
					&& LocalDate.parse(dateRetrait).isBefore(LocalDate.parse(dateSortie))) {

				return false;
			}
		}

		return true;
	}
}
