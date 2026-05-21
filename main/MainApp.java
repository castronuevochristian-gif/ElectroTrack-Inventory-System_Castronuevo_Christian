package com.electrotrack.main;

import com.electrotrack.dao.ComponentDAO;
import com.electrotrack.model.Component;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("=== ElectroTrack Live Data Retrieval Test ===");
        
       
        ComponentDAO componentDAO = new ComponentDAO();
        
       
        System.out.println("Fetching all components from 'components' table...");
        List<Component> inventoryList = componentDAO.readAll();
        
       
        System.out.println("\nTotal Items Found: " + inventoryList.size());
        System.out.println("----------------------------------------------------------------");
        
        for (Component c : inventoryList) {
            System.out.println("Part Number: " + c.getPartNumber());
            System.out.println("Item Name:   " + c.getName());
            System.out.println("Category:    " + c.getCategory());
            System.out.println("Stock Qty:   " + c.getQuantityInStock());
            System.out.println("----------------------------------------------------------------");
        }
    }
}