package com.example.avrahamgrosspa_software_1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * This program creates a User Interface to create and track parts and products.
 * This class contains the main window which tracks the current inventory
 * and leads to other windows to add or change the inventory.
 *
 * @author Avraham Gross
 * @version 1.0
 */
public class MainWindow extends Application {
    /**
     * Button to add new part
     */
    private Button addPart;
    /**
     * Button to change existing part
     */
    private Button changePart;
    /**
     * Button to delete part
     */
    private Button deletePart;
    /**
     * Button to add new product
     */
    private Button addProd;
    /**
     * Button to change existing product
     */
    private Button changeProd;
    /**
     * Button to delete existing products
     */
    private Button deleteProd;
    /**
     * Button to terminate program
     */
    private Button exit;
    /**
     * Label of page title
     */
    private Label pageTitle;
    /**
     * Label of parts section
     */
    private Label partsSection;
    private Label productsSection;
    private TableView<Part> parts;
    private TableView<Product> products;
    private TextField partSearchBar;
    private TextField productSearchBar;
    public static Part selectedPart;
    public static Product selectedProduct;
    private static int selectedPartIndex;
    private static int selectedProdIndex;

    /**
     * Returns selected part from MouseEvent to be manipulated
     *
     * @return a Part
     */
    public static Part getSelectedPart() {
        return selectedPart;
    }

    /**
     * Sets selectedPart with selection of MouseEvent
     *
     * @param part selected part
     */
    public static void setSelectedPart(Part part) {
        selectedPart = part;
    }

    /**
     * Returns selected product from MouseEvent to be manipulated
     *
     * @return a Product
     */
    public static Product getSelectedProduct() {
        return selectedProduct;
    }

    /**
     * Sets selectedProduct with selection of MouseEvent
     *
     * @param product selected product
     */
    public static void setSelectedProduct(Product product) {
        selectedProduct = product;
    }

    /**
     * Returns selected part's index in list
     *
     * @return an int of index of selected part
     */
    public static int getSelectedPartIndex() {
        return selectedPartIndex;
    }

    /**
     * Resets index for selectedPart to -1; functions as control
     */
    public static void resetSelectedPartIndex() {
        selectedPartIndex = -1;
    }

    /**
     * Returns selected product's index in list
     *
     * @return an int of index of selected product
     */
    public static int getSelectedProdIndex() {
        return selectedProdIndex;
    }

    /**
     * Resets index for selectedProduct to -1; functions as control
     */
    public static void resetSelectedProdIndex() {
        selectedProdIndex = -1;
    }

    /**
     * Resets all selected variables; ensures intuitive control of selections to not carry over from one to the next
     */
    public static void resetAllSelected() {
        selectedPart = null;
        selectedPartIndex = -1;
        selectedProduct = null;
        selectedProdIndex = -1;
    }

    private static Inventory inventory = new Inventory();

    /**
     * Returns inventory for access in other windows
     *
     * @return an Inventory
     */
    public static Inventory getInventory() {
        return inventory;
    }

    /**
     * Starts the program
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Creates main window for program
     *
     * @param primaryStage stage for window
     */
    @Override
    public void start(Stage primaryStage) {
        Scene scene;
        GridPane outerGridPane;
        GridPane partGridPane;
        GridPane partButtonsGridPane;
        GridPane productGridPane;
        GridPane productButtonsGridPane;

        outerGridPane = new GridPane();
        scene = new Scene(outerGridPane);

        addPart = new Button("Add");
        changePart = new Button("Modify");
        deletePart = new Button("Delete");
        addProd = new Button("Add");
        changeProd = new Button("Modify");
        deleteProd = new Button("Delete");
        exit = new Button("Exit");

        pageTitle = new Label("Total Inventory");
        partsSection = new Label("Parts");
        productsSection = new Label("Products");
        partSearchBar = new TextField();
        partSearchBar.setPromptText("Enter Part ID or Name");
        partSearchBar.setFocusTraversable(false);
        productSearchBar = new TextField();
        productSearchBar.setPromptText("Enter Product ID or Name");
        productSearchBar.setFocusTraversable(false);

        parts = new TableView<Part>();
        parts.setItems(inventory.getAllParts());
        //Create table columns
        TableColumn<Part, Integer> partNum = new TableColumn<Part, Integer>("Part ID");
        partNum.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn<Part, String> partsName = new TableColumn<Part, String>("Name");
        partsName.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn<Part, Integer> quantity = new TableColumn<Part, Integer>("Quantity");
        quantity.setCellValueFactory(new PropertyValueFactory("stock"));
        TableColumn<Part, Double> cost = new TableColumn<Part, Double>("Price per Unit");
        cost.setCellValueFactory(new PropertyValueFactory("price"));
        parts.getColumns().setAll(partNum, partsName, quantity, cost);
        parts.setEditable(false);

        products = new TableView<Product>();
        products.setItems(Inventory.getAllProducts());
        //Create table columns
        TableColumn<Product, Integer> prodNum = new TableColumn<Product, Integer>("Product ID");
        prodNum.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn<Product, String> prodName = new TableColumn<Product, String>("Name");
        prodName.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn<Product, Integer> amount = new TableColumn<Product, Integer>("Quantity");
        amount.setCellValueFactory(new PropertyValueFactory("stock"));
        TableColumn<Product, Double> price = new TableColumn<Product, Double>("Price per Unit");
        price.setCellValueFactory(new PropertyValueFactory("price"));
        products.getColumns().setAll(prodNum, prodName, amount, price);
        products.setEditable(false);

        partButtonsGridPane = new GridPane();
        partButtonsGridPane.setHgap(15);
        partButtonsGridPane.setPadding(new Insets(20, 0, 20, 0));
        partButtonsGridPane.add(addPart, 0, 0);
        partButtonsGridPane.add(changePart, 1, 0);
        partButtonsGridPane.add(deletePart, 2, 0);
        partButtonsGridPane.setAlignment(Pos.CENTER_RIGHT);

        partGridPane = new GridPane();
        partGridPane.setHgap(100);
        partGridPane.setPadding(new Insets(25, 25, 10, 25));
        partGridPane.add(partsSection, 0, 0);
        partGridPane.add(partSearchBar, 1, 0);
        partGridPane.add(parts, 0, 1, 2, 1);
        partGridPane.add(partButtonsGridPane, 1, 2);

        productButtonsGridPane = new GridPane();
        productButtonsGridPane.setHgap(15);
        productButtonsGridPane.setPadding(new Insets(20, 0, 20, 0));
        productButtonsGridPane.add(addProd, 0, 0);
        productButtonsGridPane.add(changeProd, 1, 0);
        productButtonsGridPane.add(deleteProd, 2, 0);
        productButtonsGridPane.setAlignment(Pos.CENTER_RIGHT);

        productGridPane = new GridPane();
        productGridPane.setHgap(75);
        productGridPane.setPadding(new Insets(25, 25, 10, 25));
        productGridPane.add(productsSection, 0, 0);
        productGridPane.add(productSearchBar, 1, 0);
        productGridPane.add(products, 0, 1, 2, 1);
        productGridPane.add(productButtonsGridPane, 1, 2);

        outerGridPane.add(pageTitle, 0, 0);
        outerGridPane.add(partGridPane, 0, 1);
        outerGridPane.add(productGridPane, 1, 1);
        outerGridPane.add(exit, 1, 2);
        outerGridPane.setHalignment(exit, HPos.RIGHT);
        outerGridPane.setPadding(new Insets(15, 15, 15, 15));

//        outerGridPane.setGridLinesVisible(true);
//        productGridPane.setGridLinesVisible(true);
//        productButtonsGridPane.setGridLinesVisible(true);
//        partGridPane.setGridLinesVisible(true);
//        partButtonsGridPane.setGridLinesVisible(true);

        resetSelectedPartIndex();
        resetSelectedProdIndex();

        partSearchBar.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Searches parts list for input in search bar. Assigns list of found parts to TableView. Supports multiple inputs with commas
             * @param actionEvent action event
             * @throws NumberFormatException if input is not a number
             * @throws NullPointerException if part list is empty
             * @throws IndexOutOfBoundsException if part list is empty
             * @see Inventory#lookupPart(int)
             * @see Inventory#lookupPart(String)
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                List<Part> temp = new ArrayList<>();
                ObservableList<Part> listOfSearchedParts = FXCollections.observableList(temp);
                String[] searchList = partSearchBar.getText().split(",");
                int searchedPartId;
                for (int i = 0; i < searchList.length; i++) {
                    try {
                        searchedPartId = Integer.parseInt(searchList[i]);
                        if (inventory.lookupPart(searchedPartId) != null && !listOfSearchedParts.contains(inventory.lookupPart(searchedPartId))) {
                            listOfSearchedParts.add(inventory.lookupPart(searchedPartId));
                        }
                    } catch (NumberFormatException e) {
                        try {
                            if (inventory.lookupPart(searchList[i]) != null && !listOfSearchedParts.contains(inventory.lookupPart(searchList[i]))) {
                                listOfSearchedParts.addAll(inventory.lookupPart(searchList[i]));
                            }
//                        } catch (NumberFormatException numberFormatException) {
                        } catch (NullPointerException nullPointerException) {
                        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        }
                    } catch (NullPointerException nullPointerException) {
                    } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    }
                }
                if (!listOfSearchedParts.isEmpty()) {
                    parts.setItems(listOfSearchedParts);
                } else {
                    parts.setItems(inventory.getAllParts());
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Part not found!");
                    alert.showAndWait();
                }
            }
        });
        productSearchBar.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Searches products list for input in search bar. Assigns list of found products to TableView. Supports multiple inputs with commas
             * @param actionEvent action event
             * @throws NumberFormatException if input is not a number
             * @throws NullPointerException if product list is empty
             * @throws IndexOutOfBoundsException if product list is empty
             * @see Inventory#lookupProduct(int)
             * @see Inventory#lookupProduct(String)
             * */
            @Override
            public void handle(ActionEvent actionEvent) {
                List<Product> temp = new ArrayList<>();
                ObservableList<Product> listOfSearchedProducts = FXCollections.observableList(temp);
                String[] searchList = productSearchBar.getText().split(",");
                int searchedProductId;
                for (int i = 0; i < searchList.length; i++) {
                    try {
                        searchedProductId = Integer.parseInt(searchList[i]);
                        if (inventory.lookupProduct(searchedProductId) != null && !listOfSearchedProducts.contains(inventory.lookupProduct(searchedProductId))) {
                            listOfSearchedProducts.add(inventory.lookupProduct(searchedProductId));
                        }
                    } catch (NumberFormatException e) {
                        try {
                            if (inventory.lookupProduct(searchList[i]) != null && !listOfSearchedProducts.contains((inventory.lookupProduct(searchList[i])))) {
                                listOfSearchedProducts.addAll(inventory.lookupProduct(searchList[i]));
                            }
                        } catch (NumberFormatException numberFormatException) {

                        } catch (NullPointerException nullPointerException) {
                        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        }
                    } catch (NullPointerException e) {
                    } catch (IndexOutOfBoundsException e) {
                    }
                }
                if (!listOfSearchedProducts.isEmpty()) {
                    products.setItems(listOfSearchedProducts);
                } else {
                    products.setItems(inventory.getAllProducts());
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Product not found!");
                    alert.showAndWait();
                }
            }
        });
        addPart.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Open new Add Parts window. Clear selections to not carry over after Add Part is closed
             * @param actionEvent action event
             * @see AddPart#start(Stage)
             * @see MainWindow#resetAllSelected()
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage partConstruct = new Stage();
                new AddPart().start(partConstruct);
                resetAllSelected();
            }
        });
        EventHandler<MouseEvent> partMouseEvent = new EventHandler<MouseEvent>() {
            /**
             * Assign value of mouse click on part to selectedPart and selectedPartIndex
             * @param mouse mouse
             * @see MainWindow#resetSelectedProdIndex()
             */
            @Override
            public void handle(MouseEvent mouse) {
                selectedPart = parts.getSelectionModel().selectedItemProperty().get();
                selectedPartIndex = parts.getSelectionModel().selectedIndexProperty().get();
                resetSelectedProdIndex();
                setSelectedProduct(null);
                mouse.consume();
            }
        };
        parts.addEventFilter(MouseEvent.MOUSE_CLICKED, partMouseEvent);

        changePart.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Open new Modify Part window. Clears selections to not carry over after Modify Part is closed
             * @param actionEvent action event
             * @see ModifyPart#start(Stage)
             * @see MainWindow#resetAllSelected()
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                if (selectedPartIndex != -1) {
                    Stage modifyPart = new Stage();
                    new ModifyPart().start(modifyPart);
                    parts.setItems(inventory.getAllParts());
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please select part to update.");
                    alert.showAndWait();
                }
            }
        });
        deletePart.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Removes part from list
             * @param actionEvent action event
             * @see Inventory#deletePart(Part)
             * @see MainWindow#resetAllSelected()
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                if (selectedPart != null) {
                    inventory.deletePart(selectedPart);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a part to delete.");
                    alert.showAndWait();
                }
                resetAllSelected();
            }
        });
        addProd.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Open new Add Product window. Clear selections to not carry over after Add Product is closed
             * @param actionEvent action event
             * @see AddProduct#start(Stage)
             * @see MainWindow#resetAllSelected()
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage prodConstruct = new Stage();
                new AddProduct().start(prodConstruct);
                resetAllSelected();
            }
        });
        EventHandler<MouseEvent> productMouseEvent = new EventHandler<MouseEvent>() {
            /**
             * Assign value of mouse click on part to selectedProduct and selectedProductIndex
             * @param mouse mouse
             * @see MainWindow#resetSelectedPartIndex()
             */
            @Override
            public void handle(MouseEvent mouse) {
                selectedProduct = products.getSelectionModel().selectedItemProperty().get();
                selectedProdIndex = products.getSelectionModel().selectedIndexProperty().get();
                resetSelectedPartIndex();
                setSelectedPart(null);
                mouse.consume();
            }
        };
        products.addEventFilter(MouseEvent.MOUSE_CLICKED, productMouseEvent);
        changeProd.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Open new Modify Product window. Clear selections to not carry over after Modify Product is closed
             * @param actionEvent action event
             * @see ModifyProduct#start(Stage)
             * @see MainWindow#resetAllSelected()
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                if (selectedProdIndex != -1) {
                    Stage modifyProd = new Stage();
                    new ModifyProduct().start(modifyProd);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please select product to update.");
                    alert.showAndWait();
                }
            }
        });
        deleteProd.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Remove product from list
             * @param actionEvent action event
             * @see Inventory#deleteProduct(Product)
             * @see MainWindow#resetAllSelected()
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                if (selectedProduct != null) {
                    inventory.deleteProduct(selectedProduct);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a product to delete.");
                    alert.showAndWait();
                }
                resetAllSelected();
            }
        });
        exit.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Close Main Window
             * @param actionEvent action event
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });
        primaryStage.setScene(scene);
        primaryStage.setTitle("Avraham Gross - PA C482");
        primaryStage.show();

    }
}
