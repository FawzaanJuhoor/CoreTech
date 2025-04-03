package com.coretech.coretech;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class AppointmentController {
    // Main containers
    @FXML private BorderPane rootPane;
    @FXML private VBox centerPane;
    @FXML private AnchorPane rightPanel;

    // Form containers
    @FXML private VBox addForm;
    @FXML private VBox updateForm;
    @FXML private VBox cancelForm;
    @FXML private VBox searchForm;

    // Add form fields
    @FXML private TextField addVinTextField;
    @FXML private TextField addCustomerIdTextField;
    @FXML private TextField addServiceTypeTextField;
    @FXML private DatePicker addServiceDatePicker;
    @FXML private Button bookButton;
    @FXML private Button addCancelButton;

    // Update form fields
    @FXML private TextField updateSearchAppointmentIdTextField;
    @FXML private TextField updateVinTextField;
    @FXML private TextField updateCustomerIdTextField;
    @FXML private TextField updateServiceTypeTextField;
    @FXML private DatePicker updateServiceDatePicker;
    @FXML private Button updateSearchButton;
    @FXML private Button updateButton;
    @FXML private Button updateCancelButton;

    // Cancel form fields
    @FXML private TextField cancelSearchAppointmentIdTextField;
    @FXML private TextField cancelVinTextField;
    @FXML private TextField cancelCustomerTextField;
    @FXML private TextField cancelServiceTypeTextField;
    @FXML private TextField cancelDateTextField;
    @FXML private Button cancelSearchButton;
    @FXML private Button confirmCancelButton;
    @FXML private Button cancelCancelButton;

    // Search form fields
    @FXML private TextField searchAppointmentIdTextField;
    @FXML private TextField searchVinTextField;
    @FXML private TextField searchCustomerIdTextField;
    @FXML private TextField searchCustomerNameTextField;
    @FXML private TextField searchPhoneTextField;
    @FXML private TextField searchServiceTypeTextField;
    @FXML private TextField searchDateTextField;
    @FXML private Button searchButton;
    @FXML private Button searchBackButton;

    // Right panel buttons
    @FXML private Button bookAppointmentButton;
    @FXML private Button updateAppointmentButton;
    @FXML private Button cancelAppointmentButton;
    @FXML private Button searchAppointmentsButton;

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            // Initialize with add form visible by default
            showAddForm();
        });
    }

    // Right panel button handlers
    @FXML
    private void handleBookAppointment(ActionEvent event) {
        showAddForm();
    }

    @FXML
    private void handleUpdateAppointment(ActionEvent event) {
        showUpdateForm();
    }

    @FXML
    private void handleCancelAppointment(ActionEvent event) {
        showCancelForm();
    }

    @FXML
    private void handleSearchAppointments(ActionEvent event) {
        showSearchForm();
    }

    // Form visibility control methods
    private void showAddForm() {
        setAllFormsInvisible();
        addForm.setVisible(true);
        addForm.setManaged(true);
        highlightActiveButton(bookAppointmentButton);
    }

    private void showUpdateForm() {
        setAllFormsInvisible();
        updateForm.setVisible(true);
        updateForm.setManaged(true);
        highlightActiveButton(updateAppointmentButton);
    }

    private void showCancelForm() {
        setAllFormsInvisible();
        cancelForm.setVisible(true);
        cancelForm.setManaged(true);
        highlightActiveButton(cancelAppointmentButton);
    }

    private void showSearchForm() {
        setAllFormsInvisible();
        searchForm.setVisible(true);
        searchForm.setManaged(true);
        highlightActiveButton(searchAppointmentsButton);
    }

    private void setAllFormsInvisible() {
        addForm.setVisible(false);
        addForm.setManaged(false);
        updateForm.setVisible(false);
        updateForm.setManaged(false);
        cancelForm.setVisible(false);
        cancelForm.setManaged(false);
        searchForm.setVisible(false);
        searchForm.setManaged(false);
    }

    private void highlightActiveButton(Button activeButton) {
        // Reset all buttons to default style
        bookAppointmentButton.setStyle("-fx-background-color: white; -fx-text-fill: #2293C3; -fx-border-color: #2293C3; -fx-font-weight: bold;");
        updateAppointmentButton.setStyle("-fx-background-color: white; -fx-text-fill: #2293C3; -fx-border-color: #2293C3; -fx-font-weight: bold;");
        cancelAppointmentButton.setStyle("-fx-background-color: white; -fx-text-fill: #2293C3; -fx-border-color: #2293C3; -fx-font-weight: bold;");
        searchAppointmentsButton.setStyle("-fx-background-color: white; -fx-text-fill: #2293C3; -fx-border-color: #2293C3; -fx-font-weight: bold;");

        // Highlight the active button
        activeButton.setStyle("-fx-background-color: #2293C3; -fx-text-fill: white; -fx-font-weight: bold;");
    }

    // Form submission handlers
    @FXML
    private void handleBookAppointmentSubmit(ActionEvent event) {
        // Validate and process the add appointment form
        System.out.println("Booking appointment...");
        // Add your business logic here
    }

    @FXML
    private void handleUpdateAppointmentSubmit(ActionEvent event) {
        // Validate and process the update appointment form
        System.out.println("Updating appointment...");
        // Add your business logic here
    }

    @FXML
    private void handleCancelAppointmentSubmit(ActionEvent event) {
        // Validate and process the cancel appointment form
        System.out.println("Cancelling appointment...");
        // Add your business logic here
    }

    @FXML
    private void handleSearchAppointmentsSubmit(ActionEvent event) {
        // Process the search form
        System.out.println("Searching appointments...");
        // Add your business logic here
    }

    // Navigation methods
    @FXML
    private void handleHome(ActionEvent event) {
        System.out.println("Navigating to Home...");
        // Add navigation logic here
    }

    @FXML
    private void handleCustomerManagement(ActionEvent event) {
        System.out.println("Navigating to Customer Management...");
        // Add navigation logic here
    }

    @FXML
    private void handleVehicleManagement(ActionEvent event) {
        System.out.println("Navigating to Vehicle Management...");
        // Add navigation logic here
    }

    @FXML
    private void handleAppointments(ActionEvent event) {
        System.out.println("Already in Appointments");
    }

    @FXML
    private void handleServicing(ActionEvent event) {
        System.out.println("Navigating to Servicing...");
        // Add navigation logic here
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        System.out.println("Logging out...");
        // Add logout logic here
    }

    // Form cancel/back buttons
    @FXML
    private void handleAddCancel(ActionEvent event) {
        System.out.println("Add form cancelled");
        showAddForm(); // Stay on same form but clear fields
        clearAddForm();
    }

    @FXML
    private void handleUpdateCancel(ActionEvent event) {
        System.out.println("Update form cancelled");
        showUpdateForm(); // Stay on same form but clear fields
        clearUpdateForm();
    }

    @FXML
    private void handleCancelBack(ActionEvent event) {
        System.out.println("Cancel form back");
        showCancelForm(); // Stay on same form but clear fields
        clearCancelForm();
    }

    @FXML
    private void handleSearchBack(ActionEvent event) {
        System.out.println("Search form back");
        showSearchForm(); // Stay on same form but clear fields
        clearSearchForm();
    }

    // Form clearing methods
    private void clearAddForm() {
        addVinTextField.clear();
        addCustomerIdTextField.clear();
        addServiceTypeTextField.clear();
        addServiceDatePicker.setValue(null);
    }

    private void clearUpdateForm() {
        updateSearchAppointmentIdTextField.clear();
        updateVinTextField.clear();
        updateCustomerIdTextField.clear();
        updateServiceTypeTextField.clear();
        updateServiceDatePicker.setValue(null);
    }

    private void clearCancelForm() {
        cancelSearchAppointmentIdTextField.clear();
        cancelVinTextField.clear();
        cancelCustomerTextField.clear();
        cancelServiceTypeTextField.clear();
        cancelDateTextField.clear();
    }

    private void clearSearchForm() {
        searchAppointmentIdTextField.clear();
        searchVinTextField.clear();
        searchCustomerIdTextField.clear();
        searchCustomerNameTextField.clear();
        searchPhoneTextField.clear();
        searchServiceTypeTextField.clear();
        searchDateTextField.clear();
    }

    public void handleCancel(ActionEvent actionEvent) {
    }
}