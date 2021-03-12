package src.main.java.com.excilys.formation.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import src.main.java.com.excilys.formation.dao.AccessDatabase;
import src.main.java.com.excilys.formation.dao.CompanieInfoDao;
import src.main.java.com.excilys.formation.mapper.CompanyInfos;
import src.main.java.com.excilys.formation.model.Company;

public class CompanyDataService {
	public static List<Company> recupDataOrdi() throws Exception {
		try (Connection con = AccessDatabase.getInstance();) {
			
			return CompanyInfos.companyInformationsMapper(CompanieInfoDao.companyInformations(con));
		
		} catch (SQLException e) {
			throw new Exception("Impossible de se connecter a la base de donnees");
		} 
	}
}
