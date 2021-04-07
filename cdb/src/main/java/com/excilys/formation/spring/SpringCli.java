package com.excilys.formation.spring;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.formation.model.GestionMenu;


public class SpringCli {
	
	public static void main(String[] args) throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringWebConfig.class);
		GestionMenu cli = context.getBean(GestionMenu.class);
		cli.run();
		((ConfigurableApplicationContext) context).close();
	}
}
