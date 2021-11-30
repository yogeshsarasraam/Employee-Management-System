package admin;

import common.DBConnection;
import common.commonMethods;
import common.query;

import java.util.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class column{

	public static void addColumn(){
		try{
			Scanner input = new Scanner(System.in);
			ResultSet col = query.table_columns();
			int no_col = 0;
			while(col.next()){
				no_col++;
			}
			col.beforeFirst();
			String name, datatype,  dup;
			
			System.out.print("[1] Enter the Name of the Column: ");
			name = input.nextLine();
			
			System.out.println("[2] Enter the Datatype of the Column\n" + "\tAvailable Datatypes:\n" + "\t\t1. Integer\n"
				+ "\t\t2. Float\n" + "\t\t3. Double\n" + "\t\t4. Bool\n" + "\t\t5. String");
			datatype = input.nextLine();
			int data = 0;
			while(!commonMethods.checkIsInteger(datatype) || ((data = Integer.parseInt(datatype))<1 || data>5)){
				System.out.print("Enter only above 5 Datatypes: ");
				datatype = input.nextLine();
			}
			switch(data){
				case 1: datatype = "INT"; break;
				case 2: datatype = "FLOAT"; break;
				case 3: datatype = "DOUBLE PRECISION"; break;
				case 4: datatype = "BOOLEAN"; break;
				case 5: datatype = "VARCHAR"; break;
			}

			/*System.out.print("[3] Enter the postition of the New Column (Available Positions are from 2 to " + (no_col+1) + "): ");
			position = input.nextLine();
			int pos = 0;
			while(!commonMethods.checkIsInteger(position) || ((pos = Integer.parseInt(position))<2 || pos>(no_col+1))){
				System.out.print("Enter only from 2 to " + (no_col+1) + " postions: ");
				position = input.nextLine();
			}*/

			boolean duplicate = true;
			if(data != 4){
				System.out.print("[4] Whether Duplicates allowed in this Column (Y/N): ");
				dup = input.nextLine();
				while(!dup.equalsIgnoreCase("Y") && !dup.equalsIgnoreCase("N")){
					System.out.print("Enter only 'Y' or 'N': ");
					dup = input.nextLine();
				}
				if(dup.equalsIgnoreCase("N"))
					duplicate = false;
			}

			if(duplicate)
				query.add_column(name, datatype, true);
			else
				query.add_column(name, datatype, false);
			
			System.out.println("*** Column Added Succesfully ***");
			return;
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void deleteColumn(){
		try{
			Scanner input = new Scanner(System.in);
			ResultSet col = query.table_columns();
			System.out.println("Enter the Column No. to be Deleted:");
			int i=1;
			col.next();
			while(col.next()){
				i++;
				System.out.println("\t" + i + ". " + col.getString("COLUMN_NAME"));
			}
			if(i == 1){
				System.out.println("*** No Columns Available to be Deleted ***");
				return;
			}
			String op; int option;
			op = input.nextLine();
			while(!commonMethods.checkIsInteger(op) || ((option=Integer.parseInt(op))<2 || option>i)){
				System.out.print("Enter only from above mentioned columns: ");
				op = input.nextLine();
			}
			i = 1;
			col.beforeFirst();
			col.next();
			String col_name = "";
			while(col.next()){
				i++;
				if(i == option){
					col_name = col.getString("COLUMN_NAME");
					break;
				}
			}
			System.out.print("Are you sure you want to Delete Column " + col_name + " (Y/N): ");
			String con;
			con = input.nextLine();
			while(!con.equalsIgnoreCase("Y") && !con.equalsIgnoreCase("N")){
				System.out.print("Enter only 'Y' or 'N': ");
				con = input.nextLine();
			}
			if(con.equalsIgnoreCase("N")){
				System.out.println("*** Deletion Aborted ***");
				return;
			}
			query.delete_column(col_name);
			System.out.println("*** Column Deleted Succesfully ***");
			return;
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}