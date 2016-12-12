package fonctionDERBY2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FonctionchangeD {
	
	public static double changeD(double prix, String devise) throws SQLException {
		Connection conn =  DriverManager.getConnection("jdbc:default:connection");
		Statement s1 = conn.createStatement();
		ResultSet rs = s1.executeQuery("select taux from DEVISE where nom='"+devise+"'");
		rs.next();
		double taux = rs.getDouble(1);
		double resultat = prix*taux;
		return resultat;
	}
}