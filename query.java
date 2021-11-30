package common;

import java.util.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

public class query{

	static DBConnection dbconnection = new DBConnection();
	static Connection connection = dbconnection.getConnection(); 

	public static void create_emp_table(String name){
		try{
			Statement statement = connection.createStatement();
			statement.executeUpdate("CREATE TABLE " + name + " (emp_id INT NOT NULL PRIMARY KEY)");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void create_users_table() throws Exception{
		Statement statement = connection.createStatement();
		statement.executeUpdate("CREATE TABLE users (name VARCHAR PRIMARY KEY NOT NULL, password VARCHAR, admin BOOLEAN );");
		return;
	}

	public static ResultSet user_id_select(String user, String pass) throws Exception{
		Statement statement = connection.createStatement();
		ResultSet res = null;
		if(pass.length()==0){
			if(user.length()==0)
				res = statement.executeQuery("SELECT * FROM users");
			else
				res = statement.executeQuery("SELECT * FROM users WHERE name = '" + user + "'");
		}
		else
			res = statement.executeQuery("SELECT * FROM users WHERE name = '" + user + "' AND password = '" + pass + "'");
		return res;
	}

	public static void new_user(String user, String pass, boolean admin) throws Exception{
		Statement statement = connection.createStatement();
		if(admin)
			statement.executeUpdate("INSERT INTO users (name, password, admin) VALUES ('" + user + "', '" + pass + "', true);");
		else
			statement.executeUpdate("INSERT INTO users (name, password, admin) VALUES ('" + user + "', '" + pass + "', false);");
		return;
	}

	public static void del_user(String user) throws Exception{
		Statement statement = connection.createStatement();
		statement.executeUpdate("DELETE FROM users WHERE name = '" + user + "'");
		return;
	}

	public static void makeadmin(String user) throws Exception{
		Statement statement = connection.createStatement();
		statement.executeUpdate("UPDATE users SET admin = true WHERE name = '" + user + "'");
		return;
	}

	public static void removeadmin(String user) throws Exception{
		Statement statement = connection.createStatement();
		statement.executeUpdate("UPDATE users SET admin = false WHERE name = '" + user + "'");
		return;
	}

	public static ResultSet tables(String name) throws Exception{
		DatabaseMetaData dbm = connection.getMetaData();
		ResultSet res = dbm.getTables(null, null, name, new String[] {"TABLE"});
		return res;
	}

	public static ResultSet table_columns(){
		try{
			DatabaseMetaData dbm = connection.getMetaData();
			ResultSet col = dbm.getColumns(null, null, "emp", null);
			return col;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public static ResultSet unique_columns() throws Exception{
		Statement statement = connection.createStatement();
		DatabaseMetaData dbm = connection.getMetaData();
		ResultSet res = dbm.getIndexInfo(null, null, "emp", true, true);
		return res;
	}

	public static ResultSet select(){
		try{
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet values = statement.executeQuery("SELECT * FROM emp");
			return values;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public static ResultSet id_select(int id){
		try{
			Statement statement = connection.createStatement();
		 	ResultSet res = statement.executeQuery("SELECT * FROM emp WHERE emp_id = " + id);
		 	return res;
		 }
		 catch (Exception e){
		 	e.printStackTrace();
		 }
		 return null;
	}

	public static ResultSet integer_column_select(String col_name, int integer) throws Exception{
		Statement st = connection.createStatement();
		ResultSet res = st.executeQuery("SELECT * FROM emp WHERE " + col_name + " = " + integer);
		return res;
	}

	public static ResultSet float_column_select(String col_name, float fl) throws Exception{
		Statement st = connection.createStatement();
		ResultSet res = st.executeQuery("SELECT * FROM emp WHERE " + col_name + " = " + fl);
		return res;
	}

	public static ResultSet double_column_select(String col_name, double dou) throws Exception{
		Statement st = connection.createStatement();
		ResultSet res = st.executeQuery("SELECT * FROM emp WHERE " + col_name + " = " + dou);
		return res;
	}

	public static ResultSet string_column_select(String col_name, String s) throws Exception{
		Statement st = connection.createStatement();
		ResultSet res = st.executeQuery("SELECT * FROM emp WHERE " + col_name + " = '" + s + "'");
		return res;
	}

	public static void deleterow(int id){
		try{
			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM emp WHERE emp_id = " + id);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return;
	}

	public static void int_update(String col_name, int integer, int id){
		try{
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE emp SET " + col_name + " = " + integer + " WHERE emp_id = " + id);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return;
	}

	public static void float_update(String col_name, float fl, int id) throws Exception{
		try{
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE emp SET " + col_name + " = " + fl + " WHERE emp_id = " + id);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return;
	}

	public static void double_update(String col_name, double dou, int id) throws Exception{
		try{
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE emp SET " + col_name + " = " + dou + " WHERE emp_id = " + id);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return;
	}

	public static void string_update(String col_name, String s, int id) throws Exception{
		try{
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE emp SET " + col_name + " = '" + s + "' WHERE emp_id = " + id);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return;
	}

	public static void bool_update(String col_name, String s, int id) throws Exception{
		try{
			Statement statement = connection.createStatement();
			if(s.equalsIgnoreCase("Y"))
				statement.executeUpdate("UPDATE emp SET " + col_name + " = true WHERE emp_id = " + id);
			else
				statement.executeUpdate("UPDATE emp SET " + col_name + " = false WHERE emp_id = " + id);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return;
	}

	public static void add_column(String col_name, String datatype, boolean duplicate){
		try{
			Statement statement = connection.createStatement();
			statement.executeUpdate("ALTER TABLE emp ADD COLUMN " + col_name + " " + datatype + ";");
			if(!duplicate){
				statement.executeUpdate("ALTER TABLE emp ADD UNIQUE (" + col_name + ");");
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return;
	}

	public static void delete_column(String col_name) throws Exception{
		Statement statement = connection.createStatement();
		statement.executeUpdate("ALTER TABLE emp DROP COLUMN " + col_name);
		return;
	}

	public static ResultSet integer_search(String col_name, int integer, int search_option) throws Exception{
		Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet res = null;
		switch(search_option){
			case 1: res = st.executeQuery("SELECT * FROM emp WHERE " + col_name + " > " + integer); break;
			case 2: res = st.executeQuery("SELECT * FROM emp WHERE " + col_name + " < " + integer); break;
			case 3: res = st.executeQuery("SELECT * FROM emp WHERE " + col_name + " = " + integer); break;
			case 4: res = st.executeQuery("SELECT * FROM emp WHERE " + col_name + " >= " + integer); break;
			case 5: res = st.executeQuery("SELECT * FROM emp WHERE " + col_name + " <= " + integer); break;
		}
		return res;
	}

	public static ResultSet float_search(String col_name, float fl, int search_option) throws Exception{
		Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet res = null;
		switch(search_option){
			case 1: res = st.executeQuery("SELECT * FROM emp WHERE " + col_name + " > " + fl); break;
			case 2: res = st.executeQuery("SELECT * FROM emp WHERE " + col_name + " < " + fl); break;
			case 3: res = st.executeQuery("SELECT * FROM emp WHERE " + col_name + " = " + fl); break;
			case 4: res = st.executeQuery("SELECT * FROM emp WHERE " + col_name + " >= " + fl); break;
			case 5: res = st.executeQuery("SELECT * FROM emp WHERE " + col_name + " <= " + fl); break;
		}
		return res;
	}

	public static ResultSet double_search(String col_name, double dou, int search_option) throws Exception{
		Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet res = null;
		switch(search_option){
			case 1: res = st.executeQuery("SELECT * FROM emp WHERE " + col_name + " > " + dou);  break;
			case 2: res = st.executeQuery("SELECT * FROM emp WHERE " + col_name + " < " + dou);break;
			case 3: res = st.executeQuery("SELECT * FROM emp WHERE " + col_name + " = " + dou); break;
			case 4: res = st.executeQuery("SELECT * FROM emp WHERE " + col_name + " >= " + dou); break;
			case 5: res = st.executeQuery("SELECT * FROM emp WHERE " + col_name + " <= " + dou); break;
		}
		return res;
	}

	public static ResultSet string_search(String col_name, String value, int search_option) throws Exception{
		Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet res = null;
		switch(search_option){
			case 1: res = st.executeQuery("SELECT * FROM emp WHERE LOWER(" + col_name + ") LIKE LOWER('" + value + "%')");break;
			case 2: res = st.executeQuery("SELECT * FROM emp WHERE LOWER(" + col_name + ") LIKE LOWER('%" + value + "')"); break;
			case 3: res = st.executeQuery("SELECT * FROM emp WHERE LOWER(" + col_name + ") LIKE LOWER('%" + value + "%')"); break;
			case 4: res = st.executeQuery("SELECT * FROM emp WHERE LOWER(" + col_name + ") NOT LIKE ('%" + value + "%')"); break;
			case 5: res = st.executeQuery("SELECT * FROM emp WHERE LOWER(" + col_name + ") = LOWER('" + value + "')"); break;
		}
		return res;
	}

	public static ResultSet bool_search(String col_name, boolean val) throws Exception{
		Statement st = connection.createStatement();
		ResultSet res = null;
		if(val)
			res = st.executeQuery("SELECT * FROM emp WHERE " + col_name + " = true");
		else
			res = st.executeQuery("SELECT * FROM emp WHERE " + col_name + " = false");
		return res;
	}

	public static ResultSet sorting(String col_name, int order) throws Exception{
		Statement st = connection.createStatement();
		ResultSet res = null;
		if(order == 1)
			res = st.executeQuery("SELECT * FROM emp ORDER BY " + col_name + " ASC");
		else
			res = st.executeQuery("SELECT * FROM emp ORDER BY " + col_name + " DESC");
		return res;
	}

}