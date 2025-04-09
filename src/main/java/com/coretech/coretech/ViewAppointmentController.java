package com.coretech.coretech;

import Models.Appointment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewAppointmentController {

    public Button viewAppointmentButton;
    public Button cancelAppointmentButton;
    public Button updateAppointmentButton;
    public Button addAppointmentButton;
    public Button searchButton;
    @FXML
    private TableView<Appointment> appointmentTable;

    @FXML
    private TableColumn<Appointment, Integer> appointmentIdColumn;

    @FXML
    private TableColumn<Appointment, String> vinColumn;

    @FXML
    private TableColumn<Appointment, String> mechanicColumn;

    @FXML
    private TableColumn<Appointment, String> servicingDateColumn;

    @FXML
    private TableColumn<Appointment, String> statusColumn;

    @FXML
    private TextField searchField;

    @FXML
    private void initialize() {
        // Set up column bindings
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        vinColumn.setCellValueFactory(new PropertyValueFactory<>("vin"));
        mechanicColumn.setCellValueFactory(new PropertyValueFactory<>("mechanic"));
        servicingDateColumn.setCellValueFactory(new PropertyValueFactory<>("servicingDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Load initial data
        loadAppointments();
    }

    @FXML
    private void handleSearch() {
        String searchText = searchField.getText();
        // Implement search logic here (e.g., filter by ID or VIN)
    }

    @FXML
    private void handleAddAppointment() {
        // Implement add logic
    }

    @FXML
    private void handleUpdateAppointment() {
        // Implement update logic
    }

    @FXML
    private void handleCancelAppointment() {
        // Implement cancel logic
    }

    @FXML
    private void handleHome() { /* Navigation logic */ }

    @FXML
    private void handleCustomerManagement() { /* Navigation logic */ }

    @FXML
    private void handleVehicleManagement() { /* Navigation logic */ }

    @FXML
    private void handleAppointments() { /* Navigation logic */ }

    @FXML
    private void handleServicing() { /* Navigation logic */ }

    @FXML
    private void handleLogout() { /* Logout logic */ }

    private void loadAppointments() {
        // Populate with your appointment data
        // appointmentTable.getItems().addAll(yourAppointmentList);
    }

    public void handleViewAppointment(ActionEvent actionEvent) {

    }
}