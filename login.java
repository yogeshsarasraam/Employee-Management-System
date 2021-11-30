import common.DBConnection;
import common.commonMethods;
import common.query;

import java.util.*;
import java.sql.ResultSet;

public class login{

	public static void Login(){
		try{
			Scanner input = new Scanner(System.in);
			String user = "", pass = "";
			/*ResultSet table = query.tables("users");
			if(!table.next()){
				System.out.println("*** No Administrator Found. Create a New Administrator ***");
				System.out.print("Username: "); user = input.nextLine();
				System.out.print("Password: "); pass = input.nextLine();
				query.create_users_table();
				query.new_user(user, pass, true);
			}*/
			ResultSet res = null;
			ResultSet main_admin = null;
			System.out.println("*** Enter Login Details ***");
			int flag = 1;
			while(flag == 1){
				System.out.print("Username: "); user = input.nextLine();
				System.out.print("Password: "); pass = input.nextLine();
				res = query.user_id_select(user, pass);
				main_admin = query.user_id_select("", "");
				main_admin.next();
				if(!res.next())
					System.out.println("Entered Username and Password is in-correct.");
				else{
					if(res.getString(1).equals(main_admin.getString(1)))
						menu.mainadmin();
					else{
						boolean administrator = res.getBoolean(3);
						if(administrator)
							menu.admin();
						else
							menu.user();
					}
					flag = 0;
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}