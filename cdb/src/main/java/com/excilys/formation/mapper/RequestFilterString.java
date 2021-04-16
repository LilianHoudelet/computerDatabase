package com.excilys.formation.mapper;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.querydsl.core.types.Order;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RequestFilterString {

	public String convertOrderString(String entry) {
		if (entry == null || entry.isBlank()) {
			return "id";
		} else if ("computerName".equals(entry)) {
			return "name";
		} else if ("company".equals(entry)) {
			return "companyId";
		} else if ("introduced".equals(entry)) {
			return "introduced";
		} else if ("discontinued".equals(entry)) {
			return "discontinued";
		} else {
			return "id";
		}
	}
	
	public Order convertOrderbool(boolean upDown) {
		return (upDown ? Order.ASC : Order.DESC);
	}

}
