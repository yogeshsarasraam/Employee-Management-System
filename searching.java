package users;

import common.*;
import java.util.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class searching{

	public static void Search(){
		try{
			Scanner input = new Scanner(System.in);
			ResultSet col = query.table_columns();
			ResultSet res = null;

			System.out.println("Enter the Column to be Searched: ");
			int i=0;
			while(col.next()){
				i++;
				System.out.println("\t" + i + ". " + col.getString("COLUMN_NAME"));
			}
			col.beforeFirst();
			String col_name = input.nextLine();
			int pos = 0;
			while(!commonMethods.checkIsInteger(col_name) || ((pos = Integer.parseInt(col_name))<1 || pos>(i+1))){
				System.out.print("Enter only above mentiond Columns: ");
				col_name = input.nextLine();
			}
			i = 0;
			int datatype = 0;
			while(col.next()){
				i++;
				if(i == pos){
					col_name = col.getString("COLUMN_NAME");
					datatype = col.getInt("DATA_TYPE");
				}
			}
			
			String option = ""; int op = 0;
			String value = "";
			if(datatype == 4 || datatype == 6 || datatype == 8){
				System.out.println("Enter the Search Option:\n\tAvailable Search Options:\n\t\t1. Greater Than\n\t\t2. Less Than\n"
						+ "\t\t3. Equals\n\t\t4. Greater Than and Equals\n\t\t5. Less Than and Equals");
				option = input.nextLine();
				while(!commonMethods.checkIsInteger(option) || ((op = Integer.parseInt(option))<1 || op>(i+1))){
					System.out.print("Enter only above mentiond Search Option: ");
					option = input.nextLine();
				}
				System.out.print("Enter the value to be Searched: ");
				int int_val = 0; float fl_val = 0; double do_val = 0;
				value = input.nextLine();
				switch(datatype){
					case 4: {
						while(!commonMethods.checkIsInteger(value)){
							System.out.print("Enter only Integer Values: ");
							value = input.nextLine();
						}
						int_val = Integer.parseInt(value);
						break;
					}
					case 6: {
						while(!commonMethods.checkIsFloat(value)){
							System.out.print("Enter only Float Values: ");
							value = input.nextLine();
						}
						fl_val = Float.parseFloat(value);
						break;
					}
					case 8: {
						while(!commonMethods.checkIsFloat(value)){
							System.out.print("Enter only Double Values: ");
							value = input.nextLine();
						}
						do_val = Double.parseDouble(value);
						break;
					}
				}
				switch(op){
					case 1: {
						if(datatype == 4)
							res = query.integer_search(col_name, int_val, 1);
						else if(datatype == 6)
							res = query.float_search(col_name, fl_val, 1);
						else if(datatype == 8)
							res = query.double_search(col_name, do_val, 1);
						break;
					}
					case 2: {
						if(datatype == 4)
							res = query.integer_search(col_name, int_val, 2);
						else if(datatype == 6)
							res = query.float_search(col_name, fl_val, 2);
						else if(datatype == 8)
							res = query.double_search(col_name, do_val, 2);
						break;
					}
					case 3: {
						if(datatype == 4)
							res = query.integer_search(col_name, int_val, 3);
						else if(datatype == 6)
							res = query.float_search(col_name, fl_val, 3);
						else if(datatype == 8)
							res = query.double_search(col_name, do_val, 3);
						break;
					}
					case 4: {
						if(datatype == 4)
							res = query.integer_search(col_name, int_val, 4);
						else if(datatype == 6)
							res = query.float_search(col_name, fl_val, 4);
						else if(datatype == 8)
							res = query.double_search(col_name, do_val, 4);
						break;
					}
					case 5: {
						if(datatype == 4)
							res = query.integer_search(col_name, int_val, 5);
						else if(datatype == 6)
							res = query.float_search(col_name, fl_val, 5);
						else if(datatype == 8)
							res = query.double_search(col_name, do_val, 5);
						break;
					}
				}
			}
			
			else if(datatype == 12){
				System.out.println("Enter the Search Option:\n\tAvailable Search Options:\n\t\t1. Starts With\n\t\t2. Ends With\n"
						+ "\t\t3. Contains\n\t\t4. Not Contains\n\t\t5. Equals");
				option = input.nextLine();
				while(!commonMethods.checkIsInteger(option) || ((op = Integer.parseInt(option))<1 || op>(i+1))){
					System.out.print("Enter only above mentiond Search Option: ");
					option = input.nextLine();
				}
				System.out.print("Enter the value to be Searched: ");
				value = input.nextLine();
				switch(op){
					case 1: {
						res = query.string_search(col_name, value, 1);
						break;
					}
					case 2: {
						res = query.string_search(col_name, value, 2);
						break;
					}
					case 3: {
						res = query.string_search(col_name, value, 3);
						break;
					}
					case 4: {
						res = query.string_search(col_name, value, 4);
						break;
					}
					case 5: {
						res = query.string_search(col_name, value, 5);
						break;
					}
				}
			}
			
			else{
				System.out.print("Enter 'Y' for True Search or 'N' for False Search: ");
				value = input.nextLine();
				while(!value.equalsIgnoreCase("Y") && !value.equalsIgnoreCase("N")){
					System.out.print("Enter 'Y' for True Search or 'N' for False Search: ");
					value = input.nextLine();
				}
				if(value.equalsIgnoreCase("Y"))
					res = query.bool_search(col_name, true);
				else
					res = query.bool_search(col_name, false);
			}
			
			if(!res.next()){
				System.out.println("*** No Records Found ***");
				return;
			}
			res.beforeFirst();
			ResultSetMetaData rsmd = res.getMetaData();
			int no_col = rsmd.getColumnCount();
			for(i=1; i<=no_col; i++){
				System.out.print(rsmd.getColumnName(i) + "	|	");
			}
			System.out.println();
			while(res.next()){
				for(i=1; i<=no_col; i++){
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
				System.out.println();
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return;
	}

}