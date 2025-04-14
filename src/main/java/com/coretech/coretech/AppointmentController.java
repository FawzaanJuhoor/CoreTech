package com.coretech.coretech;

import Models.Appointment;
import Models.Mechanic;
import Models.UserSession;
import Models.Vehicle;
import db.AppointmentDAO;
import db.MechanicDAO;
import db.VehicleDAO;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class AppointmentController extends BaseController{
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
    public TextField viewAppointmentSearchField;
    public Button viewAppointmentSearchButton;
    public TableColumn actionColumn;
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

    @FXML private Button viewAppointmentButton;
    @FXML private VBox viewForm;
    @FXML private TableView<Appointment> appointmentTable;
    @FXML private TableColumn<Appointment, Integer> appointmentIdColumn;
    @FXML private TableColumn<Appointment, String> vinColumn;
    @FXML private TableColumn<Appointment, String> mechanicColumn;
    @FXML private TableColumn<Appointment, String> servicingDateColumn;
    @FXML private TableColumn<Appointment, String> statusColumn;


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
    private Button homeButton, customerButton, vehicleButton, appointmentButton, serviceButton, logoutButton;
    @FXML
    public void initialize() {
        setWelcomeMessage(welcomeLabel); // Set welcome message from BaseController
        setupAppointmentTable();
        loadAppointments();
        // Event handlers
        homeButton.setOnAction(this::handleHome);
        customerButton.setOnAction(this::handleCustomerManagement);
        vehicleButton.setOnAction(this::handleVehicleManagement);
        appointmentButton.setOnAction(this::handleAppointments);
        serviceButton.setOnAction(this::handleServicing);
        logoutButton.setOnAction(e -> handleLogout());
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
        viewForm.setVisible(false);
        viewForm.setManaged(false);
    }

    private void highlightActiveButton(Button activeButton) {
        Button[] allButtons = {
                bookAppointmentButton,
                updateAppointmentButton,
                cancelAppointmentButton,
                searchAppointmentsButton,
                viewAppointmentButton // add this
        };

        for (Button btn : allButtons) {
            if (btn != null) {
                btn.setStyle("-fx-background-color: white; -fx-text-fill: #2293C3; -fx-border-color: #2293C3; -fx-font-weight: bold;");
            }
        }

        if (activeButton != null) {
            activeButton.setStyle("-fx-background-color: #2293C3; -fx-text-fill: white; -fx-font-weight: bold;");
        }
    }


    @FXML
    protected Label welcomeLabel; // Must be protected or public if accessed by subclass
    // Navigation methods
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
        int userId = UserSession.getInstance().getUserId();

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

    @FXML
    private void handleViewAppointment() {
        hideAllForms(); // Create this to hide other forms
        viewForm.setVisible(true);
        viewForm.setManaged(true);
        highlightActiveButton(viewAppointmentButton); // Correct method name
        // Optional: if using active button highlighting
        loadAppointments(); // Optional: Load table data
    }



    private void hideAllForms() {
        addForm.setVisible(false);
        addForm.setManaged(false);

        updateForm.setVisible(false);
        updateForm.setManaged(false);

        cancelForm.setVisible(false);
        cancelForm.setManaged(false);

        searchForm.setVisible(false);
        searchForm.setManaged(false);

        viewForm.setVisible(false);
        viewForm.setManaged(false);
    }

    @FXML
    private void setupAppointmentTable() {
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        vinColumn.setCellValueFactory(new PropertyValueFactory<>("vin"));
        mechanicColumn.setCellValueFactory(new PropertyValueFactory<>("mechanicName")); // This assumes your model has getMechanicName()
        servicingDateColumn.setCellValueFactory(new PropertyValueFactory<>("serviceDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

//        // Action column: add Update/Delete buttons
//        actionColumn.setCellFactory(col -> new TableCell<Appointment, Void>() {
//            private final Button updateButton = new Button("Update");
//            private final Button deleteButton = new Button("Delete");
//            private final HBox buttonBox = new HBox(5, updateButton, deleteButton);
//
//            {
//                updateButton.setStyle("-fx-font-size: 11px; -fx-padding: 2 5;");
//                deleteButton.setStyle("-fx-font-size: 11px; -fx-padding: 2 5;");
//
//                updateButton.setOnAction(e -> {
//                    Appointment appointment = getTableView().getItems().get(getIndex());
//                    populateUpdateForm(appointment);
//                    showUpdateForm();
//                });
//
//                deleteButton.setOnAction(e -> {
//                    Appointment appointment = getTableView().getItems().get(getIndex());
//                    populateCancelForm(appointment);
//                    showCancelForm();
//                });
//            }
//
//            @Override
//            protected void updateItem(Void item, boolean empty) {
//                super.updateItem(item, empty);
//                setGraphic(empty ? null : buttonBox);
//            }
//        });
    }

    private void populateUpdateForm(Appointment a) {
        updateSearchAppointmentIdTextField.setText(String.valueOf(a.getAppointmentId()));
        updateVinTextField.setText(a.getVin());
        updateServiceTypeComboBox.setValue(a.getServiceType());
        updateServiceDatePicker.setValue(a.getServiceDate());
        updateStatusComboBox.setValue(a.getStatus());

        // Load mechanics from DB if not already loaded
        List<Mechanic> mechanics = MechanicDAO.getAllMechanics();
        CancelMechanicComboBox.setItems(FXCollections.observableArrayList(mechanics));

        // Set selected mechanic
        for (Mechanic m : mechanics) {
            if (m.getMechanicId() == a.getMechanicId()) {
                CancelMechanicComboBox.setValue(m);
                break;
            }
        }

        updateVinTextField.setDisable(true); // Optional: lock VIN after loading
    }

    private void populateCancelForm(Appointment a) {
        cancelSearchAppointmentIdTextField.setText(String.valueOf(a.getAppointmentId()));
        cancelVinTextField.setText(a.getVin());
        CancelServiceTypeComboBox.setValue(a.getServiceType());
        cancelServiceDatePicker.setValue(a.getServiceDate());
        CancelStatusComboBox.setValue(a.getStatus());

        // Load mechanics into cancel combo box
        List<Mechanic> mechanics = MechanicDAO.getAllMechanics();
        CancelMechanicComboBox.setItems(FXCollections.observableArrayList(mechanics));

        // Select mechanic in combo box
        for (Mechanic m : mechanics) {
            if (m.getMechanicId() == a.getMechanicId()) {
                CancelMechanicComboBox.setValue(m);
                break;
            }
        }

        // Disable all fields for read-only delete view
        cancelVinTextField.setEditable(false);
        CancelServiceTypeComboBox.setDisable(true);
        CancelMechanicComboBox.setDisable(true);
        cancelServiceDatePicker.setDisable(true);
        CancelStatusComboBox.setDisable(true);
    }

    private void redirectToUpdateOrCancel(Appointment appointment) {
        if (appointment.getStatus().equalsIgnoreCase("Completed")) {
            showAlert(Alert.AlertType.INFORMATION, "View Only", "Completed appointments cannot be modified.");
        } else {
            updateSearchAppointmentIdTextField.setText(String.valueOf(appointment.getAppointmentId()));
            handleSearchAppointmentById();
            showUpdateForm();
        }
    }


    @FXML
    private void loadAppointments() {
        appointmentTable.getItems().clear();
        List<Appointment> list = AppointmentDAO.getAllAppointments();
        System.out.println("Loaded: " + list.size() + " appointments");

        appointmentTable.getItems().addAll(list);
    }

    @FXML
    private void handleViewAppointmentSearch(ActionEvent actionEvent) {
        String query = viewAppointmentSearchField.getText().trim().toLowerCase();
        appointmentTable.getItems().clear();

        if (!query.isEmpty()) {
            List<Appointment> all = AppointmentDAO.getAllAppointments();
            for (Appointment a : all) {
                if (a.getVin().toLowerCase().contains(query) ||
                        a.getMechanicName().toLowerCase().contains(query)) {
                    appointmentTable.getItems().add(a);
                }
            }
        } else {
            loadAppointments();
        }
    }


}