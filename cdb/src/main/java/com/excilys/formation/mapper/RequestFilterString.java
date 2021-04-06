package com.excilys.formation.mapper;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RequestFilterString {

	public String convertOrderString(String entry) {
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
	
	public String convertOrderbool(boolean upDown) {
		return (upDown ? " ASC " : " DESC ");
	}

}
