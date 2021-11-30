import admin.create;
import admin.deleterow;
import admin.update;
import admin.integerclass;
import admin.floatclass;
import admin.doubleclass;
import admin.stringclass;
import admin.column;
import admin.newuser;
import admin.manageadmin;

import users.list;
import users.searching;
import users.sorting;

import common.commonMethods;

import java.util.*;

public class menu{

	public static void mainadmin() throws Exception{
		Scanner input = new Scanner(System.in);
		String con;
		do{
			System.out.println("1. Create New Record");
			System.out.println("2. Update Existing Record");
			System.out.println("3. Delete Record");
			System.out.println("4. List All Records");
			System.out.println("5. Add New Column");
			System.out.println("6. Delete Column");
			System.out.println("7. Search Record");
			System.out.println("8. Sort Record");
			System.out.println("9. Make Admin");
			System.out.println("10. Remove Admin");
			System.out.println("11. Create New User");
			System.out.println("12. Delete Existing User");
			System.out.println("0. Exit");
			System.out.print("Enter the Operation to perfrom: ");
			String op; int option = 0;
			op = input.nextLine();
			while(!commonMethods.checkIsInteger(op) || ((option=Integer.parseInt(op))<0 || option>12)){
				System.out.print("Enter only above 12 operations: ");
				op = input.nextLine();
			}
			switch(option){
				case 0: return;
				case 1: create.Create(); break;
				case 2: update.Update(); break;
				case 3: deleterow.Delete(); break;
				case 4: list.List(); break;
				case 5: column.addColumn(); break;
				case 6: column.deleteColumn(); break;
				case 7: searching.Search(); break;
				case 8: sorting.Sort(); break;
				case 9: manageadmin.makeadmin(); break;
				case 10: manageadmin.removeadmin(); break;
				case 11: newuser.create_user(); break;
				case 12: newuser.delete_user(); break;
			}
			System.out.print("Whether you want to continue the opeartions (Y/N): ");
			con = input.nextLine();
			while(!con.equalsIgnoreCase("Y") && !con.equalsIgnoreCase("N")){
				System.out.print("Enter only 'Y' or 'N': ");
				con = input.nextLine();
			}
		}
		while(con.equalsIgnoreCase("Y"));
	}


	public static void admin() throws Exception{
		Scanner input = new Scanner(System.in);
		String con;
		do{
			System.out.println("1. Create New Record");
			System.out.println("2. Update Existing Record");
			System.out.println("3. Delete Record");
			System.out.println("4. List All Records");
			System.out.println("5. Add New Column");
			System.out.println("6. Delete Column");
			System.out.println("7. Search Record");
			System.out.println("8. Sort Record");
			System.out.println("9. Delete Existing User");
			System.out.println("0. Exit");
			System.out.print("Enter the Operation to perfrom: ");
			String op; int option = 0;
			op = input.nextLine();
			while(!commonMethods.checkIsInteger(op) || ((option=Integer.parseInt(op))<0 || option>9)){
				System.out.print("Enter only above 9 operations: ");
				op = input.nextLine();
			}
			switch(option){
				case 0: return;
				case 1: create.Create(); break;
				case 2: update.Update(); break;
				case 3: deleterow.Delete(); break;
				case 4: list.List(); break;
				case 5: column.addColumn(); break;
				case 6: column.deleteColumn(); break;
				case 7: searching.Search(); break;
				case 8: sorting.Sort(); break;
				case 9: newuser.delete_user(); break;
			}
			System.out.print("Whether you want to continue the opeartions (Y/N): ");
			con = input.nextLine();
			while(!con.equalsIgnoreCase("Y") && !con.equalsIgnoreCase("N")){
				System.out.print("Enter only 'Y' or 'N': ");
				con = input.nextLine();
			}
		}
		while(con.equalsIgnoreCase("Y"));
	}


	public static void user(){
		Scanner input = new Scanner(System.in);
		String con;
		do{
			System.out.println("1. List All Records");
			System.out.println("2. Search Record");
			System.out.println("3. Sort Record");
			System.out.println("4. Exit");
			System.out.print("Enter the Operation to perfrom: ");
			String op; int option = 0;
			op = input.nextLine();
			while(!commonMethods.checkIsInteger(op) || ((option=Integer.parseInt(op))<1 || option>4)){
				System.out.print("Enter only above 4 operations: ");
				op = input.nextLine();
			}
			switch(option){
				case 1: list.List(); break;
				case 2: searching.Search(); break;
				case 3: sorting.Sort(); break;
				case 4: return;
			}
			System.out.print("Whether you want to continue the opeartions (Y/N): ");
			con = input.nextLine();
			while(!con.equalsIgnoreCase("Y") && !con.equalsIgnoreCase("N")){
				System.out.print("Enter only 'Y' or 'N': ");
				con = input.nextLine();
			}
		}
		while(con.equalsIgnoreCase("Y"));
	}

}