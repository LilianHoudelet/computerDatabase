package com.excilys.formation.service;

import java.time.LocalDate;

import com.excilys.formation.model.Computer;

public class ValidationComputer {

	public static boolean isComputerValid(Computer computer) {

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

	public static boolean isComputerValid(String computerName, String dateSortie, String dateRetrait) {

		if (computerName == null || computerName.isBlank()) {

			return false;
		}

		if (dateSortie != null && !dateSortie.isBlank()) {

			if (dateRetrait != null && !dateRetrait.isBlank()
					&& LocalDate.parse(dateRetrait).isBefore(LocalDate.parse(dateSortie))) {

				return false;
			}
		}

		return true;
	}
}
