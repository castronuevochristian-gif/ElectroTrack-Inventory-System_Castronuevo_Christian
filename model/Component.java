package com.electrotrack.model;

/**
 * Entity model class representing a row from the 'components' table.
 * Demonstrates Object-Oriented Encapsulation via private fields.
 */
public class Component {
    // Private fields to hold the electronics inventory details securely
    private int componentId;
    private String partNumber;
    private String name;
    private String category;
    private int quantityInStock;
    private int supplierId; // Relational foreign key link to the suppliers table

    // Parameterized Constructor to build component objects from database rows
    public Component(int componentId, String partNumber, String name, String category, int quantityInStock, int supplierId) {
        this.componentId = componentId;
        this.partNumber = partNumber;
        this.name = name;
        this.category = category;
        this.quantityInStock = quantityInStock;
        this.supplierId = supplierId;
    }

    // Public Getters and Setters (Provides controlled entry points)
    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
}