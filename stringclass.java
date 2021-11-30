package admin;

import common.DBConnection;
import common.commonMethods;
import common.query;

import java.util.*;
import java.sql.ResultSet;

public class stringclass{

	public static String Create(String cur_col_name, boolean unique){
		try{
			Scanner input = new Scanner(System.in);
			ResultSet s_dup = null;

			String s = ""; int flag = 1;
			if(commonMethods.isMobile(cur_col_name)){
				while(flag==1){
                    s = input.nextLine();
                    int count = 0, first = 0;
                    if(s.charAt(0)<'6')
                        first = 1;
                    for(int j=0; j<s.length(); j++){
                        if((s.charAt(j)>='1' && s.charAt(j)<='9') || s.charAt(j) == '0')
                            count++;
                    }
                    if(count == 10 && first == 0)
                        flag = 0;
                    if(flag==1){
                        System.out.print("Entered " + cur_col_name + " is not valid. Re-enter a valid " + cur_col_name + ": ");
                        continue;
                    }
                    if(unique){
						s_dup = query.string_column_select(cur_col_name, s);
						if(s_dup.next()){
							System.out.print("Entered " + cur_col_name + " is already Present. Re-enter a "
										+ "unique " + cur_col_name + ": ");
							flag = 1;
							continue;
						}
						s_dup.close();
                    }
                    flag = 0;
                }
                return s;
			}

			if(commonMethods.ismail(cur_col_name)){
                while(flag==1){
                   	s = input.nextLine();
                    int a = 0, dot = 0;
                    for(int j=s.length()-1; j>=0; j--){
                        if(s.charAt(j) == '.' && a == 1)
                            break;
                        else if(s.charAt(j) == '@' && dot == 0)
                            break;
                        if(s.charAt(j) == '.' && a == 0)
                            dot++;
                        if(s.charAt(j) == '@' && dot == 1)
                            a++;
                        if(a==1 && dot==1)
                            break;
                    }
                    if(a==1 && dot==1)
                        flag = 0;
                    if(flag==1){
                        System.out.print("Entered " + cur_col_name + " is not valid. Re-enter a valid " + cur_col_name + ": ");
                        continue;
                    }
                    if(unique){
                    	s_dup = query.string_column_select(cur_col_name, s);
                    	if(s_dup.next()){
                    		System.out.print("Entered " + cur_col_name + " is already Present. Re-enter a "
										+ "unique " + cur_col_name + ": ");
							flag = 1;
							continue;
                    	}
                    	s_dup.close();
                    }
                    flag = 0;
                }
                return s;
            }

            if(commonMethods.isgender(cur_col_name)){
                while(flag==1){
                    System.out.print(" Enter '1' for Male, '2' for Female, '3' for Others: ");
                    s = input.nextLine();
                    if(s.equals("1")){
                        s = "Male";
                        return s;
                    }
                    
                    else if(s.equals("2")){
                        s = "Female";
                        return s;
                    }
                    
                    else if(s.equals("3")){
                    	s = "Others";
                    	return s;
                    }
                }
            }

            while(flag==1){
            	s = input.nextLine();
            	if(unique){
            		s_dup = query.string_column_select(cur_col_name, s);
                    if(s_dup.next()){
                    	System.out.print("Entered " + cur_col_name + " is already Present. Re-enter a "
								+ "unique " + cur_col_name + ": ");
						flag = 1;
						continue;
                    }
                    s_dup.close();
                }
                flag = 0;
            }
           return s; 
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return "";
	}

}