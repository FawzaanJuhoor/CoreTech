package com.coretech.coretech;

import Models.Appointment;
import db.AppointmentDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.sql.Connection;
import java.time.LocalDate;

public class AddAppointmentController {

    // Top Bar
    @FXML private Label titleLabel;

    // Left Sidebar
    @FXML private ImageView logoImageView;
    @FXML private Label welcomeLabel;
    @FXML private Button homeButton;
    @FXML private Button customerManagementButton;
    @FXML private Button vehicleManagementButton;
    @FXML private Button appointmentsButton;
    @FXML private Button servicingButton;
    @FXML private Button logoutButton;

    // Main Content
    @FXML private Label formTitleLabel;
    @FXML private TextField vinTextField;
    @FXML private TextField customerIdTextField;
    @FXML private TextField serviceTypeTextField;
    @FXML private DatePicker serviceDatePicker;
    @FXML private Button bookButton;
    @FXML private Button cancelButton;

    // Right Panel
    @FXML private Button bookAppointmentButton;
    @FXML private Button updateAppointmentButton;
    @FXML private Button cancelAppointmentButton;
    @FXML private Button searchAppointmentsButton;
    private Connection connection;

    @FXML
    public void initialize() {
        System.out.println("AddAppointmentController initialized!");
    }

    // Sidebar Handlers
    @FXML private void handleHome() { System.out.println("Home clicked"); }
    @FXML private void handleCustomerManagement() { System.out.println("Customer Management clicked"); }
    @FXML private void handleVehicleManagement() { System.out.println("Vehicle Management clicked"); }
    @FXML private void handleAppointments() { System.out.println("Appointments clicked"); }
    @FXML private void handleServicing() { System.out.println("Servicing clicked"); }
    @FXML private void handleLogout() { System.out.println("Logout clicked"); }

    // Right Panel Actions
    @FXML private void handleBookAppointment() { System.out.println("Book Appointment clicked"); }
    @FXML private void handleUpdateAppointment() { System.out.println("Update Appointment clicked"); }
    @FXML private void handleCancelAppointment() { System.out.println("Cancel Appointment clicked"); }
    @FXML private void handleSearchAppointments() { System.out.println("Search Appointments clicked"); }

    public void BookAppointment(ActionEvent actionEvent) {
        try {
            String vin = vinTextField.getText().trim();
            int customerId = Integer.parseInt(customerIdTextField.getText().trim());
            String serviceType = serviceTypeTextField.getText().trim();
            LocalDate serviceDate = serviceDatePicker.getValue();

            if (vin.isEmpty() || serviceType.isEmpty() || serviceDate == null) {
                showAlert(Alert.AlertType.WARNING, "Please fill in all fields.");
                return;
            }

            Appointment appointment = new Appointment(vin, customerId, serviceType, serviceDate);
            AppointmentDAO appointmentDAO = new AppointmentDAO(connection);

            boolean success = appointmentDAO.addAppointment(appointment);
            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "Appointment successfully booked.");
                clearForm();
            } else {
                showAlert(Alert.AlertType.ERROR, "Failed to book appointment.");
            }

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Customer ID must be a number.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "An unexpected error occurred.");
        }
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearForm() {
        vinTextField.clear();
        customerIdTextField.clear();
        serviceTypeTextField.clear();
        serviceDatePicker.setValue(null);
    }
}
