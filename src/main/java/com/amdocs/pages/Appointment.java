package com.amdocs.pages;

import java.sql.SQLException;
import java.util.Scanner;

import com.amdocs.DAO.Implementation.AppointmentDAOimpl;

public class Appointment {

    private static Scanner sc = new Scanner(System.in);
    static AppointmentDAOimpl aDAO = new AppointmentDAOimpl();

    public static void appointmentMenu() throws SQLException {
        boolean x = true;
        try {
            while (x) {
                System.out.println("\nAppointment Main Menu");
                System.out.println("----------------");
                System.out.println(
                        "1. Book an appointment \t 2. Modify appointment details \t 3. Delete an appointment \t 4. View single record \t 5. View all records \t 0. Exit");
                System.out.println("----------------");
                System.out.println("\nEnter your choice");

                int ch = Integer.parseInt(sc.nextLine());

                switch (ch) {
                    case 0:
                        x = false;
                        break;
                    case 1:
                        aDAO.getAppointmentDetails();
                        break;
                    case 2:
                        aDAO.updateRecord();
                        break;
                    case 3:
                        aDAO.deleteEntity();
                        break;
                    case 4:
                        aDAO.viewSingleRecord();
                        break;
                    case 5:
                        aDAO.viewAllRecords();
                        break;
                    default:
                        appointmentMenu();
                        break;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Input should be a number in the range of 0 - 5.");
            System.out.println("Entered: " + e.getMessage());
            appointmentMenu();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
