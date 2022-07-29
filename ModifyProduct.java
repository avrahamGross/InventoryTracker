package com.example.avrahamgrosspa_software_1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static com.example.avrahamgrosspa_software_1.MainWindow.*;

/**
 * This class opens a window to modify an existing product in inventory in Main Window
 */
public class ModifyProduct extends Application {
    Part tempPart;
    Part availablePart;
    int associatedPartIndex;

    /**
     * Create window to modify product. Fills TextFields with current part data
     * @param primaryStage primary stage
     */
    @Override
    public void start(Stage primaryStage) {
        GridPane outerPane = new GridPane();
        GridPane availablePartsPane = new GridPane();
        GridPane associatedPartsPane = new GridPane();
        GridPane buttons = new GridPane();
        GridPane prodDetails = new GridPane();
        Button addPart = new Button("Add");
        Button removePart = new Button("Remove");
        Button commit = new Button("Save");
        commit.setDefaultButton(true);
        Button cancel = new Button("Cancel");
        Label pageTitle = new Label("Add Product");
        Label idLabel = new Label("ID");
        Label nameLabel = new Label("Name");
        Label priceLabel = new Label("Price");
        Label quantityLabel = new Label("Quantity");
        Label maxLabel = new Label("Max");
        Label minLabel = new Label("Min");
        TextField id = new TextField();
        id.setDisable(true);
        TextField name = new TextField();
        TextField price = new TextField();
        TextField quantity = new TextField();
        TextField max = new TextField();
        TextField min = new TextField();
        TextField searchAvailableParts = new TextField();
        searchAvailableParts.setPromptText("Enter Part ID or Name");
        searchAvailableParts.setFocusTraversable(false);


        TableView<Part> availableParts = new TableView<Part>();
        availableParts.setItems(MainWindow.getInventory().getAllParts());
        TableColumn<Part, Integer> partNum = new TableColumn<Part, Integer>("Part ID");
        partNum.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn<Part, String> partName = new TableColumn<Part, String>("Name");
        partName.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn<Part, Integer> partStock = new TableColumn<Part, Integer>("Quantity");
        partStock.setCellValueFactory(new PropertyValueFactory("stock"));
        TableColumn<Part, Double> partCost = new TableColumn<Part, Double>("Price per Unit");
        partCost.setCellValueFactory(new PropertyValueFactory("price"));
        availableParts.getColumns().setAll(partNum, partName, partStock, partCost);
        availableParts.setEditable(false);
        availableParts.setMaxHeight(175);

        TableView<Part> associatedParts = new TableView<Part>();
//        List<Part> temp = new ArrayList<>();
        ObservableList<Part> ProdAssociatedParts = FXCollections.observableList(MainWindow.getInventory().getAllProducts().get(MainWindow.getSelectedProdIndex()).getAllAssociatedParts());
        associatedParts.setItems(ProdAssociatedParts);
        TableColumn<Part, Integer> associatedPartNum = new TableColumn<Part, Integer>("Part ID");
        associatedPartNum.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn<Part, String> associatedPartName = new TableColumn<Part, String>("Name");
        associatedPartName.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn<Part, Integer> associatedPartStock = new TableColumn<Part, Integer>("Quantity");
        associatedPartStock.setCellValueFactory(new PropertyValueFactory("stock"));
        TableColumn<Part, Double> associatedPartCost = new TableColumn<Part, Double>("Price per Unit");
        associatedPartCost.setCellValueFactory(new PropertyValueFactory("price"));
        associatedParts.getColumns().setAll(associatedPartNum, associatedPartName, associatedPartStock, associatedPartCost);
        associatedParts.setEditable(false);
        associatedParts.setMaxHeight(175);

        availablePartsPane.setHgap(150);
        availablePartsPane.setVgap(15);
        availablePartsPane.setPadding(new Insets(15, 25, 25, 25));
        availablePartsPane.add(searchAvailableParts, 1, 0);
        searchAvailableParts.setAlignment(Pos.CENTER_RIGHT);
        availablePartsPane.add(availableParts, 0, 1, 2, 1);
        availablePartsPane.add(addPart, 1, 2);
        availablePartsPane.setHalignment(addPart, HPos.RIGHT);

        buttons.add(removePart, 0, 0, 2, 1);
        buttons.add(commit, 0, 1);
        buttons.add(cancel, 1, 1);
        buttons.setAlignment(Pos.CENTER_RIGHT);
        buttons.setHalignment(removePart, HPos.CENTER);
        buttons.setPadding(new Insets(20, 50, 20, 0));
        buttons.setVgap(10);
        buttons.setHgap(20);

        associatedPartsPane.setPadding(new Insets(15, 25, 25, 25));
        associatedPartsPane.add(associatedParts, 0, 0);
        associatedPartsPane.add(buttons, 0, 1);

        prodDetails.setVgap(15);
        prodDetails.setHgap(15);
        prodDetails.setPadding(new Insets(0, 25, 0, 25));
        prodDetails.add(idLabel, 0, 0);
        prodDetails.add(id, 1, 0);
        prodDetails.add(nameLabel, 0, 1);
        prodDetails.add(name, 1, 1);
        prodDetails.add(quantityLabel, 0, 2);
        prodDetails.add(quantity, 1, 2);
        prodDetails.add(priceLabel, 0, 3);
        prodDetails.add(price, 1, 3);
        prodDetails.add(maxLabel, 0, 4);
        prodDetails.add(max, 1, 4);
        prodDetails.add(minLabel, 2, 4);
        prodDetails.add(min, 3, 4);
        prodDetails.setAlignment(Pos.CENTER);
        prodDetails.setScaleX(1.2);
        prodDetails.setScaleY(1.);

        outerPane.add(pageTitle, 0, 0);
        outerPane.setHgap(100);
        outerPane.setPadding(new Insets(50, 50, 50, 50));
        outerPane.add(prodDetails, 0, 1, 1, 2);
        outerPane.add(availablePartsPane, 1, 1);
        outerPane.add(associatedPartsPane, 1, 2);
        outerPane.setValignment(prodDetails, VPos.CENTER);

        id.setText(Integer.toString(getSelectedProduct().getId()));
        name.setText(getSelectedProduct().getName());
        quantity.setText(Integer.toString(getSelectedProduct().getStock()));
        price.setText(Double.toString(getSelectedProduct().getPrice()));
        max.setText(Integer.toString(getSelectedProduct().getMax()));
        min.setText(Integer.toString(getSelectedProduct().getMin()));

        searchAvailableParts.setOnAction(new EventHandler<ActionEvent>() {
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
                String[] searchList = searchAvailableParts.getText().split(",");
                int searchedPartId;
                for (int i = 0; i < searchList.length; i++) {
                    try {
                        searchedPartId = Integer.parseInt(searchList[i]);
                        if (getInventory().lookupPart(searchedPartId) != null && !listOfSearchedParts.contains(MainWindow.getInventory().lookupPart(searchedPartId))) {
                            listOfSearchedParts.add(MainWindow.getInventory().lookupPart(searchedPartId));
                        }
                    } catch (NumberFormatException e) {
                        try {
                            if (getInventory().lookupPart(searchList[i]) != null && !listOfSearchedParts.contains(MainWindow.getInventory().lookupPart(searchList[i]))) {
                                listOfSearchedParts.addAll(MainWindow.getInventory().lookupPart(searchList[i]));
                            }
                        } catch (NumberFormatException numberFormatException) {
//                Alert alert = new Alert(Alert.AlertType.ERROR, "There is no Part under with the given data");
//                alert.showAndWait();
                        } catch (NullPointerException nullPointerException) {
                        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        }
                    } catch (NullPointerException nullPointerException) {
                    } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    }

                    if (!listOfSearchedParts.isEmpty()) {
                        availableParts.setItems(listOfSearchedParts);
                    } else {
                        availableParts.setItems(MainWindow.getInventory().getAllParts());
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Part not found!");
                        alert.showAndWait();
                    }
                }
            }
        });
        EventHandler<MouseEvent> availablePartsMouseEvent = new EventHandler<MouseEvent>() {
            /**
             * Assign value of mouse click in available parts table on part to availablePart
             * @param mouse mouse
             */
            @Override
            public void handle(MouseEvent mouse) {
                availablePart = availableParts.getSelectionModel().selectedItemProperty().get();
                mouse.consume();
                associatedPartIndex = -1;
            }
        };
        availableParts.addEventFilter(MouseEvent.MOUSE_CLICKED, availablePartsMouseEvent);
        addPart.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Add part to list of associated parts
             * @param actionEvent action event
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                if (availablePart != null) {
                    ProdAssociatedParts.add(availablePart);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please select part component.");
                    alert.showAndWait();
                }
                availablePart = null;
            }
        });
        EventHandler<MouseEvent> associatedPartsMouseEvent = new EventHandler<MouseEvent>() {
            /**
             * Assign index value of mouse click in associated parts table on part to associatedPartIndex
             * @param mouse mouse
             */
            @Override
            public void handle(MouseEvent mouse) {
                associatedPartIndex = associatedParts.getSelectionModel().selectedIndexProperty().get();
                mouse.consume();
                availablePart = null;
            }
        };
        associatedParts.addEventFilter(MouseEvent.MOUSE_CLICKED, associatedPartsMouseEvent);
        removePart.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Remove part from associated parts lise
             * @param actionEvent action event
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                if (associatedPartIndex != -1) {
                    ProdAssociatedParts.remove(associatedPartIndex);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please select associated part to remove.");
                    alert.showAndWait();
                }
                associatedPartIndex = -1;
            }
        });
        commit.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Create new product from input data to replace old product; replace old product in inventory
             * @param actionEvent action event
             * @throws NumberFormatException if input data type is incorrect for fields
             * @see Inventory#updateProduct(int,Product)
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                if (ProdAssociatedParts.size() < 1) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "A product must include part components");
                    alert.showAndWait();
                    return;
                }
                try {
                    if (Integer.parseInt(min.getText()) > Integer.parseInt(max.getText())) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Minimum quantity cannot be greater than maximum.");
                        alert.showAndWait();
                        return;
                    }
                    if (Integer.parseInt(quantity.getText()) < Integer.parseInt(min.getText()) || Integer.parseInt(quantity.getText()) > Integer.parseInt(max.getText())) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Quantity must fall between minimum and maximum quantity.");
                        alert.showAndWait();
                        return;
                    }
                    Product newProduct = new Product(Integer.parseInt(id.getText()), name.getText(), Double.parseDouble(price.getText()), Integer.parseInt(quantity.getText()), Integer.parseInt(min.getText()), Integer.parseInt(max.getText()));
                    for (int i = 0; i < ProdAssociatedParts.size(); i++) {
                        newProduct.addAssociatedPart(ProdAssociatedParts.get(i));
                    }
                    MainWindow.getInventory().updateProduct(MainWindow.getSelectedProdIndex(), newProduct);
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please ensure compatible values are used.");
                    alert.showAndWait();
                    return;
                }
                MainWindow.resetAllSelected();
                primaryStage.close();
            }
        });
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Close Modify Part window
             * @param actionEvent
             */
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.close();
            }
        });

        Scene scene = new Scene(outerPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
