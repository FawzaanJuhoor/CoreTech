package com.coretech.coretech;

import Models.Vehicle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ViewVehicleController extends BaseController{

    public Button viewVehicleButton;
    public Button addVehicleButton;
    public Button updateVehicleButton;
    public Button removeVehicleButton;
    public Button servicingDetailsButton;
    public Button viewSearchButton;
    public ScrollPane viewScrollPane;
    public HBox tableWrapper;

    @FXML private TextField viewSearchField;
    @FXML private TableView<Vehicle> vehicleTable;
    @FXML private TableColumn<Vehicle, String> idColumn;
    @FXML private TableColumn<Vehicle, String> vinColumn;
    @FXML private TableColumn<Vehicle, String> emailColumn;
    @FXML private TableColumn<Vehicle, String> makeColumn;
    @FXML private TableColumn<Vehicle, String> modelColumn;
    @FXML private TableColumn<Vehicle, String> yearColumn;
    @FXML private TableColumn<Vehicle, String> servicingHistoryColumn;
    @FXML private TableColumn<Vehicle, Void> actionColumn;

    @FXML
    private VBox viewVehicleForm;

    @FXML
    private Button homeButton, customerButton, vehicleButton, appointmentButton, serviceButton, logoutButton;


    @FXML
    protected Label welcomeLabel; // Must be protected or public if accessed by subclass


    @FXML
    private void initialize() {

        setWelcomeMessage(welcomeLabel); // Set welcome message from BaseController

        // Event handlers
        homeButton.setOnAction(this::handleHome);
        customerButton.setOnAction(this::handleCustomerManagement);
        vehicleButton.setOnAction(this::handleVehicleManagement);
        appointmentButton.setOnAction(this::handleAppointments);
        serviceButton.setOnAction(this::handleServicing);
        logoutButton.setOnAction(e -> handleLogout());

        // Set up column bindings
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        vinColumn.setCellValueFactory(new PropertyValueFactory<>("vin"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        makeColumn.setCellValueFactory(new PropertyValueFactory<>("make"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        servicingHistoryColumn.setCellValueFactory(new PropertyValueFactory<>("servicingHistory"));

        actionColumn.setCellFactory(col -> new TableCell<>() {
            private final Button updateButton = new Button("Update");
            private final Button deleteButton = new Button("Delete");
            private final HBox buttonBox = new HBox(5, updateButton, deleteButton);

            {
                updateButton.setStyle("-fx-font-size: 11px; -fx-padding: 2 5;");
                deleteButton.setStyle("-fx-font-size: 11px; -fx-padding: 2 5;");

                updateButton.setOnAction(event -> {
                    Vehicle vehicle = getTableView().getItems().get(getIndex());
//                    System.out.println("Update clicked for: " + vehicle.getId());
                    // Add your update logic here
                });

                deleteButton.setOnAction(event -> {
                    Vehicle vehicle = getTableView().getItems().get(getIndex());
                    getTableView().getItems().remove(vehicle); // Remove from table
//                    System.out.println("Deleted: " + vehicle.getId());
                    // Add DB delete logic if needed
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : buttonBox);
            }
        });

        // Load initial vehicle data
        loadVehicles();
    }

    private void loadVehicles() {
        // Populate with vehicle data
        // vehicleTable.getItems().addAll(vehicleList);
    }

    @FXML
    private void handleViewSearch() {
        String searchText = viewSearchField.getText();
        // Implement search logic here
    }

    @FXML
    private void handleAddVehicle(ActionEvent event) {
        // Implement add vehicle logic
    }

    @FXML
    private void handleUpdateVehicle(ActionEvent event) {
        // Implement update vehicle logic
    }

    @FXML
    private void handleRemoveVehicle(ActionEvent event) {
        // Implement remove logic
    }

    @FXML
    private void handleServicingDetails(ActionEvent event) {
        // Implement servicing detail logic
    }

    @FXML
    private void handleViewVehicle() {
        viewVehicleForm.setVisible(true);
        viewVehicleForm.setManaged(true);
        System.out.println("Showing view vehicle form");
    }

    // Navigation handlers
    @FXML
    private void handleHome(ActionEvent event) {
        System.out.println("Home Clicked");
        switchScene("SalesRepDashboard.fxml", "Home", (Node) event.getSource());
    }

    @FXML
    private void handleCustomerManagement(ActionEvent event) {
        System.out.println("Customer Management Clicked");
        switchScene("MainCustomerManagement.fxml", "Home", (Node) event.getSource());
    }

    @FXML
    private void handleVehicleManagement(ActionEvent event) {
        System.out.println("Vehicle Management Clicked");
        switchScene("MainVehicleManagement.fxml", "Home", (Node) event.getSource());
    }

    @FXML
    private void handleAppointments(ActionEvent event) {
        System.out.println("Appointments Clicked");
        switchScene("MainAppointmentManagement.fxml", "Home", (Node) event.getSource());
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
