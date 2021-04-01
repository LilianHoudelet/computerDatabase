package com.excilys.formation.spring;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

//import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan( basePackages = { "com.excilys.formation.dao","com.excilys.formation.service","com.excilys.formation.servlet","com.excilys.formation.model"})
public class SpringWebConfig implements WebApplicationInitializer {
	
	//private static final String PROP_FILE_NAME = "/config/db.properties";
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
	    context.register(SpringWebConfig.class);
	    context.setServletContext(servletContext);
	}

	@Bean
	public DataSource getDataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setJdbcUrl( "jdbc:mysql://localhost:3306/computer-database-db" );
		dataSource.setUsername( "admincdb" );
		dataSource.setPassword( "qwerty1234" );
		return dataSource;
		//return new HikariDataSource(new HikariConfig(PROP_FILE_NAME));
	}

	
}
