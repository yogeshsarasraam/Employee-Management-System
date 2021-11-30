package admin;

import common.DBConnection;
import common.commonMethods;
import common.query;

import java.util.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class update{

	public static void Update(){
		try{
			ResultSet col = query.table_columns();
			Set<String> hash_set = commonMethods.unique();
			Scanner input = new Scanner(System.in);
			String s=""; int flag = 1, id = 0;
			System.out.print("Enter an Employee ID to Updated: ");
			while(flag==1){
				s = input.nextLine();
				while(!commonMethods.checkIsInteger(s)){
					System.out.print("Enter only a valid Employee ID: ");
					s = input.nextLine();
				}
				id = Integer.parseInt(s);
				ResultSet res = query.id_select(id);
				if(!res.next()){
					System.out.print("Entered Employee ID is not found. Re-enter: ");
					continue;
				}
				flag = 0;
			}

			System.out.println("Enter the Column to be Updated:\n\tAvailable Columns:");
			int i=0;
			while(col.next()){
				i++;
				System.out.println("\t\t" + i + ". " + col.getString("COLUMN_NAME"));
			}
			col.beforeFirst();
			String position = input.nextLine();
			int pos = 0;
			while(!commonMethods.checkIsInteger(position) || ((pos = Integer.parseInt(position))<1 || pos>(i+1))){
				System.out.print("Enter only above mentiond Columns: ");
				position = input.nextLine();
			}

			System.out.print("Enter the New Value to be Update: ");
			int integer = 0; double dou = 0; float fl = 0; double d = 0;
			i = 0;
			while(col.next()){
				String cur_col_name = col.getString("COLUMN_NAME");
				boolean isunique = false;
				if(hash_set.contains(cur_col_name))
					isunique = true;
				i++;
				if(i == pos){
					int datatype = col.getInt("DATA_TYPE");
					switch(datatype){
						case 4: {
							integer = integerclass.Create(cur_col_name, isunique);
							query.int_update(cur_col_name, integer, id);
							break;
						}

						case 6: {
							fl = floatclass.Create(cur_col_name, isunique);
							query.float_update(cur_col_name, fl, id);
							break;
						}

						case 8: {
							dou = doubleclass.Create(cur_col_name, isunique);
							query.double_update(cur_col_name, dou, id);
							break;
						}

						case 12: {
							s = stringclass.Create(cur_col_name, isunique);
							query.string_update(cur_col_name, s, id);
							break;
						}

						default: {
							s = input.nextLine();
							while(!s.equalsIgnoreCase("Y") && !s.equalsIgnoreCase("N")){
								System.out.print("Enter only 'Y' or 'N': ");
								s = input.nextLine();
							}
							query.bool_update(cur_col_name, s, id);
							break;
						}
					}
					break;
				}
			}
			System.out.println("*** Updated Succesfully ***");
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return;
	}

}