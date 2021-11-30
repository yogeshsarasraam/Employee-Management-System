package admin;

import common.DBConnection;
import common.commonMethods;
import common.query;

import java.util.*;
import java.sql.ResultSet;


public class integerclass{

	public static int Create(String cur_col_name, boolean unique){
		try{
			Scanner input = new Scanner(System.in);
			String s; 
			int integer = 0, flag = 1;
			s = input.nextLine();
			while(flag == 1){
				while(!commonMethods.checkIsInteger(s)){
					System.out.print("Enter only Integer Values: ");
					s = input.nextLine();
				}
				integer = Integer.parseInt(s);
				if(unique){
					ResultSet int_dup = query.integer_column_select(cur_col_name, integer);
					if(int_dup.next()){
						System.out.print("Entered " + cur_col_name + " is already Present. Re-enter a "
										+ "unique " + cur_col_name + ": ");
						s = input.nextLine();
						continue;
					}
				}
				flag = 0;
			}
			return integer;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return 0;
	}

}