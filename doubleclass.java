package admin;

import common.DBConnection;
import common.commonMethods;
import common.query;

import java.util.*;
import java.sql.ResultSet;

public class doubleclass{

	public static double Create(String cur_col_name, boolean unique){
		try{
			Scanner input = new Scanner(System.in);
			String s; 
			double dou = 0, flag = 1;
			s = input.nextLine();
			while(flag == 1){
				while(!commonMethods.checkIsFloat(s)){
					System.out.print("Enter only Double Values: ");
					s = input.nextLine();
				}
				dou = Double.parseDouble(s);
				if(unique){
					ResultSet do_dup = query.double_column_select(cur_col_name, dou);
					if(do_dup.next()){
						System.out.print("Entered " + cur_col_name + " is already Present. Re-enter a "
										+ "unique " + cur_col_name + ": ");
						s = input.nextLine();
						continue;
					}
				}
				flag = 0;
			}
			return dou;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return 0;
	}

}