package admin;

import common.DBConnection;
import common.commonMethods;
import common.query;

import java.util.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DatabaseMetaData;

public class create{

	public static void Create(){
		try{
			System.out.println("*** Creation Starts ***");
			Scanner input = new Scanner(System.in);
			ResultSet values = query.select();
			ResultSetMetaData rsmd = values.getMetaData();
			int no_col = rsmd.getColumnCount();
			values.moveToInsertRow();
			Set<String> hash_set = commonMethods.unique();
			//System.out.println(hash_set);
			
			for(int i=1; i<=no_col; i++){
				int cur_col_type = rsmd.getColumnType(i);
				String cur_col_name = rsmd.getColumnName(i);
				boolean isunique = false;
				if(hash_set.contains(cur_col_name))
					isunique = true;
				int integer = 0; double dou = 0; float fl = 0; double d = 0; boolean bool = false; String s = "";
				System.out.print(rsmd.getColumnName(i) + ": ");
				switch(cur_col_type){
					case 4: {
						integer = integerclass.Create(cur_col_name, isunique);
						values.updateInt(i, integer);
						break;
					}

					case 6: {
						fl = floatclass.Create(cur_col_name, isunique);
						values.updateFloat(i, fl);
						break;
					}

					case 8: {
						dou = doubleclass.Create(cur_col_name, isunique);
						values.updateDouble(i, dou);
						break;
					}

					case 12: {
						s = stringclass.Create(cur_col_name, isunique);
						values.updateString(i, s);
						break;
					}

					default: {
						s = input.nextLine();
						while(!s.equalsIgnoreCase("Y") && !s.equalsIgnoreCase("N")){
							System.out.print("Enter only 'Y' or 'N': ");
							s = input.nextLine();
						}
						if(s.equalsIgnoreCase("Y"))
							bool = true;
						values.updateBoolean(i, bool);
						break;
					}
				}
			}
			values.insertRow();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("*** Creation Completed Successfully ***");
		return;
	}

}