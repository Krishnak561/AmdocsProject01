package com.amdocs.DAO;

import java.sql.SQLException;

import com.amdocs.entities.CustomerEntity;

public interface CustomerDAO {
    
    boolean insertEntity(CustomerEntity csEntity) throws SQLException;

    boolean deleteEntity() throws SQLException;

    boolean viewSingleRecord() throws SQLException;

    boolean viewAllRecords() throws SQLException;

    boolean updateRecord() throws SQLException;
}
