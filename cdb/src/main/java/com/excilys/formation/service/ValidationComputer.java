package com.excilys.formation.service;

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
	
}
