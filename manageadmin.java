package admin;

import common.query;
import java.sql.ResultSet;
import java.util.*;

public class manageadmin{

	public static void makeadmin() throws Exception{
		Scanner input = new Scanner(System.in);
		ResultSet res = null;
		System.out.print("Enter an User to Make As Admin: ");
		String user = "";
		user = input.nextLine();
		int flag = 1;
		while(flag == 1){
			res = query.user_id_select(user, "");
			if(res.next()){
				if(res.getBoolean(3)){
					System.out.println("*** Entered User is Already an Admin ***");
					return;
				}
				else{
					System.out.print("Do you want to make this User As Admin (Y/N): ");
					String con; con = input.nextLine();
					while(!con.equalsIgnoreCase("Y") && !con.equalsIgnoreCase("N")){
						System.out.print("Enter only 'Y' or 'N': ");
						con = input.nextLine();
					}
					if(con.equalsIgnoreCase("Y")){
						query.makeadmin(user);
						System.out.println("*** User is Admin now ***");
					}
					else{
						System.out.println("*** Aborted ***");
						return;
					}
				}
				flag = 0;
			}
			else{
				System.out.print("No User Found. Re-enter the Username: ");
				user = input.nextLine();
				res = null;
			}
		}
	}


	public static void removeadmin() throws Exception{
		Scanner input = new Scanner(System.in);
		ResultSet res = null, main_admin = null;
		System.out.print("Enter an User to Remove From Admin: ");
		String user = "";
		user = input.nextLine();
		int flag = 1;
		while(flag == 1){
			res = query.user_id_select(user, "");
			main_admin = query.user_id_select("", "");
			main_admin.next();
			if(res.next()){
				if(res.getString(1).equals(main_admin.getString(1))){
					System.out.println("*** Entered Admin Cannot Be made as an User ***");
					return;
				}
				if(!res.getBoolean(3)){
					System.out.println("*** Entered User is Not an Admin ***");
					return;
				}
				else{
					System.out.print("Do you want to make this Admin As user (Y/N): ");
					String con; con = input.nextLine();
					while(!con.equalsIgnoreCase("Y") && !con.equalsIgnoreCase("N")){
						System.out.print("Enter only 'Y' or 'N': ");
						con = input.nextLine();
					}
					if(con.equalsIgnoreCase("Y")){
						query.removeadmin(user);
						System.out.println("*** Admin is User now ***");
					}
					else{
						System.out.println("*** Aborted ***");
						return;
					}
				}
				flag = 0;
			}
			else{
				System.out.print("No User Found. Re-enter the Username: ");
				user = input.nextLine();
				res = null;
			}
		}
	}

}