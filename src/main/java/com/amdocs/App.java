package com.amdocs;

import java.sql.SQLException;
import java.util.*;

import com.amdocs.pages.*;

public class App {

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		try {

			mainMenu();
		} catch (NumberFormatException e) {

			System.out.println("Input should be a number in the range of 0 - 4.");
			System.out.println("Entered: " + e.getMessage());

		} catch (Exception e) {

			System.out.println("Error : " + e.getMessage());

		}
	}

	public static void mainMenu() throws SQLException {

		boolean x = true;
		while (x) {
			System.out.println("\n Main Menu");
			System.out.println("----------------");
			System.out.println("1. Customer \t 2. Advocate \t 3. Appointment \t 4. Service \t 0. Exit");
			System.out.println("----------------");
			System.out.print("\nEnter your choice: ");
			int ch = Integer.parseInt(sc.next());
			switch (ch) {
				case 0:
					x = false;
					break;
				case 1:
					Customer.customerMenu();
					break;
				case 2:
					Appointment.appointmentMenu();
					break;
				case 3:
					System.out.println("This service is not available. Try Again!");
					mainMenu();
					break;
				case 4:
					System.out.println("This service is not available. Try Again!");
					mainMenu();
					break;
				default:
					mainMenu();
					break;
			}
		}
	}
}
