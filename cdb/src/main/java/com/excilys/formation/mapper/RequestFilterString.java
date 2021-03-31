package com.excilys.formation.mapper;

public class RequestFilterString {

	public static String convertOrderString(String entry) {
		if (entry == null || entry.isBlank()) {
			return "computer.id";
		} else if ("computerName".equals(entry)) {
			return "computer.name";
		} else if ("company".equals(entry)) {
			return "company.name";
		} else if ("introduced".equals(entry)) {
			return "introduced";
		} else if ("discontinued".equals(entry)) {
			return "discontinued";
		} else {
			return "computer.id";
		}
	}
	
	public static String convertOrderbool(boolean upDown) {
		return (upDown ? " ASC " : " DESC ");
	}

}
