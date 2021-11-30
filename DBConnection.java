package common;

import java.util.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;


public class DBConnection{
	public static Connection getConnection(){
		Connection connection = null;
		try{
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/EMS", "postgres", "1234");
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return connection;
	}
	//public static void main(String[] args){}
}