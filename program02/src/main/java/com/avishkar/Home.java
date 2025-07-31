package com.avishkar;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Home extends Application {

    private final ObservableList<Inventoryitem> itemList = FXCollections.observableArrayList();
    private final TableView<Inventoryitem> tableView = new TableView<>();

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Inventory Management System");

        // Title
        Text title = new Text("Inventory Management System");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 28));
        title.setFill(Color.WHITE);
        VBox titleBox = new VBox(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(20, 0, 10, 0));

        // TableView setup
        tableView.setItems(itemList);
        tableView.setPrefHeight(300);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Inventoryitem, String> nameCol = new TableColumn<>("Item Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Inventoryitem, String> idCol = new TableColumn<>("Item ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Inventoryitem, Integer> qtyCol = new TableColumn<>("Quantity");
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        tableView.getColumns().addAll(nameCol, idCol, qtyCol);

        // Input Fields
        TextField nameField = new TextField();
        nameField.setPromptText("Item Name");

        TextField idField = new TextField();
        idField.setPromptText("Item ID");

        TextField qtyField = new TextField();
        qtyField.setPromptText("Quantity");

        HBox inputBox = new HBox(10, nameField, idField, qtyField);
        inputBox.setAlignment(Pos.CENTER);

        // Buttons
        Button addBtn = new Button("ADD");
        styleButton(addBtn);
        Button updateBtn = new Button("UPDATE");
        styleButton(updateBtn);
        Button deleteBtn = new Button("DELETE");
        styleButton(deleteBtn);

        Label resultLabel = new Label();
        resultLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        resultLabel.setTextFill(Color.WHITE);

        HBox buttonBox = new HBox(15, addBtn, updateBtn, deleteBtn);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));

        // Button Actions
        addBtn.setOnAction(e -> {
            String name = nameField.getText();
            String id = idField.getText();
            String qtyText = qtyField.getText().trim();

            if (name.isEmpty() || id.isEmpty() || qtyText.isEmpty()) {
                resultLabel.setText("All fields are required.");
                return;
            }

            try {
                int qty = Integer.parseInt(qtyText);
                itemList.add(new Inventoryitem(name, id, qty));
                nameField.clear();
                idField.clear();
                qtyField.clear();
                resultLabel.setText("Item added.");
            } catch (NumberFormatException ex) {
                resultLabel.setText("Quantity must be a number.");
            }
        });

        updateBtn.setOnAction(e -> {
            Inventoryitem selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                selected.setName(nameField.getText());
                selected.setId(idField.getText());
                try {
                    selected.setQuantity(Integer.parseInt(qtyField.getText()));
                    tableView.refresh();
                    resultLabel.setText("Item updated.");
                } catch (Exception ex) {
                    resultLabel.setText("Quantity must be a number.");
                }
            } else {
                resultLabel.setText("Select an item to update.");
            }
        });

        deleteBtn.setOnAction(e -> {
            Inventoryitem selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                itemList.remove(selected);
                nameField.clear();
                idField.clear();
                qtyField.clear();
                resultLabel.setText("Item deleted.");
            } else {
                resultLabel.setText("Select an item to delete.");
            }
        });

        // Table row selection
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                nameField.setText(newSel.getName());
                idField.setText(newSel.getId());
                qtyField.setText(String.valueOf(newSel.getQuantity()));
            }
        });

        VBox root = new VBox(20, titleBox, tableView, inputBox, buttonBox, resultLabel);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #4caf50, #2e7d32);");

        Scene scene = new Scene(root, 900, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void styleButton(Button button) {
        button.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        button.setStyle("-fx-background-color: #1b5e20; -fx-text-fill: white; -fx-background-radius: 8px;");
        button.setMinWidth(120);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
