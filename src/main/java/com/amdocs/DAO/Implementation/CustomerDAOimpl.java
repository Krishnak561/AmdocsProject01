package com.amdocs.DAO.Implementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.amdocs.DAO.CustomerDAO;
import com.amdocs.entities.CustomerEntity;
import com.amdocs.exceptions.InvalidDateFormatException;
import com.amdocs.exceptions.InvalidFirstNameException;
import com.amdocs.exceptions.InvalidNumberException;
import com.amdocs.services.SQLconnection;

public class CustomerDAOimpl implements CustomerDAO {

    Scanner sc = new Scanner(System.in);
    private ArrayList<Object> customerData = new ArrayList<Object>();

    private static final String INSERT_CUSTOMER = "insert into customer(FirstName, LastName, DOB, City, Pincode, PhoneNumber, Email) values(?,?,?,?,?,?,?)";
    private static String DELETE_CUSTOMER = "Delete from customer where Customer_ID = ";
    private static String VIEW_SINGLE_CUSTOMER = "Select * from customer where Customer_ID = ";
    private static String VIEW_ALL_CUSTOMER = "Select * from customer";
    private static String UPDATE_CUSTOMER = "Update customer set FirstName = ?, LastName = ?, DOB = ?, City = ?, Pincode = ?, PhoneNumber = ?, Email = ? where Customer_ID = ?";

    private static Connection conn = SQLconnection.getConnection();

    @Override
    public boolean insertEntity(CustomerEntity csEntity) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(INSERT_CUSTOMER);
        ps.setString(1, csEntity.getFirstName());
        ps.setString(2, csEntity.getLastName());
        ps.setString(3, csEntity.getDob());
        ps.setString(4, csEntity.getCityOfResidence());
        ps.setInt(5, csEntity.getPincode());
        ps.setString(6, csEntity.getPhoneNum());
        ps.setString(7, csEntity.getEmail());
        int executeUpdate = ps.executeUpdate();
        ps.close();
        if (executeUpdate > 0) {
            System.out.println("Customer Registered.");
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteEntity() throws SQLException {
        System.out.println("Enter the Customer ID to delete");
        String id = sc.nextLine();
        DELETE_CUSTOMER += id;
        Statement stmt = conn.createStatement();
        int n = stmt.executeUpdate(DELETE_CUSTOMER);
        if (n > 0) {
            System.out.println("Customer deleted.");
            return true;
        }
        System.out.println("Record with ID " + id + " not found. Enter again.");
        return false;
    }

    @Override
    public boolean viewSingleRecord() throws SQLException {
        System.out.println("Enter the Customer ID to view");
        String id = sc.nextLine();
        VIEW_SINGLE_CUSTOMER += id;
        boolean flag = viewRecord(VIEW_SINGLE_CUSTOMER);
        return flag;

    }

    @Override
    public boolean viewAllRecords() throws SQLException {
        boolean flag = viewRecord(VIEW_ALL_CUSTOMER);
        return flag;
    }

    private boolean viewRecord(String VIEW_CUSTOMER) throws SQLException {

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(VIEW_CUSTOMER);

        while (rs.next()) {
            int id = rs.getInt("Customer_ID");
            String fn = rs.getString("FirstName");
            String ln = rs.getString("LastName");
            String dob = rs.getString("DOB");
            String city = rs.getString("City");
            int pincode = rs.getInt("Pincode");
            String phonenum = rs.getString("PhoneNumber");
            String email = rs.getString("Email");

            customerData.add(fn);
            customerData.add(ln);
            customerData.add(dob);
            customerData.add(city);
            customerData.add(pincode);
            customerData.add(phonenum);
            customerData.add(email);
            customerData.add(id);

            System.out.println("\n\tCustomer ID: \t" + id);
            System.out.println("\tFull Name: \t" + "Mr/Mrs/Miss. " + fn + " " + ln);
            System.out.println("\tDate Of Birth: \t" + dob);
            System.out.println("\tCity of Residence: \t" + city);
            System.out.println("\tPincode: \t" + pincode);
            System.out.println("\tPhone Number \t" + phonenum);
            System.out.println("\tEmail ID: \t" + email);

        }
        return true;

    }

    public void getCustomerDetails() throws SQLException, InvalidNumberException, InvalidFirstNameException, InvalidDateFormatException {

        CustomerEntity obj = new CustomerEntity();

        System.out.println("Enter your first name: ");
        String fn = sc.nextLine();
        if (fn.length() != 0) {
            obj.setFirstName(fn);
        } else {
            throw new InvalidFirstNameException("Error: First Name can not be empty.\nTry again");
        }

        System.out.println("Enter your last name: ");
        obj.setLastName(sc.nextLine());

        System.out.println("Enter your DOB(DD-MM-YYYY): ");
        String strDate = sc.nextLine();
        String strDateRegEx = "\\d{2}-\\d{2}-\\d{4}";

        if (strDate.matches(strDateRegEx)) {
            obj.setDob(strDate);
        } else {
            throw new InvalidDateFormatException("Error: Invalid date format. Try Again!");
        }

        System.out.println("Enter your city of residence: ");
        obj.setCityOfResidence(sc.nextLine());

        System.out.println("Enter your pincode: ");
        obj.setPincode(Integer.parseInt(sc.nextLine()));

        System.out.println("Enter your email: ");
        obj.setEmail(sc.nextLine());

        System.out.println("Enter your phone number: ");
        String str = sc.nextLine();
        Pattern ptrn = Pattern.compile("(0/91)?[7-9][0-9]{9}");
        Matcher match = ptrn.matcher(str);
        if (match.find() && match.group().equals(str)) {
            obj.setPhoneNum(str);
        } else {
            throw new InvalidNumberException("Error: Invalid Number has been entered.\nTry Again");
        }

        insertEntity(obj);
    }

    @Override
    public boolean updateRecord() throws SQLException {

        viewSingleRecord();

        PreparedStatement ps = conn.prepareStatement(UPDATE_CUSTOMER);
        String t;
        System.out.println("Do you want to update First Name? (Y/N)");
        t = sc.nextLine();
        if (t.equals("Y")) {
            System.out.println("Enter your updated First Name");
            ps.setString(1, sc.nextLine());
        } else {
            ps.setString(1, String.valueOf(customerData.get(0)));
        }

        System.out.println("Do you want to update Last Name? (Y/N)");
        t = sc.nextLine();
        if (t.equals("Y")) {
            System.out.println("Enter your updated Last Name");
            ps.setString(2, sc.nextLine());
        } else {
            ps.setString(2, String.valueOf(customerData.get(1)));
        }

        System.out.println("Do you want to update DOB? (Y/N)");
        t = sc.nextLine();
        if (t.equals("Y")) {
            System.out.println("Enter your updated DOB");
            ps.setString(3, sc.nextLine());
        } else {
            ps.setString(3, String.valueOf(customerData.get(2)));
        }

        System.out.println("Do you want to update City of residence? (Y/N)");
        t = sc.nextLine();
        if (t.equals("Y")) {
            System.out.println("Enter your updated City of Residence");
            ps.setString(4, sc.nextLine());
        } else {
            ps.setString(4, String.valueOf(customerData.get(3)));
        }

        System.out.println("Do you want to update Pincode? (Y/N)");
        t = sc.nextLine();
        if (t.equals("Y")) {
            System.out.println("Enter your updated Pincode");
            ps.setInt(5, Integer.parseInt(sc.nextLine()));
        } else {
            ps.setInt(5, (int) customerData.get(4));
        }

        System.out.println("Do you want to update Email? (Y/N)");
        t = sc.nextLine();
        if (t.equals("Y")) {
            System.out.println("Enter your updated Email");
            ps.setString(6, sc.nextLine());
        } else {
            ps.setString(6, String.valueOf(customerData.get(5)));
        }

        System.out.println("Do you want to update Phone Number? (Y/N)");
        t = sc.nextLine();
        if (t.equals("Y")) {
            System.out.println("Enter your updated Phone Number");
            ps.setString(7, sc.nextLine());
        } else {
            ps.setString(7, String.valueOf(customerData.get(6)));
        }

        ps.setInt(8, (int) customerData.get(7));
        int executeUpdate = ps.executeUpdate();
        ps.close();
        if (executeUpdate > 0) {
            System.out.println("Customer Details Updated.");
            return true;
        }
        return false;
    }

}
