package com.excilys.formation.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan( basePackages = { "com.excilys.formation.dao","com.excilys.formation.service","com.excilys.formation.servlet","com.excilys.formation.model","com.excilys.formation.mapper"})
public class SpringConfig extends AbstractContextLoaderInitializer {
	
	@Override
	protected WebApplicationContext createRootApplicationContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(SpringConfig.class);
		return context;
	}
	
	@Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(getDataSource());
    }
	
	@Bean
	public DataSource getDataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setJdbcUrl( "jdbc:mysql://localhost:3306/computer-database-db" );
		dataSource.setUsername( "admincdb" );
		dataSource.setPassword( "qwerty1234" );
		return dataSource;
	}
}
