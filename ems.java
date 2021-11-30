import common.DBConnection;
import common.query;
import common.commonMethods;

import admin.newuser;

import java.util.*;
import java.sql.ResultSet;


public class ems{

	public static void main(String[] args){
		try{
			Scanner input = new Scanner(System.in);
			ResultSet table = query.tables("emp");
			if(!table.next())
				query.create_emp_table("emp");

			String user = "", pass = "";
			table = null;
			table = query.tables("users");
			if(!table.next()){
				System.out.println("*** No Administrator Found. Create a New Administrator ***");
				System.out.print("Username: "); user = input.nextLine();
				System.out.print("Password: "); pass = input.nextLine();
				query.create_users_table();
				query.new_user(user, pass, true);
			}

			System.out.println("\t\tWelcome to Employee Management System");
			System.out.print("Enter '1' for Login or '2' for New User Registeration: ");
			String op;
			op = input.nextLine();
			while(!op.equals("1") && !op.equals("2")){
				System.out.print("Enter only '1' for Login or '2' for New User Registeration: ");
				op = input.nextLine();
			}
			if(op.equals("1"))
				login.Login();
			else{
				newuser.create_user();
				System.out.print("Enter '1' for Login or '0' for Exit: ");
				op = input.nextLine();
				while(!op.equals("1") && !op.equals("0")){
					System.out.print("Enter only '1' for Login or '0' for Exit: ");
					op = input.nextLine();
				}
				if(op.equals("1"))
					login.Login();
				else
					return;
			}
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("Database Connection Failed!!!");
		}
	}

}