package com.coretech.coretech;

import Models.Appointment;
import Models.AppointmentInvoiceInfo;
import Models.UserSession;
import db.AppointmentDAO;
import db.InvoicePDFGenerator;
import db.SalesDashboardDAO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class SalesRepDashboardController extends BaseController {
    @FXML private Label upcomingCustomerNameLabel;
    @FXML private Label upcomingCustomerTimeLabel;

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
        loadDashboardStats(); // 👈 Add this call

        // Event handlers
        homeButton.setOnAction(this::handleHome);
        customerButton.setOnAction(this::handleCustomerManagement);
        vehicleButton.setOnAction(this::handleVehicleManagement);
        appointmentButton.setOnAction(this::handleAppointments);
        serviceButton.setOnAction(this::handleServicing);
        logoutButton.setOnAction(e -> handleLogout());

    }

    @FXML private Label todayCustomerCountLabel;

    private void loadDashboardStats() {
        int count = SalesDashboardDAO.getTodayCustomerCount();
        todayCustomerCountLabel.setText(String.valueOf(count));

        Map<String, String> upcoming = SalesDashboardDAO.getUpcomingCustomerToday();
        upcomingCustomerNameLabel.setText(upcoming.get("name"));
        upcomingCustomerTimeLabel.setText(upcoming.get("time"));


    }

    private void loadAppointments() {
        appointmentSummaryTable.getItems().clear();
        List<Appointment> realAppointments = AppointmentDAO.getDashboardAppointments();
        appointmentSummaryTable.getItems().addAll(realAppointments);
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


        actionColumn.setCellFactory(col -> new TableCell<>() {
            private final Button invoiceButton = new Button("Invoice");

            {
                invoiceButton.setOnAction(e -> {
                    Appointment appointment = getTableView().getItems().get(getIndex());

                    System.out.println("🚨 Invoice button clicked!");
                    System.out.println("Selected Appointment ID: " + appointment.getAppointmentId());

                    AppointmentInvoiceInfo fullInfo = InvoicePDFGenerator.getInvoiceInfo(appointment.getAppointmentId());

                    if (fullInfo == null) {
                        showAlert(Alert.AlertType.ERROR, "No Data", "No invoice data found for this appointment.");
                        return;
                    }

                    System.out.println("Customer: " + fullInfo.getCustomerName());
                    System.out.println("Items: " + (fullInfo.getServiceItems() == null ? "null" : fullInfo.getServiceItems().size()));

                    FileChooser fc = new FileChooser();
                    fc.setTitle("Save Invoice");
                    fc.setInitialFileName("Invoice_Appointment_" + fullInfo.getAppointmentId() + ".pdf");
                    fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

                    Window window = ((Node) e.getSource()).getScene().getWindow();
                    File file = fc.showSaveDialog(window);

                    if (file != null) {
                        boolean success = InvoicePDFGenerator.generateInvoice(fullInfo, file);
                        if (success) {
                            showAlert(Alert.AlertType.INFORMATION, "Invoice Generated", "Saved to:\n" + file.getAbsolutePath());
                        } else {
                            showAlert(Alert.AlertType.ERROR, "Failed", "Could not generate invoice.");
                        }
                    } else {
                        System.out.println("⚠️ User cancelled file save dialog.");
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                    return;
                }

                Appointment appointment = getTableView().getItems().get(getIndex());
                if (appointment.getStatus() != null && appointment.getStatus().equalsIgnoreCase("Completed")) {
                    setGraphic(invoiceButton); // ✅ Show only if status is "Completed"
                } else {
                    setGraphic(null); // ❌ Hide if not completed
                }
            }

        });

    }



    protected void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
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
