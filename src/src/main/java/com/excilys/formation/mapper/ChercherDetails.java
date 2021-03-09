package src.main.java.com.excilys.formation.mapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;

import src.main.java.com.excilys.formation.model.Computer;

public class ChercherDetails {
	public static void details(String s) throws ClassNotFoundException, SQLException{
		
		String requete = new String("select computer.name, introduced, discontinued, company.name from computer join company where company.id = computer.company_id and computer.name = '"+s+"'");
		//List<Computer> list = new ArrayList<Computer>();
		
		Connection con = AccessDatabase.getInstance();  
		Statement stmt=con.createStatement();
		ResultSet rs = stmt.executeQuery(requete);  
		
		while(rs.next())  
		{
			System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4));  
		}
		con.close();
	}
}
