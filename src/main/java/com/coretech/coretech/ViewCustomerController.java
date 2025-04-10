package com.coretech.coretech;

import Models.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ViewCustomerController extends BaseController {

    public Button viewCustomerButton;
    public Button addCustomerButton;
    public Button updateCustomerButton;
    public Button deleteCustomerButton;
    public ScrollPane viewScrollPane;
    public HBox customerTableWrapper;
    public Button viewSearchButton;
    public StackPane contentPane;

    @FXML private TextField viewSearchField;
    @FXML private TableView<Customer> viewCustomerTable;
    @FXML private TableColumn<Customer, String> viewIdColumn;
    @FXML private TableColumn<Customer, String> viewNameColumn;
    @FXML private TableColumn<Customer, String> viewPhoneColumn;
    @FXML private TableColumn<Customer, String> viewEmailColumn;
    @FXML private TableColumn<Customer, String> viewAddressColumn;
    @FXML private TableColumn<Customer, Void> viewActionColumn;

    @FXML private VBox viewCustomerForm;
    @FXML private Button homeButton, customerButton, vehicleButton, appointmentButton, serviceButton, logoutButton;
    @FXML protected Label welcomeLabel;

    @FXML
    private void initialize() {
        setWelcomeMessage(welcomeLabel); // from BaseController

        // Navigation buttons
        homeButton.setOnAction(this::handleHome);
        customerButton.setOnAction(this::handleCustomerManagement);
        vehicleButton.setOnAction(this::handleVehicleManagement);
        appointmentButton.setOnAction(this::handleAppointments);
        serviceButton.setOnAction(this::handleServicing);
        logoutButton.setOnAction(e -> handleLogout());

        // Table column setup
        viewIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        viewNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        viewPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        viewEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        viewAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        // Action column with Update/Delete
        viewActionColumn.setCellFactory(col -> new TableCell<>() {
            private final Button updateButton = new Button("Update");
            private final Button deleteButton = new Button("Delete");
            private final HBox buttonBox = new HBox(5, updateButton, deleteButton);

            {
                updateButton.setStyle("-fx-font-size: 11px; -fx-padding: 2 5;");
                deleteButton.setStyle("-fx-font-size: 11px; -fx-padding: 2 5;");

                updateButton.setOnAction(event -> {
                    Customer customer = getTableView().getItems().get(getIndex());
                    // handle update logic
                });

                deleteButton.setOnAction(event -> {
                    Customer customer = getTableView().getItems().get(getIndex());
                    getTableView().getItems().remove(customer);
                    // handle delete logic
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : buttonBox);
            }
        });

        loadCustomers();
    }

    private void loadCustomers() {
        // Populate with customer data
        // viewCustomerTable.getItems().addAll(customerList);
    }

    @FXML
    private void handleViewSearch() {
        String searchText = viewSearchField.getText();
        // Implement search logic here
    }

    @FXML
    private void showAddCustomerForm(ActionEvent event) {
        // Add customer logic
    }

    @FXML
    private void showUpdateCustomerForm(ActionEvent event) {
        // Update customer logic
    }

    @FXML
    private void showDeleteCustomerForm(ActionEvent event) {
        // Delete customer logic
    }

    @FXML
    private void showViewCustomerForm() {
        viewCustomerForm.setVisible(true);
        viewCustomerForm.setManaged(true);
    }

    // Navigation handlers
    @FXML
    private void handleHome(ActionEvent event) {
        switchScene("SalesRepDashboard.fxml", "Home", (Node) event.getSource());
    }

    @FXML
    private void handleCustomerManagement(ActionEvent event) {
        switchScene("MainCustomerManagement.fxml", "Customer", (Node) event.getSource());
    }

    @FXML
    private void handleVehicleManagement(ActionEvent event) {
        switchScene("MainVehicleManagement.fxml", "Vehicle", (Node) event.getSource());
    }

    @FXML
    private void handleAppointments(ActionEvent event) {
        switchScene("MainAppointmentManagement.fxml", "Appointments", (Node) event.getSource());
    }

    @FXML
    private void handleServicing(ActionEvent event) {
        // Go to servicing section
    }

    @FXML
    private void handleLogout() {
        logout(welcomeLabel); // from BaseController
    }
}
