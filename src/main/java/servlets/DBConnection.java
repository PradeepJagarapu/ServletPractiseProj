package servlets;

import java.sql.*;

public class DBConnection {
	static Connection con;
	public static Connection getCon() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb?useSSL=false","root","");
		
		return con;
	}
	
}
