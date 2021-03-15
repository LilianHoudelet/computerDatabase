package main.java.com.excilys.formation.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.com.excilys.formation.model.Company;

public class CompanyInfos {
	
	public static List<Company> companyInformationsMapper(ResultSet companyInformations) throws SQLException {
		List<Company> list = new ArrayList<Company>();
		
		while (companyInformations.next()) {
			list.add(new Company(companyInformations.getInt(1), companyInformations.getString(2)));
		}			
		return list; 
	}
	
	public static Company companyInformationsMapperId(ResultSet companyInformations) throws Exception {
		if (companyInformations.next()) {
			return new Company(companyInformations.getInt(1), companyInformations.getString(2));	
		}	else {
			throw new Exception ("Pas de donnes dans la base");
		}
	}
}