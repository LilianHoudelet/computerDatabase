package src.main.java.com.excilys.formation.mapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import src.main.java.com.excilys.formation.model.Company;

public class CompanyInfos {
	
	public static final String REQUETE = "SELECT id, name FROM company";
		
	public static List<Company> companyInformations() throws ClassNotFoundException, SQLException{
		List<Company> list = new ArrayList<Company>();
		try(Connection con = AccessDatabase.getInstance();){	
			Statement stmt=con.createStatement();
			ResultSet rs = stmt.executeQuery(REQUETE);  	
			while(rs.next())  
			{
				list.add(new Company(rs.getInt(1), rs.getString(2)));
			}
			con.close();
			return list; 
		}
	}
}


