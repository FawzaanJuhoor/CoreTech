package com.coretech.coretech;

import Models.UserSession;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SalesRepDashboardController extends BaseController {
    @FXML
    private Button homeButton, customerButton, vehicleButton, appointmentButton, serviceButton, logoutButton;


    @FXML
    protected Label welcomeLabel; // Must be protected or public if accessed by subclass

    @FXML
    public void initialize() {
        setWelcomeMessage(welcomeLabel); // Set welcome message from BaseController

        // Event handlers
        homeButton.setOnAction(this::handleHome);
        customerButton.setOnAction(this::handleCustomerManagement);
        vehicleButton.setOnAction(this::handleVehicleManagement);
        appointmentButton.setOnAction(this::handleAppointments);
        serviceButton.setOnAction(this::handleServicing);
        logoutButton.setOnAction(e -> handleLogout());

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
    }



    @FXML
    private void handleLogout() {
        logout(welcomeLabel); // Use common logout method from BaseController
    }

}
