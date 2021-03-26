package com.excilys.formation.dao;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Here we are connecting to the database, the informations given are used in the connectionUrl variable
 * and used to access the database
 * 
 * @author Lilian Houdelet
 *
 */
public class AccessDatabase {
	
	private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
    
    static String dataBase = "computer-database-db";
	
    static {
        config.setJdbcUrl( "jdbc:mysql://localhost:3306/computer-database-db" );
        config.setUsername( "admincdb" );
        config.setPassword( "qwerty1234" );
        config.setDataSourceClassName("com.mysql.cj.jdbc.Driver");
        ds = new HikariDataSource( config );
    } 
		
	private static Connection instance;
		
	static Logger logger = org.slf4j.LoggerFactory.getLogger(AccessDatabase.class);

	/**
	 * ici, une connextion a la base de donnee est cree
	 * @throws ClassNotFoundException
	 */
	private AccessDatabase() throws ClassNotFoundException {
		try {
			logger.info("Connection a la base de données avec les informations requises");
			instance = ds.getConnection();
		} catch (SQLException e) {
			logger.error("Impossible de se connecter à la base de données : Identifiants incorrects");
			e.printStackTrace();
		}
	}
	
	public static Connection getInstance() throws ClassNotFoundException, SQLException {
		if (instance == null || instance.isClosed()) {
			logger.info("Création d'une nouvelle instance");
			new AccessDatabase();
		}
		logger.debug("Utilisation de l'instance");
		return instance;
	}
}
