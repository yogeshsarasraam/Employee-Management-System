package users;

import common.*;
import java.util.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class list{

	public static void List(){
		try{
			ResultSet res = query.select();
			ResultSetMetaData rsmd = res.getMetaData();
			int no_col = rsmd.getColumnCount();
			for(int i=1; i<=no_col; i++){
				System.out.print(rsmd.getColumnName(i) + "	|	");
			}
			System.out.println();
			while(res.next()){
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
				System.out.println();
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return;
	}

}