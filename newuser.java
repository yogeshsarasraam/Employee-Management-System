package admin;

import common.query;
import java.util.*;
import java.sql.ResultSet;

public class newuser{

	public static void create_user() throws Exception{
		Scanner input = new Scanner(System.in);
		System.out.println("*** Enter Details for New User Creation ***");
		String name = "", pass = "", admin = "";
		//boolean adm = false;
		System.out.print("Username: ");
		int flag = 1;
		while(flag == 1){
			name = input.nextLine();
			ResultSet dup = query.user_id_select(name, "");
			if(dup.next())
				System.out.print("Entered Username is already Present. Re-enter an unique Username: ");
			else
				flag = 0;
		}
		System.out.print("Password: ");
		pass = input.nextLine();
		/*System.out.print("Whether the User is Admin or Not (Y/N): ");
		admin = input.nextLine();
		while(!admin.equalsIgnoreCase("Y") && !admin.equalsIgnoreCase("N")){
				System.out.print("Enter only 'Y' or 'N': ");
				admin = input.nextLine();
		}
		if(admin.equalsIgnoreCase("Y"))
			adm = true;*/
		//query.new_user(name, pass, adm);
		query.new_user(name, pass, false);
		System.out.println("*** User Added Successfully ***");
		return;
	}

	public static void delete_user() throws Exception{
		Scanner input = new Scanner(System.in);
		String name = "";
		System.out.print("Enter the Username to be Deleted: ");
		name = input.nextLine();
		ResultSet res = query.user_id_select(name, "");
		if(!res.next()){
			System.out.println("*** No User Found ***");
			return;
		}
		ResultSet admin = query.user_id_select("", "");
		if(admin.next() && admin.getString(1).equals(name)){
			System.out.println("*** User Cannot be Deleted ***");
			return;
		}
		System.out.print("Are you sure you want to delete this User (Y/N): ");
		String con; con = input.nextLine();
		while(!con.equalsIgnoreCase("Y") && !con.equalsIgnoreCase("N")){
			System.out.print("Enter only 'Y' or 'N': ");
			con = input.nextLine();
		}
		if(con.equalsIgnoreCase("Y")){
			query.del_user(name);
			System.out.println("*** User Deleted Successfully ***");
		}
		else
			System.out.println("*** Deletion Aborted ***");
	}

}