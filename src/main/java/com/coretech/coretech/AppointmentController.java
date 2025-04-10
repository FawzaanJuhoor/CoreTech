package com.coretech.coretech;

import Models.Appointment;
import Models.Mechanic;
import Models.Vehicle;
import db.AppointmentDAO;
import db.MechanicDAO;
import db.VehicleDAO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class AppointmentController {
    public Label formTitleLabel;
    public VBox formContainer;
    public HBox vinBox;
    public Label vinLabel;
    public TextField vinTextField;
    public HBox emailIdBox;
    public Label emailIdLabel;
    public TextField emailIdTextField;
    public ComboBox serviceTypeComboBox;
    public ComboBox mechanicComboBox;
    public DatePicker serviceDatePicker;
    public ComboBox statusComboBox;
    public HBox buttonContainer;
    public ComboBox updateStatusComboBox;
    public ComboBox updateServiceTypeComboBox;
    public TextField updateSearchVinTextField;
    public ComboBox CancelStatusComboBox;
    public DatePicker cancelServiceDatePicker;
    public ComboBox CancelMechanicComboBox;
    public ComboBox CancelServiceTypeComboBox;
    @FXML
    private ComboBox<Mechanic> updateMechanicComboBox;
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

    private void loadMechanics() {
        try {
            List<Mechanic> mechanics = MechanicDAO.getAllMechanics();
            System.out.println("Loaded mechanics: " + mechanics.size()); // Debug
            updateMechanicComboBox.setItems(FXCollections.observableArrayList(mechanics));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void initialize() {
        loadMechanics(); // Always populate mechanic combo box

        Platform.runLater(() -> {
            // Initialize with add form visible by default

            // Set the items in the ComboBox
            serviceTypeComboBox.setItems(FXCollections.observableArrayList(
                    "Oil Change", "Brake Inspection", "Tire Rotation", "Wheel Alignment",
                    "Battery Replacement", "Engine Diagnostics", "Transmission Repair","Other"
            ));


            statusComboBox.setItems(FXCollections.observableArrayList(
                    "Requested", "Started", "In Progress", "Completed"
            ));


            updateServiceTypeComboBox.setItems(FXCollections.observableArrayList(
                    "Oil Change", "Brake Inspection", "Tire Rotation", "Wheel Alignment",
                    "Battery Replacement", "Engine Diagnostics", "Transmission Repair","Other"
            ));

            updateStatusComboBox.setItems(FXCollections.observableArrayList(
                    "Requested", "Started", "In Progress", "Completed"
            ));

            // Load Mechanics from DB
//            List<Mechanic> mechanics = MechanicDAO.getAllMechanics();
//            mechanicComboBox.setItems(FXCollections.observableArrayList(mechanics));

            mechanicComboBox.setItems(FXCollections.observableArrayList(MechanicDAO.getAllMechanics()));

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
    @FXML
    private void clearAddForm() {
        vinTextField.clear();
        serviceTypeComboBox.setValue(null);
        mechanicComboBox.setValue(null);
        serviceDatePicker.setValue(null);
        statusComboBox.setValue(null);

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


    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void clearUpdateForm() {
        updateSearchAppointmentIdTextField.clear();
        updateVinTextField.clear();
        updateServiceTypeComboBox.setValue(null);
        updateServiceDatePicker.setValue(null);
        updateStatusComboBox.setValue(null);
        updateMechanicComboBox.setItems(null);
        // Re-enable the VIN field for the next search (if needed)
        updateVinTextField.setDisable(false); // Enable VIN field again
        loadMechanics(); // Always populate mechanic combo box

    }

    @FXML
    private void BookAppointment() {
        String vin = vinTextField.getText().trim();
        String serviceType = (String) serviceTypeComboBox.getValue();
        Mechanic mechanic = (Mechanic) mechanicComboBox.getValue();
        LocalDate serviceDate = serviceDatePicker.getValue();
        String status = (String) statusComboBox.getValue();
        int userId = 22; //LoggedInUser.getUserId(); // however you manage logged-in user

        if (vin.isEmpty() || serviceType == null || mechanic == null || serviceDate == null || status == null) {
            showAlert(Alert.AlertType.WARNING, "Missing Fields", "Please fill in all required fields.");
            return;
        }

        int vehicleId = VehicleDAO.getVehicleIdByVIN(vin);
        if (vehicleId == -1) {
            showAlert(Alert.AlertType.ERROR, "Invalid VIN", "Vehicle not found.");
            return;
        }

        boolean result = AppointmentDAO.insertAppointment(vehicleId, mechanic.getMechanicId(), userId, serviceType, serviceDate, status);

        if (result) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Appointment booked successfully.");
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to book appointment.");
        }
    }


    // Handle searching appointment by ID
    @FXML
    private void handleSearchAppointmentById() {
        loadMechanics();
        String appointmentIdText = updateSearchAppointmentIdTextField.getText().trim();

        if (appointmentIdText.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Input Error", "Please enter Appointment ID.");
            return;
        }

        try {
            int appointmentId = Integer.parseInt(appointmentIdText);
            Appointment appointment = AppointmentDAO.getAppointmentById(appointmentId);

            if (appointment != null) {
                // Fill in the fields with appointment details
                updateVinTextField.setText(appointment.getVin());
                updateServiceTypeComboBox.setValue(appointment.getServiceType());
                updateServiceDatePicker.setValue(appointment.getServiceDate());
                updateStatusComboBox.setValue(appointment.getStatus());

                // Set mechanic combo box based on mechanic ID
                for (Mechanic mechanic : updateMechanicComboBox.getItems()) {
                    if (mechanic.getMechanicId() == appointment.getMechanicId()) {
                        updateMechanicComboBox.setValue(mechanic);
                        break;
                    }
                }

                updateVinTextField.setDisable(true); // Disable VIN field

            } else {
                // Show alert if appointment is not found
                showAlert(Alert.AlertType.INFORMATION, "Not Found", "No appointment found with the provided Appointment ID.");
                clearUpdateForm();
                loadMechanics();
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid ID", "Please enter a valid Appointment ID.");
            loadMechanics();
        }
    }

    // Handle updating the appointment
    @FXML
    private void UpdateAppointment() {
        String appointmentIdText = updateSearchAppointmentIdTextField.getText().trim();
        String serviceType = (String) updateServiceTypeComboBox.getValue();
        Mechanic mechanic = updateMechanicComboBox.getValue();
        LocalDate serviceDate = updateServiceDatePicker.getValue();
        String status = (String) updateStatusComboBox.getValue();

        if (appointmentIdText.isEmpty() || serviceType.isEmpty() || mechanic == null || serviceDate == null || status.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Missing Fields", "Please fill in all required fields.");
            return;
        }

        // Update the appointment by ID
        boolean success = AppointmentDAO.updateAppointmentById(
                Integer.parseInt(appointmentIdText),
                serviceType,
                mechanic.getMechanicId(),
                serviceDate,
                status
        );

        if (success) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Appointment updated successfully.");
            clearUpdateForm();
            loadMechanics(); //  Always populate mechanic combo box

        } else {
            showAlert(Alert.AlertType.ERROR, "Update Failed", "Failed to update the appointment.");
            clearUpdateForm();
            loadMechanics(); // Always populate mechanic combo box

        }
    }

    // Called when "CONFIRM" is clicked
    @FXML
    public void CancelAppointment(ActionEvent event) {
        String idText = cancelSearchAppointmentIdTextField.getText().trim();

        if (idText.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Missing Input", "Please enter an appointment ID to cancel.");
            return;
        }

        try {
            int appointmentId = Integer.parseInt(idText);

            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Confirm Deletion");
            confirmation.setHeaderText(null);
            confirmation.setContentText("Are you sure you want to cancel this appointment?");

            Optional<ButtonType> result = confirmation.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                boolean success = AppointmentDAO.deleteAppointment(appointmentId);
                if (success) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Appointment cancelled successfully.");
                    clearCancelForm();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to cancel appointment. Appointment ID may not exist.");
                }
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid ID", "Appointment ID must be a number.");
        }
    }

    @FXML
    public void handleCancelSearchAppointmentById(ActionEvent event) {
        String idText = cancelSearchAppointmentIdTextField.getText().trim();

        if (idText.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Missing Input", "Please enter an appointment ID.");
            return;
        }

        try {
            int appointmentId = Integer.parseInt(idText);
            Appointment appointment = AppointmentDAO.getAppointmentById(appointmentId);

            if (appointment != null) {
                cancelVinTextField.setText(appointment.getVin());
                CancelServiceTypeComboBox.setValue(appointment.getServiceType());

                // Load mechanics from DB if not already loaded
                List<Mechanic> mechanics = MechanicDAO.getAllMechanics();
                CancelMechanicComboBox.setItems(FXCollections.observableArrayList(mechanics));

                // Set selected mechanic
                for (Mechanic m : mechanics) {
                    if (m.getMechanicId() == appointment.getMechanicId()) {
                        CancelMechanicComboBox.setValue(m);
                        break;
                    }
                }

                cancelServiceDatePicker.setValue(appointment.getServiceDate());
                CancelStatusComboBox.setValue(appointment.getStatus());

                // Disable fields
                cancelVinTextField.setEditable(false);
                CancelServiceTypeComboBox.setDisable(true);
                CancelMechanicComboBox.setDisable(true);
                cancelServiceDatePicker.setDisable(true);
                CancelStatusComboBox.setDisable(true);

            } else {
                showAlert(Alert.AlertType.WARNING, "Not Found", "No appointment found with that ID.");
            }

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Appointment ID must be a number.");
        }
    }


    @FXML
    private void clearCancelForm() {
        cancelSearchAppointmentIdTextField.clear();
        cancelVinTextField.clear();
        CancelServiceTypeComboBox.getSelectionModel().clearSelection();
        CancelMechanicComboBox.getSelectionModel().clearSelection();
        cancelServiceDatePicker.setValue(null);
        CancelStatusComboBox.getSelectionModel().clearSelection();
    }
}