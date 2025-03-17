package com.coretech.coretech;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class AddAppointmentController {

    // Top Bar
    @FXML
    private Label titleLabel;

    // Left Sidebar
    @FXML
    private ImageView logoImageView;
    @FXML
    private Label welcomeLabel;
    @FXML
    private Button homeButton;
    @FXML
    private Button customerManagementButton;
    @FXML
    private Button vehicleManagementButton;
    @FXML
    private Button appointmentsButton;
    @FXML
    private Button servicingButton;
    @FXML
    private Button logoutButton;

    // Main Content
    @FXML
    private Label formTitleLabel;
    @FXML
    private TextField vinTextField;
    @FXML
    private TextField customerIdTextField;
    @FXML
    private TextField serviceTypeTextField;
    @FXML
    private DatePicker serviceDatePicker;
    @FXML
    private Button bookButton;
    @FXML
    private Button cancelButton;

    // Right Panel
    @FXML
    private Button bookAppointmentButton;
    @FXML
    private Button updateAppointmentButton;
    @FXML
    private Button cancelAppointmentButton;
    @FXML
    private Button searchAppointmentsButton;

    // Initialize method (optional)
    @FXML
    public void initialize() {
        // You can add initialization logic here
        System.out.println("AddAppointmentController initialized!");
    }

    // Event handlers for buttons (optional)
    @FXML
    private void handleHome() {
        System.out.println("Home button clicked!");
    }

    @FXML
    private void handleCustomerManagement() {
        System.out.println("Customer Management button clicked!");
    }

    @FXML
    private void handleVehicleManagement() {
        System.out.println("Vehicle Management button clicked!");
    }

    @FXML
    private void handleAppointments() {
        System.out.println("Appointments button clicked!");
    }

    @FXML
    private void handleServicing() {
        System.out.println("Servicing button clicked!");
    }

    @FXML
    private void handleLogout() {
        System.out.println("Logout button clicked!");
    }

    @FXML
    private void handleBookAppointment() {
        System.out.println("Book Appointment button clicked!");
    }

    @FXML
    private void handleUpdateAppointment() {
        System.out.println("Update Appointment button clicked!");
    }

    @FXML
    private void handleCancelAppointment() {
        System.out.println("Cancel Appointment button clicked!");
    }

    @FXML
    private void handleSearchAppointments() {
        System.out.println("Search Appointments button clicked!");
    }
}