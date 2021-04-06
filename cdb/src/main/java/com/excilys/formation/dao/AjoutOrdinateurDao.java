package com.excilys.formation.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.formation.model.Computer;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AjoutOrdinateurDao {

	static Logger logger = org.slf4j.LoggerFactory.getLogger(AjoutOrdinateurDao.class);

	public static final String REQUETE_AJOUTER_COMPLET = "INSERT INTO computer (id, name, introduced, discontinued, company_id) VALUES (?,?,?,?,?)";

	public void computerInformations(Connection con, Computer ordinateur)
			throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = con.prepareStatement(REQUETE_AJOUTER_COMPLET);
		stmt.setInt(1, ordinateur.getId());
		stmt.setString(2, ordinateur.getName());
		if (ordinateur.getDateSortie() != null) {
			stmt.setDate(3, Date.valueOf(ordinateur.getDateSortie()));
		} else {
			stmt.setDate(3, null);
		}
		if (ordinateur.getDateRetrait() != null) {

			stmt.setDate(4, Date.valueOf(ordinateur.getDateRetrait()));
		} else {
			stmt.setDate(4, null);
		}
		if (ordinateur.getCompany().getId() != 0) {
			stmt.setInt(5, ordinateur.getCompany().getId()); // ordinateur.getCompany().getId());
		} else {
			stmt.setNull(5, 0);
		}

		stmt.executeUpdate();
		
		logger.debug("Ajout d'un élément dans la base de données");
	}
}
