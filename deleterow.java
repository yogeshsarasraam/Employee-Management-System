package admin;

import common.DBConnection;
import common.commonMethods;
import common.query;

import java.util.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class deleterow{

	public static void Delete(){
		try{
			Scanner input = new Scanner(System.in);
			ResultSet res = query.select();
			ResultSetMetaData rsmd = res.getMetaData();
			int no_col = rsmd.getColumnCount();
			System.out.print("Enter the Emp_ID to be Deleted: ");
			String s; s = input.nextLine();
			while(!commonMethods.checkIsInteger(s)){
					System.out.print("Enter only Integer Values: ");
					s = input.nextLine();
			}
			int id = Integer.parseInt(s);
			while(res.next()){
				int cur_id = res.getInt(1);
				if(id == cur_id){
					for(int i=1; i<=no_col; i++){
						System.out.print(rsmd.getColumnName(i) + "	| ");
					}
					System.out.println();
					for(int i=1; i<=no_col; i++){
						int cur_col_type = rsmd.getColumnType(i);
						switch(cur_col_type){
							case 4: System.out.print(res.getInt(i)); break;
							case 6: System.out.print(res.getFloat(i)); break;
							case 8: System.out.print(res.getDouble(i)); break;
							case 16: System.out.print(res.getBoolean(i)); break;
							default: System.out.print(res.getString(i)); break;
						}
						System.out.print("	| ");
					}
					System.out.println("\n");
					System.out.print("Are you sure you want to delete this Emp_ID (Y/N): ");
					String con; con = input.nextLine();
					while(!con.equalsIgnoreCase("Y") && !con.equalsIgnoreCase("N")){
						System.out.print("Enter only 'Y' or 'N': ");
						con = input.nextLine();
					}
					if(con.equalsIgnoreCase("Y")){
						query.deleterow(id);
						System.out.println("*** Employee Details Deleted Successfully ***");
					}
					else
						System.out.println("*** Deletion Aborted ***");
					return;
				}
			}
			System.out.println("Entered Employee ID is not found.");
			return;
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}