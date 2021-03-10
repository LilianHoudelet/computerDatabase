package src.main.java.com.excilys.formation.mapper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import src.main.java.com.excilys.formation.model.Computer;

public class AjoutDatabase {
	
	public static final String REQUETE_PART1 = "INSERT INTO computer (id, name";
	public static final String REQUETE_PART2 = ") VALUES ( ";
	
	public static final String REQUETE_ID_CONSTRUCTEUR = "SELECT id FROM company WHERE name = \"";
	
	public static final String REQUETE_NOMBRE_ORDINATEURS = "SELECT max(id) FROM company";
	
	public static final int PAS_DE_CONSTRUCTEUR_TROUVE = 0;
			
	public static void ajoutDB(Computer machine) throws ClassNotFoundException, SQLException{
		
		Connection con = AccessDatabase.getInstance(); 
		
		int idConstructeur = 0;
		int idMachine = 0;
		// 1ere partie : récupération de l'id de la compagnie
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(REQUETE_ID_CONSTRUCTEUR + machine.getCompany() +"\"");
		
		if(rs.next()) {
			idConstructeur = rs.getInt(1);
		}
		
		// 2e partie : création de l'id machine
		 
		Statement stmt2 = con.createStatement();
		ResultSet rs2 = stmt.executeQuery(REQUETE_NOMBRE_ORDINATEURS);
		
		if(rs2.next()) {
			idMachine = rs2.getInt(1) + 1;
		}
		// 3e partie : ajout dans la base
		
		try{
			Statement stmt3 = con.createStatement();
			String requetePart1Complete = REQUETE_PART1;
			String requetePart2Complete = REQUETE_PART2+idMachine + ", " + machine.getName();
			
			if(idConstructeur != PAS_DE_CONSTRUCTEUR_TROUVE) {
				requetePart1Complete = requetePart1Complete + ", company_id";
				requetePart2Complete = requetePart2Complete + ", " + idConstructeur;
			}
			if(machine.getDateSortie() != null) {
				requetePart1Complete = requetePart1Complete + ", introduced";
				requetePart2Complete = requetePart2Complete + ", " + Date.valueOf(machine.getDateSortie()).toString();
			}
			if(machine.getDateRetrait() != null) {
				requetePart1Complete = requetePart1Complete + ", discontinued";
				requetePart2Complete = requetePart2Complete + ", " + Date.valueOf(machine.getDateRetrait()).toString();
			}
			stmt3.executeUpdate(requetePart1Complete+requetePart2Complete+")");
			
		}
		catch (SQLException e) {
			
		}
		con.close();

				
	}
}
