package com.example.avrahamgrosspa_software_1;

/**
 * A class extending the Part class to create Outsourced parts
 * @author Avraham Gross
 * @version 1.0
 */
public class Outsourced extends Part{
    /**
     * The name of the company that produces the part
     */
    private String companyName;

    /**
     * Constructor initializing Outsourced part variables
     * @param id id number of part
     * @param name name of part
     * @param price price per unit of part
     * @param stock quantity of part in stock
     * @param min mimimnum quantity allowed
     * @param max maximum quantity allowed
     * @param companyName name of manufacturing company
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * Sets the manufacturing company's name to companyName
     * @param companyName name of manufacturing company
     */
    public void setCompanyId(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Returns name of manufacturing company
     * @return a String value of the part manufacturer's name
     */
    public String getCompanyName() {
        return companyName;
    }
}

