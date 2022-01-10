package com.example.test.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnection {

    private Connection connection;
    private String connectionURL = "jdbc:sqlserver://LAPTOP-6Q58TKOL:1433;databaseName=projectDB;user=user;password=user";

    public Connection getConnection() throws SQLException {
        if (this.connection == null || this.connection.isClosed()) {
            try {
                connection = DriverManager.getConnection(connectionURL);
            }catch (SQLException ex) {
                System.out.println("Connection error!");
            }
        }
        return connection;
    }


//    public String getConnectionURL() {return connectionURL;}
//    public void setConnectionURL(String connectionURL) {this.connectionURL = connectionURL;}
}
