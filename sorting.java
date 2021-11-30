package users;

import common.*;
import java.util.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class sorting{

	public static void Sort(){
		try{
			Scanner input = new Scanner(System.in);
			ResultSet col = query.table_columns();

			System.out.println("Enter the Column to be Sorted: ");
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
			while(col.next()){
				i++;
				if(i == pos){
					col_name = col.getString("COLUMN_NAME");
				}
			}

			int flag = 1, order = 0; String s = "";
			while(flag == 1){
				System.out.print("Enter '1' for Ascending Order or '2' for Descending Order: ");
				s = input.nextLine();
				if(commonMethods.checkIsInteger(s)){
					if(s.equals("1")){
						order = 1;
						break;
					}
					else if(s.equals("2")){
						order = 2;
						break;
					}
				}
			}

			ResultSet res = null;
			if(order == 1)
				res = query.sorting(col_name, 1);
			else if(order == 2)
				res = query.sorting(col_name, 2);

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