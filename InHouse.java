package com.example.avrahamgrosspa_software_1;

/**
 * A class extending the Part class to create In House parts
 * @author Avraham Gross
 * @version 1.0
 */
public class InHouse extends Part {
    /**
     * The id number of the machine
     */
    private int machineId;
    /**
     * Constructor initializing In House part variables
     * @param id id number of part
     * @param name name of part
     * @param price price per unit of part
     * @param stock quantity of part in stock
     * @param min mimimnum quantity allowed
     * @param max maximum quantity allowed
     * @param machineId id number of the machine
     */
    public InHouse (int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * Sets the machine's id to machineId
     * @param machineId id number of the machine
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    /**
     * Returns the machine id number
     * @return an int value of the machine id number
     */
    public int getMachineId() {
        return machineId;
    }
}

