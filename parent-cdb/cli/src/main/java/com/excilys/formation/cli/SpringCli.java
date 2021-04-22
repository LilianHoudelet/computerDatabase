package com.excilys.formation.cli;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;

import com.excilys.formation.consoleconfig.SpringWebConfig;

@Repository
public class SpringCli {
	
	public static void main(String[] args) throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringWebConfig.class);
		GestionMenu cli = context.getBean(GestionMenu.class);
		cli.run();
		((ConfigurableApplicationContext) context).close();
	}
}
