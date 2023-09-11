package com.amdocs.DAO;

import java.sql.SQLException;

import com.amdocs.entities.AppointmentEntity;

public interface AppointmentDAO {
    boolean insertEntity(AppointmentEntity apEntity) throws SQLException;

    boolean deleteEntity() throws SQLException;

    void viewSingleRecord() throws SQLException;

    void viewAllRecords() throws SQLException;

    boolean updateRecord() throws SQLException;
    
}
