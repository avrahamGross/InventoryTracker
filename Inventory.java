package com.example.avrahamgrosspa_software_1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.List;

/**
 * A class to make an inventory to track all parts and products
 * @author Avraham Gross
 * @version 1.0
 */
public class Inventory {
    /** A list that contains all parts in an inventory */
    private static List<Part> listParts = new ArrayList<>();
    /** A list that contains all products in an inventory */
    private static List<Product> listProducts = new ArrayList<>();
    /** An ObservableList as a wrapper for list of parts */
    private static ObservableList<Part> allParts = FXCollections.observableList(listParts);
    /** An ObservableList as a wrapper for list of products */
    private static ObservableList<Product> allProducts = FXCollections.observableList(listProducts);

    /**
     * Adds a part to list of parts in inventory
     * @param newPart part to add to inventory
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * Adds a product to list of products in inventory
     * @param newProduct product to add to inventory
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * Returns part from list of parts if found. Returns null if part not found
     * @param partId id of product part to be found
     * @return found part. If part not found, returns null
     */
    public static Part lookupPart(int partId) {
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getId() == partId) {
                return allParts.get(i);
            }
        }
        return null;
    }

    /**
     * Returns product from list of products if found. Returns null if product not found
     * @param productId id of product to be found
     * @return found product. If product not found, returns null
     */
    public static Product lookupProduct(int productId) {
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getId() == productId) {
                return allProducts.get(i);
            }
        }
        return null;
    }

    /**
     * Returns an ObservableList containing searched part. Returns null if part not found
     * @param partName name of part to be found
     * @return an ObservableList containing found part. If part not found, returns null
     */
    public static ObservableList<Part> lookupPart(String partName) {
        List<Part> list = new ArrayList<>();
        ObservableList<Part> result = FXCollections.observableList(list);
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getName().equals(partName)) {
                result.add(allParts.get(i));
                return result;
            }
        }
        return null;
    }
    /**
     * Returns an ObservableList containing searched product. Returns null if part not found
     * @param productName name of product to be found
     * @return an ObservableList containing found product. If product not found, returns null
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        List<Product> list = new ArrayList<>();
        ObservableList<Product> results = FXCollections.observableList(list);
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getName().equals(productName)) {
                results.setAll(allProducts.get(i));
                return results;
            }
        }
        return null;
    }

    /**
     * Replaces part with new part upon changes made
     * @param index index of current part
     * @param selectedPart new part to replace original part
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /**
     * Replaces product with new product upon changes made
     * @param index index of current product
     * @param newProduct new product to replace current product
     */
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    /**
     * Returns a boolean value determined on if a part was successfully deleted
     * @param selectedPart part to be deleted
     * @return a boolean value if the part was successfully deleted from the inventory
     */
    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    /**
     * Returns a boolean value determined on if a product was successfully deleted
     * @param selectedProduct part to be deleted
     * @return a boolean value if the product was successfully deleted from the inventory
     */
    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }

    /**
     * Returns an ObservableList of all parts in inventory
     * @return an ObservableList of all parts in inventory
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Returns an ObservableList of all products in inventory
     * @return an ObservableList of all products in inventory
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

}

