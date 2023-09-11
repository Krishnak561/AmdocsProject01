package com.amdocs.DAO.Implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.amdocs.DAO.AppointmentDAO;
import com.amdocs.entities.AppointmentEntity;
import com.amdocs.exceptions.InvalidFirstNameException;
import com.amdocs.services.SQLconnection;

public class AppointmentDAOimpl implements AppointmentDAO {

    private static final String INSERT_APPOINTMENT = "insert into appointment(FirstName, LastName, ReasonOfVisit, DateOfVisit, Customer_ID) values(?,?,?,?,?)";
    private static String DELETE_APPOINTMENT = "Delete from appointment where Appointment_ID = ";
    private static String VIEW_SINGLE_APPOINTMENT = "Select * from appointment where Appointment_ID = ";
    private static String VIEW_ALL_APPOINTMENT = "Select * from appointment";
    private static String UPDATE_APPOINTMENT = "Update appointment set FirstName = ?, LastName = ?, ReasonOfVisit = ?, DateOfVisit = ?, Customer_ID = ? where Appointment_ID = ?";

    private Scanner sc = new Scanner(System.in);
    private static Connection conn = SQLconnection.getConnection();
    private ArrayList<Object> customerData = new ArrayList<Object>();

    @Override
    public boolean insertEntity(AppointmentEntity apEntity) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(INSERT_APPOINTMENT);
        ps.setString(1, apEntity.getFirstName());
        ps.setString(2, apEntity.getLastName());
        ps.setString(3, apEntity.getReasonOfVisit());
        ps.setString(4, apEntity.getDateOfVisit());
        ps.setInt(5, apEntity.getCustomer_ID());
        int executeUpdate = ps.executeUpdate();
        ps.close();
        if (executeUpdate > 0) {
            System.out.println("Appointment Booked.");
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteEntity() throws SQLException {
        System.out.println("Enter the Appointment ID to delete");
        String id = sc.nextLine();
        DELETE_APPOINTMENT += id;
        Statement stmt = conn.createStatement();
        int n = stmt.executeUpdate(DELETE_APPOINTMENT);
        if (n > 0) {
            System.out.println("Appointment Cancelled.");
            return true;
        }
        System.out.println("Record with ID " + id + " not found. Enter again.");
        return false;
    }

    @Override
    public void viewSingleRecord() throws SQLException {
        System.out.println("Enter the Appointment ID to view");
        String id = sc.nextLine();
        VIEW_SINGLE_APPOINTMENT += id;
        viewRecord(VIEW_SINGLE_APPOINTMENT);
    }

    @Override
    public void viewAllRecords() throws SQLException {
        viewRecord(VIEW_ALL_APPOINTMENT);
    }

    @Override
    public boolean updateRecord() throws SQLException {
        viewSingleRecord();

        PreparedStatement ps = conn.prepareStatement(UPDATE_APPOINTMENT);
        String t;
        System.out.println("Do you want to update Advocate's First Name? (Y/N)");
        t = sc.nextLine();
        if (t.equals("Y")) {
            System.out.println("Enter your updated Advocate's First Name");
            ps.setString(1, sc.nextLine());
        } else {
            ps.setString(1, String.valueOf(customerData.get(0)));
        }

        System.out.println("Do you want to update Advocate's Last Name? (Y/N)");
        t = sc.nextLine();
        if (t.equals("Y")) {
            System.out.println("Enter your updated Advocate's Last Name");
            ps.setString(2, sc.nextLine());
        } else {
            ps.setString(2, String.valueOf(customerData.get(1)));
        }

        System.out.println("Do you want to update Reason Of Visit? (Y/N)");
        t = sc.nextLine();
        if (t.equals("Y")) {
            System.out.println("Enter your updated Reason Of Visit");
            ps.setString(3, (sc.nextLine()));
        } else {
            ps.setString(3, (String) customerData.get(2));
        }

        System.out.println("Do you want to update Date Of Visit? (Y/N)");
        t = sc.nextLine();
        if (t.equals("Y")) {
            System.out.println("Enter your updated Date Of Visit");
            ps.setString(4, sc.nextLine());
        } else {
            ps.setString(4, (String) customerData.get(3));
        }

        ps.setInt(5, (int) customerData.get(4));
        int executeUpdate = ps.executeUpdate();
        ps.close();
        if (executeUpdate > 0) {
            System.out.println("Appointment Details Updated for Customer ID " + String.valueOf(customerData.get(5)));
            return true;
        }
        return false;
    }

    public void getAppointmentDetails() throws SQLException, InvalidFirstNameException {

        AppointmentEntity obj = new AppointmentEntity();
            System.out.println("Enter Advocate's First Name: ");
            String fn = sc.nextLine();
        if (fn.length() != 0) {
            obj.setFirstName(fn);
        } else {
            throw new InvalidFirstNameException("Error: First Name can not be empty.\nTry again");
        }

            System.out.println("Enter Advocate's Last Name: ");
            obj.setLastName(sc.nextLine());

            System.out.println("Enter Customer ID: ");
            obj.setCustomer_ID(Integer.parseInt(sc.nextLine()));

            System.out.println("Enter the Date of Appointment: ");
            obj.setDateOfVisit(sc.nextLine());

            System.out.println("Enter Reason of Visit ");
            obj.setReasonOfVisit(sc.nextLine());

        
        insertEntity(obj);
    }

    private void viewRecord(String VIEW_APPOINTMENT) throws SQLException {

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(VIEW_APPOINTMENT);

        while (rs.next()) {
            int id = rs.getInt("Appointment_ID");
            String afn = rs.getString("FirstName");
            String aln = rs.getString("LastName");
            String rov = rs.getString("ReasonOfVisit");
            String dov = rs.getString("DateOfVisit");
            String cid = rs.getString("Customer_ID");

            customerData.add(afn);
            customerData.add(aln);
            customerData.add(rov);
            customerData.add(dov);
            customerData.add(id);
            customerData.add(cid);

            System.out.println("\n\tAppointment ID: \t" + id);
            System.out.println("\tAdvocate Full Name: \t" + "Mr/Mrs/Miss. " + afn + " " + aln);
            System.out.println("\tReason of Visit: \t" + rov);
            System.out.println("\tDate of Visit: \t" + dov);
            System.out.println("\tCustomer ID: \t" + cid);

        }

    }

}
