package admin;

import common.DBConnection;
import common.commonMethods;
import common.query;

import java.util.*;
import java.sql.ResultSet;


public class floatclass{

	public static float Create(String cur_col_name, boolean unique){
		try{
			Scanner input = new Scanner(System.in);
			String s; 
			float fl = 0, flag = 1;
			s = input.nextLine();
			while(flag == 1){
				while(!commonMethods.checkIsFloat(s)){
					System.out.print("Enter only Float Values: ");
					s = input.nextLine();
				}
				fl = Float.parseFloat(s);
				if(unique){
					ResultSet fl_dup = query.float_column_select(cur_col_name, fl);
					if(fl_dup.next()){
						System.out.print("Entered " + cur_col_name + " is already Present. Re-enter a "
										+ "unique " + cur_col_name + ": ");
						s = input.nextLine();
						continue;
					}
				}
				flag = 0;
			}
			return fl;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return 0;
	}

}