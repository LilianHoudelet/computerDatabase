package com.excilys.formation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;

public class DeleteCompanyDao {
	
	public final static String DELETE_COMPANY = "DELETE FROM company WHERE id = ?";
	
	public final static String DELETE_COMPUTER = "DELETE FROM computer WHERE company_id = ?";
	
	static Logger logger = org.slf4j.LoggerFactory.getLogger(DeleteCompanyDao.class);
	
	public static void deleteCompany(Connection con, int id) throws Exception {
		try {
			con.setAutoCommit(false);
			
			PreparedStatement stmt = con.prepareStatement(DELETE_COMPUTER);
			stmt.setInt(1, id);
			stmt.executeUpdate();
						
			PreparedStatement stmt2 = con.prepareStatement(DELETE_COMPANY);
			stmt2.setInt(1, id);
			int nbLigneSuppr = stmt2.executeUpdate();
			
			if (nbLigneSuppr < 1) {
				throw new Exception("La companie avec l'id "+id+ " n'est pas dans la base");
			}
			
			con.commit();
		} catch (SQLException e) {
			try {
				if (con != null) {
					con.rollback();
				}
			} catch (SQLException e1) {
				
			}
		}
		
		logger.debug("Suppression de l'element id et des computer associes a l'id " + id);
		
	}
}
