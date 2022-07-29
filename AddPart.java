package com.example.avrahamgrosspa_software_1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * This class opens a window to add a new part to inventory in Main Window
 */
public class AddPart extends Application {
    /**
     * Create window to add parts
     * @param primaryStage primary stage
     */
    @Override
    public void start(Stage primaryStage) {
        GridPane gp = new GridPane();
        GridPane buttons = new GridPane();
        Scene scene = new Scene(gp);
        RadioButton inHouse = new RadioButton("In-House");
        RadioButton outsourced = new RadioButton("Outsourced");
        ToggleGroup group = new ToggleGroup();
        inHouse.setToggleGroup(group);
        outsourced.setToggleGroup(group);
        Label idLabel = new Label("ID");
        Label nameLabel = new Label("Name");
        Label priceLabel = new Label("Price");
        Label quantityLabel = new Label("Quantity");
        Label maxLabel = new Label("Max");
        Label minLabel = new Label("Min");
        Label machineIdLabel = new Label("Machine ID");
        machineIdLabel.setVisible(false);
        Label outsourcedLabel = new Label("Company Name");
        outsourcedLabel.setVisible(false);
        TextField id = new TextField();
        id.setDisable(true);
        id.setText("Auto Generated ID");
        TextField name = new TextField();
        TextField price = new TextField();
        TextField quantity = new TextField();
        TextField max = new TextField();
        TextField min = new TextField();
        TextField machineId = new TextField();
        machineId.setVisible(false);
        TextField compName = new TextField();
        compName.setVisible(false);
        Button add = new Button("Apply");
        add.setDefaultButton(true);
        Button cancel = new Button("Cancel");

        gp.add(inHouse, 0, 0);
        gp.add(outsourced, 1, 0);
        gp.add(idLabel, 0, 1);
        gp.add(id, 1, 1);
        gp.add(nameLabel, 0, 2);
        gp.add(name, 1, 2);
        gp.add(priceLabel, 0, 3);
        gp.add(price, 1, 3);
        gp.add(quantityLabel, 0, 4);
        gp.add(quantity, 1, 4);
        gp.add(maxLabel, 0, 5);
        gp.add(max, 1, 5);
        gp.add(minLabel, 2, 5);
        gp.add(min, 3, 5);
        gp.add(machineIdLabel, 0, 6);
        gp.add(machineId, 1, 6);
        gp.add(outsourcedLabel, 0, 6);
        gp.add(compName, 1, 6);
        buttons.add(add, 0, 0);
        buttons.add(cancel, 1, 0);
        buttons.setHgap(15);
        gp.add(buttons, 3, 7);
        gp.setPadding(new Insets(20, 20, 20, 20));
        gp.setVgap(15);
        gp.setHgap(15);

        inHouse.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Set machine id field to visible
             * @param event event
             */
            @Override
            public void handle(ActionEvent event) {
                machineId.setVisible(true);
                machineIdLabel.setVisible(true);
                outsourcedLabel.setVisible(false);
                compName.setVisible(false);
            }
        });
        outsourced.setOnAction(new EventHandler<ActionEvent>() {
            /**
             *Set company name to visible
             * @param event event
             */
            @Override
            public void handle(ActionEvent event) {
                machineId.setVisible(false);
                machineIdLabel.setVisible(false);
                outsourcedLabel.setVisible(true);
                compName.setVisible(true);
            }
        });
        add.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Create new part from input data and add to inventory
             * @param event event
             * @throws NumberFormatException if input data type is incorrect for field
             * @see Inventory#getAllParts()
             * @see  Inventory#addPart(Part)
             */
            @Override
            public void handle(ActionEvent event) {
                if (!inHouse.isSelected() && !outsourced.isSelected()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please select the part source location.");
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
                    int idAutoGenerate = MainWindow.getInventory().getAllParts().size() + 1;
                    if (MainWindow.getInventory().getAllParts().isEmpty()) {
                        idAutoGenerate = 1;
                    }
                    if (inHouse.isSelected()) {
                        InHouse newPart = new InHouse(idAutoGenerate, name.getText(), Double.parseDouble(price.getText()), Integer.parseInt(quantity.getText()), Integer.parseInt(min.getText()), Integer.parseInt(max.getText()), Integer.parseInt(machineId.getText()));
                        MainWindow.getInventory().addPart(newPart);
                    }
                    if (outsourced.isSelected()) {
                        Outsourced newPart = new Outsourced(idAutoGenerate, name.getText(), Double.parseDouble(price.getText()), Integer.parseInt(quantity.getText()), Integer.parseInt(min.getText()), Integer.parseInt(max.getText()), compName.getText());
                        MainWindow.getInventory().addPart(newPart);
                    }
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please ensure compatible values are used.");
                    alert.showAndWait();
                    return;
                }
                primaryStage.close();
            }
        });
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Close Add Part window
             * @param event event
             */
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
