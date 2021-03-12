package src.main.java.com.excilys.formation.mapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import src.main.java.com.excilys.formation.dao.AccessDatabase;
import src.main.java.com.excilys.formation.model.Computer;

public class UpdateDatabase {
	
	public static final String REQUETE_UPDATE = "UPDATE computer SET ";
	
	public static final String REQUETE_ID_CONSTRUCTEUR = "SELECT id FROM company WHERE name = \"";
	
	public static final String REQUETE_NOMBRE_ORDINATEURS = "SELECT id FROM computer WHERE name = \"";
	
	public static final String REQUETE_UPDATE_FIN = " WHERE id = ";
	
	public static void modifDB(Computer machine, Computer machineModif) throws ClassNotFoundException, SQLException {
		
		Connection con = AccessDatabase.getInstance(); 
		String requeteFinal = new String();
		
		if (!machine.getCompany().equals(machineModif.getCompany())) {
			// 1ere partie : récupération de l'id de la compagnie
					
			Statement stmt = con.createStatement();
			ResultSet rsCompany = stmt.executeQuery(REQUETE_ID_CONSTRUCTEUR + machineModif.getCompany() + "\" ");
			
			if (rsCompany.next()) {
				requeteFinal = requeteFinal + "company_id=" + rsCompany.getInt(1) + " ";
			}
		}
		if (machineModif.getDateSortie() != null) { // && machine.getDateSortie()!=null) { //&& !machine.getDateSortie().equals(machineModif.getDateSortie())) {
			requeteFinal = requeteFinal + "introduced=\"" + machineModif.getDateSortie() + "\" ";
		}
		if (machineModif.getDateRetrait() != null) { //  && !machine.getDateRetrait().equals(machineModif.getDateRetrait())) {
			requeteFinal = requeteFinal + "discontinued=\"" + machineModif.getDateRetrait() + "\"";
		}
		
		try {
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate(REQUETE_UPDATE + requeteFinal.trim().replace(" ", ",") + REQUETE_UPDATE_FIN + machine.getId());
		} catch (SQLException e) {
			
		}
		con.close();
	}
}
