package common;

import java.util.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class commonMethods{

	public static Set<String> unique(){
		try{
			ResultSet unique = query.unique_columns();
			Set<String> hash_set = new HashSet<String>();
			while(unique.next()){
				hash_set.add(unique.getString("COLUMN_NAME"));
			}
			unique.close();
			return hash_set;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return new HashSet<String>();
	}

	public static boolean checkIsInteger(String s){
		for(int i=0; i<s.length(); i++){
			if(s.charAt(i)>='0' && s.charAt(i)<='9')
				continue;
			return false;
		}
		return true;
	}

	public static boolean checkIsFloat(String s){
		for(int i=0; i<s.length(); i++){
			if((s.charAt(i)>='0' && s.charAt(i)<='9') || s.charAt(i)=='.')
				continue;
			return false;
		}
		return true;
	}

	public static boolean isMobile(String s){
	    if(s.length() < 5)
	        return false;
	    for(int i=0; i<=s.length()-5; i++){
	        if((s.substring(i, i+5)).equalsIgnoreCase("phone")){
	            return true;
	        }
	    }
	    for(int i=0; i<=s.length()-6; i++){
	        if((s.substring(i, i+6)).equalsIgnoreCase("mobile")){
	            return true;
	        }
	    }
	    return false;
	}

	public static boolean ismail(String s){
	    if(s.length() < 4)
	        return false;
	    for(int i=0; i<=s.length()-4; i++){
	        if((s.substring(i, i+4)).equalsIgnoreCase("mail"))
	            return true;
	    }
	    return false;
	}

	public static boolean isgender(String s){
	    if(s.length() < 3)
	        return false;
	    for(int i=0; i<=s.length()-6; i++){
	        if((s.substring(i, i+6)).equalsIgnoreCase("gender"))
	            return true;
	    }
	    for(int i=0; i<=s.length()-3; i++){
	        if((s.substring(i, i+3)).equalsIgnoreCase("sex"))
	            return true;
	    }
	    return false;
	}

}