package com.example.avrahamgrosspa_software_1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to make products out of part components
 * @author Avraham Gross
 * @version 1.0
 */
public class Product {
    /**
     * List that contains associated parts of product
     */
    private List<Part> list;
    /**
     * Wrapper around list of associated products to obtain TableView compatibility
     */
    private ObservableList<Part> associatedParts;
    /**
     * Id number of product
     */
    private int id;
    /**
     * Name of product
     */
    private String name;
    /**
     * Price of product
     */
    private double price;
    /**
     * Quantity of stock in inventory; must be between minimum and maximum
     */
    private int stock;
    /**
     * Minimum required stock of product in inventory
     */
    private int min;
    /**
     * Maximum stock of product allowed in inventory
     */
    private int max;

    /**
     * Constructor initializing Product data.
     * An Array List is used to track associated part components
     * The list is wrapped in an ObservableList to allow TableView compatibility
     * @param id id number of product
     * @param name name of product
     * @param price price of product
     * @param stock stock of product in inventory
     * @param min minimum stock of product required
     * @param max maximum stock of prodcut allowed
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        list = new ArrayList<>();
        associatedParts = FXCollections.observableList(list);
    }

    /**
     * Sets id
     * @param id id of prodcut
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns product's id number
     * @return an int value of the product's id number
     */
    public int getId() {
        return id;
    }

    /**
     * Sets product name
     * @param name name of rpoduct
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns name of product
     * @return a String value of the product name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets products price
     * @param price price of product
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns product price
     * @return a double value of the product
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets quantity of product in stock
     * @param stock quantity of prodcut in inventory
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Returns quantity of product in stock
     * @return an int value of product quantity
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the minimum required quantity to stock product
     * @param min minimum quantity allowed
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Returns minimum quantity required to stock product
     * @return an int value of minimum required quantity
     */
    public int getMin() {
        return min;
    }

    /**
     * Sets the maximum allowed quantity of product stock
     * @param max maximum allowed quantity
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Returns maximim allowed quantity of product stock
     * @return an int value of maximum allowed quantity
     */
    public int getMax() {
        return max;
    }

    /**
     * Adds a Part object to list of associated parts
     * @param part part added to list of associated parts
     */
    public void addAssociatedPart(Part part) {
            associatedParts.add(part);
    }

    /**
     * Returns a boolean value determined on if an associated part is successfully removed from the list
     * @param selectedAssociatedPart part to remove from associated parts list
     * @return a boolean value if the part was successfully removed from the list
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        return associatedParts.remove(selectedAssociatedPart);
    }

    /**
     * Returns an ObservableList containing all parts associated with a product
     * @return an ObservableList containing all parts associated with a product
     */
    public ObservableList getAllAssociatedParts() {
        return this.associatedParts;
    }

}

