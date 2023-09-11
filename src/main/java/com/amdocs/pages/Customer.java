package com.amdocs.pages;

import java.sql.SQLException;
import java.util.Scanner;

import com.amdocs.DAO.Implementation.CustomerDAOimpl;
import com.amdocs.exceptions.InvalidDateFormatException;
import com.amdocs.exceptions.InvalidFirstNameException;
import com.amdocs.exceptions.InvalidNumberException;

public class Customer {

	// Create Customer SQL Table

	private static Scanner sc = new Scanner(System.in);
	static CustomerDAOimpl cDAO = new CustomerDAOimpl();

	static int i = 0;

	public static void customerMenu() throws SQLException {

		boolean x = true;

		try {
			while (x) {
			System.out.println("\nCustomer Main Menu");
			System.out.println("----------------");
			System.out.println("1. Register Customer \t 2. Modify customer details \t 3. Delete customer record \t 4. View single record \t 5. View all records \t 0. Exit");
			System.out.println("----------------");
			System.out.print("\nEnter your choice: ");

			int ch = Integer.parseInt(sc.nextLine());

			switch (ch) {
				case 0:
					x = false;
					break;
				case 1:
					cDAO.getCustomerDetails();
					break;
				case 2:
					cDAO.updateRecord();
					break;
				case 3:
					cDAO.deleteEntity();
					break;
				case 4:
					cDAO.viewSingleRecord();
					break;
				case 5:
					cDAO.viewAllRecords();
					break;
				default:
					customerMenu();
					break;
			}
		}
		} catch (InvalidDateFormatException e) {
            System.out.println(e.getMessage());
            customerMenu();
        }
		catch(InvalidNumberException e){
			System.out.println(e.getMessage());
			customerMenu();
		}
		catch (NumberFormatException e) {
			System.out.println("Input should be a number in the range of 0 - 5.");
			System.out.println("Entered: " + e.getMessage());
			customerMenu();
		}catch(InvalidFirstNameException e){
			System.out.println(e.getMessage());
			customerMenu();
		}
		catch(SQLException e){
			System.out.println("Error: " + e.getMessage());
		}
	}
}
