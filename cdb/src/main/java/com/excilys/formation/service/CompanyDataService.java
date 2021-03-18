package com.excilys.formation.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.excilys.formation.dao.AccessDatabase;
import com.excilys.formation.dao.CompanieInfoDao;
import com.excilys.formation.mapper.CompanyInfos;
import com.excilys.formation.model.Company;

public class CompanyDataService {
	public static List<Company> recupDataCompany() throws Exception {
		try (Connection con = AccessDatabase.getInstance();) {
			
			return CompanyInfos.companyInformationsMapper(CompanieInfoDao.companyInformations(con));
		
		} catch (SQLException e) {
			throw new Exception("Impossible de se connecter a la base de donnees");
		} 
	}
	public static Company recupDataCompanyId(String nomConstructeur) throws Exception {
		try (Connection con = AccessDatabase.getInstance();) {
			
			return CompanyInfos.companyInformationsMapperId(CompanieInfoDao.companyInformationsId(con, nomConstructeur));
		
		} catch (SQLException e) {
			throw new Exception("Impossible de se connecter a la base de donnees Id");
		} 
	}
}
