package com.coretech.coretech;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ServicingDetailsController implements Initializable {

    @FXML
    private TextField vinTextField;

    @FXML
    private TableView<ServiceRecord> serviceHistoryTable;

    @FXML
    private TableColumn<ServiceRecord, String> dateColumn;

    @FXML
    private TableColumn<ServiceRecord, String> descriptionColumn;

    // ObservableList to hold service records (initially empty)
    private ObservableList<ServiceRecord> serviceData = FXCollections.observableArrayList();

    // Sample data class to represent a service record
    public static class ServiceRecord {
        private final String date;
        private final String description;

        public ServiceRecord(String date, String description) {
            this.date = date;
            this.description = description;
        }

        public String getDate() {
            return date;
        }

        public String getDescription() {
            return description;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set up the columns in the TableView
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Bind the empty ObservableList to the TableView (no data initially)
        serviceHistoryTable.setItems(serviceData);

        // Set the column resize policy to CONSTRAINED_RESIZE_POLICY to fill the TableView's width
        serviceHistoryTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Customize cell rendering to add borders to each column's cells
        dateColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("-fx-border-color: black; -fx-border-width: 0 1px 1px 0; -fx-alignment: center;");
                } else {
                    setText(item);
                    setStyle("-fx-border-color: black; -fx-border-width: 0 1px 1px 0; -fx-alignment: center;");
                }
            }
        });

        descriptionColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("-fx-border-color: black; -fx-border-width: 0 0 1px 0; -fx-alignment: center;"); // No right border for the last column
                } else {
                    setText(item);
                    setStyle("-fx-border-color: black; -fx-border-width: 0 0 1px 0; -fx-alignment: center;"); // No right border for the last column
                }
            }
        });
    }

    @FXML
    private void handleSearch() {
        String vin = vinTextField.getText().trim();
        if (vin.isEmpty()) {
            showAlert("Error", "Please enter a VIN number.");
            return;
        }

        // Simulate searching by VIN (in a real app, this would query a database)
        System.out.println("Searching for VIN: " + vin);

        // For now, populate with sample data after search (you can replace this with actual database query)
        serviceData.clear();
        serviceData.add(new ServiceRecord("23-02-2025", "Oil Change"));
        serviceData.add(new ServiceRecord("23-01-2025", "General Check"));
    }

    @FXML
    private void handleHome() {
        System.out.println("Navigating to Home...");
        // Add navigation logic here (e.g., switch to Home scene)
    }

    @FXML
    private void handleCustomerManagement() {
        System.out.println("Navigating to Customer Management...");
        // Add navigation logic here
    }

    @FXML
    private void handleVehicleManagement() {
        System.out.println("Navigating to Vehicle Management...");
        // Add navigation logic here
    }

    @FXML
    private void handleAppointments() {
        System.out.println("Navigating to Appointments...");
        // Add navigation logic here
    }

    @FXML
    private void handleServicing() {
        System.out.println("Navigating to Servicing...");
        // Add navigation logic here
    }

    @FXML
    private void handleLogout() {
        System.out.println("Logging out...");
        // Add logout logic here
    }

    @FXML
    private void handleAddVehicle() {
        System.out.println("Adding a new vehicle...");
        // Add logic for adding a vehicle
    }

    @FXML
    private void handleRemoveVehicle() {
        System.out.println("Removing a vehicle...");
        // Add logic for removing a vehicle
    }

    @FXML
    private void handleUpdateVehicle() {
        System.out.println("Updating vehicle info...");
        // Add logic for updating vehicle info
    }

    @FXML
    private void handleServicingDetails() {
        System.out.println("Viewing servicing details...");
        // This is the current view, so no navigation needed
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}