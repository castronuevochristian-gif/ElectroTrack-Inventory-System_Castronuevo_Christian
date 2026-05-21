package com.electrotrack.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
 
    private static DBConnection instance;
    private Connection connection;

  
    private static final String URL = "jdbc:mysql://localhost:3306/electrotrack_db";
    private static final String USER = "root";       // If your MySQL username is different, change it here
    private static final String PASSWORD = "Isekaitruck04_";   // Put your actual MySQL password here

  
    private DBConnection() {
        try {
            // Tell Java to load and use the MySQL Connector driver we added earlier
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Attempt to establish the active link with your MySQL Workbench schema
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("SUCCESS: Connected to electrotrack_db securely!");
            
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: Driver not found. Double check your Libraries folder.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("ERROR: Connection failed. Check your password or if MySQL is running.");
            e.printStackTrace();
        }
    }

   
    public static synchronized DBConnection getInstance() {
        try {
           
            if (instance == null || instance.getConnection() == null || instance.getConnection().isClosed()) {
                instance = new DBConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instance;
    }

   
    public Connection getConnection() {
        return connection;
    }
}