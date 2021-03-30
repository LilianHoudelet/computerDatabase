package com.excilys.formation.dao;


import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;

import com.zaxxer.hikari.HikariDataSource;

/**
 * Here we are connecting to the database, the informations given are used in the connectionUrl variable
 * and used to access the database
 * 
 * @author Lilian Houdelet
 *
 */
public class AccessDatabase {
	
//	private static final String PROP_FILE_NAME = "db.properties";
//	private static final String PROPERTY_URL = "db.url";
//	private static final String PROPERTY_USER = "db.user";
//	private static final String PROPERTY_PASSWORD = "db.password";
//	private static final String PROPERTY_DRIVER = "db.driver";
	
    private static HikariDataSource dataSource = new HikariDataSource();
		
	private static AccessDatabase instance = new AccessDatabase();
		
	static Logger logger = org.slf4j.LoggerFactory.getLogger(AccessDatabase.class);

	/**
	 * ici, une connextion a la base de donnee est cree
	 * @throws ClassNotFoundException
	 */
	private AccessDatabase() {
		try {
			dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
			dataSource.setJdbcUrl( "jdbc:mysql://localhost:3306/computer-database-db" );
			dataSource.setUsername( "admincdb" );
			dataSource.setPassword( "qwerty1234" );
			
		} catch (Exception e) {
			logger.error("Impossible de se connecter à la base de données : Identifiants incorrects");
		}
	}
	
	public static AccessDatabase getInstance() {
		if (instance == null) {
			logger.info("Création d'une nouvelle instance");
			new AccessDatabase();
		}
		logger.debug("Utilisation de l'instance");
		return instance;
	}
	
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}
