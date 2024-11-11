package ecom;

import java.sql.*;

public  class DbUtil {
	public static String url ="jdbc:postgresql://localhost/ecommerceDb";
	public static String userName ="postgres";
	public static String passWord="28268452";
	public DbUtil() {
	}
	PreparedStatement dbConnection(String s ) throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		Connection con =DriverManager.getConnection(url, userName, passWord);
		PreparedStatement st=con.prepareStatement(s);
		return st;
		
	}
	
}
