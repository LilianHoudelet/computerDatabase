package com.excilys.formation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DeleteCompanyDao {
	
	public final static String DELETE_COMPANY = "DELETE FROM company WHERE id = ?";
	
	public final static String DELETE_COMPUTER = "DELETE FROM computer WHERE company_id = ?";
	
	static Logger logger = org.slf4j.LoggerFactory.getLogger(DeleteCompanyDao.class);
	
	public static void deleteCompany(Connection con, int companyId) throws Exception {
		try {
			con.setAutoCommit(false);
			
			PreparedStatement stmt = con.prepareStatement(DELETE_COMPUTER);
			stmt.setInt(1, companyId);
			stmt.executeUpdate();
						
			PreparedStatement stmt2 = con.prepareStatement(DELETE_COMPANY);
			stmt2.setInt(1, companyId);
			int nbLigneSuppr = stmt2.executeUpdate();
			
			if (nbLigneSuppr < 1) {
				throw new Exception("La companie avec l'id "+companyId+ " n'est pas dans la base");
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
		
		logger.debug("Suppression de l'element id et des computer associes a l'id " + companyId);
		
	}
}
