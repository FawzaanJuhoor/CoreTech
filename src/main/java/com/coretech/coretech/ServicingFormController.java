package com.coretech.coretech;

import Models.ServiceInventory;
import db.ServiceInventoryDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

public class ServicingFormController extends BaseController {

    public TextField AppointmentID;
    public TextField ItemID;
    public TextField addAppointmentIDField;
    public TextField addItemID;
    public TextField addQuantity;
    public Button cancelAddButton;
    public Button addServiceButton;
    public Button customerButton;
    public Button vehicleButton;
    public Button appointmentButton;
    public Button serviceButton;


    // Sidebar Buttons
    @FXML private Button homeButton;
    @FXML private Button logoutButton;

    @FXML
    protected Label welcomeLabel; // Must be protected or public if accessed by subclass

    @FXML
    private void initialize() {
        setWelcomeMessage(welcomeLabel);

        homeButton.setOnAction(this::handleHome);
        customerButton.setOnAction(this::handleCustomerManagement);
        vehicleButton.setOnAction(this::handleVehicleManagement);
        appointmentButton.setOnAction(this::handleAppointments);
        serviceButton.setOnAction(this::handleServicing);
        logoutButton.setOnAction(e -> handleLogout());

        // Add handlers for add/cancel buttons
        addServiceButton.setOnAction(this::handleAddService);
        cancelAddButton.setOnAction(e -> clearForm());
    }

    @FXML
    private void handleAddService(ActionEvent event) {
        try {
            int appointmentId = Integer.parseInt(addAppointmentIDField.getText().trim());
            int itemId = Integer.parseInt(addItemID.getText().trim());
            int quantity = addQuantity.getText().isEmpty() ? 1 : Integer.parseInt(addQuantity.getText().trim());

            ServiceInventory serviceInventory = new ServiceInventory(appointmentId, itemId, quantity);
            boolean success = ServiceInventoryDAO.addServiceInventory(serviceInventory);

            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Service item added successfully.");

                clearForm();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to add service item.");

            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.WARNING, "Error","Please enter valid numeric values.");

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error",  e.getMessage());

        }
    }

    @FXML
    private void clearForm() {
        addAppointmentIDField.clear();
        addItemID.clear();
        addQuantity.clear();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleHome(ActionEvent event) {
        System.out.println("Home Clicked");
        switchScene("SalesRepDashboard.fxml", "Home", (Node) event.getSource());

    }

    @FXML
    private void handleCustomerManagement(ActionEvent event) {
        System.out.println("Customer Management Clicked");
        switchScene("MainCustomerManagement.fxml", "Customer", (Node) event.getSource());

    }

    @FXML
    private void handleVehicleManagement(ActionEvent event) {
        System.out.println("Vehicle Management Clicked");
        switchScene("MainVehicleManagement.fxml", "Vehicle", (Node) event.getSource());

    }

    @FXML
    private void handleAppointments(ActionEvent event) {
        System.out.println("Appointments Clicked");
        switchScene("MainAppointmentManagement.fxml", "Appointment", (Node) event.getSource());

    }

    @FXML
    private void handleServicing(ActionEvent event) {
        System.out.println("Servicing Clicked");
        switchScene("ServicingForm.fxml", "Appointment", (Node) event.getSource());

    }

    @FXML
    private void handleLogout() {
        logout(welcomeLabel); // Use common logout method from BaseController
    }


}