package com.coretech.coretech;

import Models.Appointment;
import Models.UserSession;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.time.LocalDate;
import java.util.List;

public class SalesRepDashboardController extends BaseController {
    @FXML
    private Button homeButton, customerButton, vehicleButton, appointmentButton, serviceButton, logoutButton;

    @FXML private TableView<Appointment> appointmentSummaryTable;
    @FXML private TableColumn<Appointment, Integer> appointmentIdColumn;
    @FXML private TableColumn<Appointment, String> emailColumn;
    @FXML private TableColumn<Appointment, String> makeColumn;
    @FXML private TableColumn<Appointment, String> modelColumn;
    @FXML private TableColumn<Appointment, Integer> yearColumn;
    @FXML private TableColumn<Appointment, Integer> userIdColumn;
    @FXML private TableColumn<Appointment, String> serviceTypeColumn;
    @FXML private TableColumn<Appointment, LocalDate> serviceDateColumn;
    @FXML private TableColumn<Appointment, String> statusColumn;
    @FXML private TableColumn<Appointment, Double> totalCostColumn;
    @FXML private TableColumn<Appointment, Void> actionColumn;

    @FXML
    protected Label welcomeLabel; // Must be protected or public if accessed by subclass

    @FXML
    public void initialize() {
        setWelcomeMessage(welcomeLabel); // Set welcome message from BaseController
        setupTableColumns();
        loadAppointments();
        // Event handlers
        homeButton.setOnAction(this::handleHome);
        customerButton.setOnAction(this::handleCustomerManagement);
        vehicleButton.setOnAction(this::handleVehicleManagement);
        appointmentButton.setOnAction(this::handleAppointments);
        serviceButton.setOnAction(this::handleServicing);
        logoutButton.setOnAction(e -> handleLogout());

    }
    private void loadAppointments() {
        // Clear any existing data
//        appointmentSummaryTable.getItems().clear();
//
//        // Example: Dummy data (replace with AppointmentDAO.getDashboardAppointments() when ready)
//        List<Appointment> dummyAppointments = List.of(
//                new Appointment(1, "john@example.com", "Toyota", "Corolla", 2020, 101, "Oil Change", LocalDate.now(), "Completed", 89.99),
//                new Appointment(2, "jane@example.com", "Honda", "Civic", 2019, 102, "Brake Inspection", LocalDate.now().plusDays(1), "Requested", 120.50)
//        );
//
//        appointmentSummaryTable.getItems().addAll(dummyAppointments);
    }


    private void setupTableColumns() {
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        makeColumn.setCellValueFactory(new PropertyValueFactory<>("make"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        serviceTypeColumn.setCellValueFactory(new PropertyValueFactory<>("serviceType"));
        serviceDateColumn.setCellValueFactory(new PropertyValueFactory<>("serviceDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        totalCostColumn.setCellValueFactory(new PropertyValueFactory<>("totalCost"));

        actionColumn.setCellFactory(col -> {
            TableCell<Appointment, Void> cell = new TableCell<>() {
                private final Button invoiceButton = new Button("Invoice");

                {
                    invoiceButton.setOnAction(e -> {
                        Appointment appointment = getTableView().getItems().get(getIndex());
                        System.out.println("Generate invoice for appointment ID: " + appointment.getAppointmentId());
                        // Implement your invoice logic here
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(invoiceButton);
                    }
                }
            };
            return cell;
        });
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
