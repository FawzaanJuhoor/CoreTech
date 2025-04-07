package com.coretech.coretech;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class ViewVehicleController {

    public Button viewVehicleButton;
    public TableView viewVehicleTable;
    public TableColumn viewIdColumn;
    public TableColumn viewCustomerIdColumn;
    public TableColumn viewMakeColumn;
    public TableColumn viewModelColumn;
    public TableColumn viewYearColumn;
    public TableColumn viewLicensePlateColumn;
    public TableColumn viewVinColumn;
    public TableColumn viewActionColumn;
    public ComboBox viewSearchTypeComboBox;
    public TextField viewSearchField;
    public Button viewSearchButton;
    public VBox viewVehicleForm;

    // Add these new columns
    @FXML
    private TableColumn<Vehicle, String> emailColumn;
    @FXML
    private TableColumn<Vehicle, String> servicingHistoryColumn;
    @FXML
    private ScrollPane scrollPane;


    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;

    @FXML
    private TableView<Vehicle> vehicleTable;

    @FXML
    private TableColumn<Vehicle, String> idColumn;

    @FXML
    private TableColumn<Vehicle, String> customerIdColumn;

    @FXML
    private TableColumn<Vehicle, String> makeColumn;

    @FXML
    private TableColumn<Vehicle, String> modelColumn;

    @FXML
    private TableColumn<Vehicle, String> yearColumn;

    @FXML
    private TableColumn<Vehicle, String> licensePlateColumn;

    @FXML
    private TableColumn<Vehicle, String> vinColumn;

    @FXML
    private TableColumn<Vehicle, Void> actionColumn;

    @FXML
    private Button addVehicleButton;

    @FXML
    private Button removeVehicleButton;

    @FXML
    private Button updateVehicleButton;

    @FXML
    private Button servicingDetailsButton;

    private ObservableList<Vehicle> vehicleData = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Initialize search type options

        // Set up column value factories
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        vinColumn.setCellValueFactory(cellData -> cellData.getValue().vinProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        makeColumn.setCellValueFactory(cellData -> cellData.getValue().makeProperty());
        modelColumn.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
        yearColumn.setCellValueFactory(cellData -> cellData.getValue().yearProperty());
        servicingHistoryColumn.setCellValueFactory(cellData -> cellData.getValue().servicingHistoryProperty());
        // Configure table properties for responsiveness
        configureTableResponsiveness();

        // Add test data with the new fields
        vehicleData.addAll(
                new Vehicle("1", "JT2BF22K8W0123456", "customer1@example.com", "Toyota", "Camry", "2018", "Oil Change (2023), Brakes (2022)"),
                new Vehicle("2", "2HGFC2F56LH543210", "customer2@example.com", "Honda", "Civic", "2020", "Tire Rotation (2023)"),
                new Vehicle("3", "1FTFW1ET0EFB12345", "customer1@example.com", "Ford", "F-150", "2019", "Oil Change (2023), Battery Replacement (2022)"),
                new Vehicle("4", "5XYZU3LB0HG123456", "customer3@example.com", "Hyundai", "Tucson", "2021", "Oil Change (2023)"),
                new Vehicle("5", "3VW6A7AJ0GM987654", "customer4@example.com", "Volkswagen", "Jetta", "2016", "Brake Pads (2022)"),
                new Vehicle("6", "1HGCR2F30FA123456", "customer5@example.com", "Honda", "Accord", "2015", "Oil Change (2023), Tires (2022)"),
                new Vehicle("7", "2T1BURHE0GC123456", "customer6@example.com", "Toyota", "Corolla", "2017", "Battery Replacement (2023)"),
                new Vehicle("8", "1C4RJFAG0FC123456", "customer7@example.com", "Jeep", "Grand Cherokee", "2018", "Oil Change (2023)"),
                new Vehicle("9", "5N1AR2MN0GC123456", "customer8@example.com", "Nissan", "Pathfinder", "2019", "Tire Rotation (2023)"),
                new Vehicle("10", "4S4BSANC0H1234567", "customer9@example.com", "Subaru", "Outback", "2020", "Oil Change (2023), Brakes (2022)")

        );
        // Load data
        vehicleTable.setItems(vehicleData);
        System.out.println("Vehicle data loaded: " + vehicleData.size() + " items");

        // Set up action column
        actionColumn.setCellFactory(getActionButtonCellFactory());

        // Optional: Add context menu for column visibility control
        addColumnVisibilityContextMenu();
    }

    private void configureTableResponsiveness() {
        // Use UNCONSTRAINED_RESIZE_POLICY to allow columns to maintain their minimum widths
        vehicleTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        // Set minimum widths for columns (these are the baseline widths)
        idColumn.setMinWidth(50);
        vinColumn.setMinWidth(120);
        emailColumn.setMinWidth(120);
        makeColumn.setMinWidth(80);
        modelColumn.setMinWidth(80);
        yearColumn.setMinWidth(50);
        servicingHistoryColumn.setMinWidth(150);
        actionColumn.setMinWidth(100);

        // Set initial preferred widths (same as min widths for consistency)
        idColumn.setPrefWidth(50);
        vinColumn.setPrefWidth(150);
        emailColumn.setPrefWidth(150);
        makeColumn.setPrefWidth(100);
        modelColumn.setPrefWidth(120);
        yearColumn.setPrefWidth(60);
        servicingHistoryColumn.setPrefWidth(200);
        actionColumn.setPrefWidth(150);

        // Total minimum width of all columns
        final double totalMinWidth = 50 + 150 + 150 + 100 + 120 + 60 + 200 + 150; // 980 pixels

        // Listen for changes to the TableView's width and adjust column widths proportionally
        vehicleTable.widthProperty().addListener((obs, oldVal, newVal) -> {
            double tableWidth = newVal.doubleValue();
            if (tableWidth > 0) {
                // If the table width is greater than the total minimum width, stretch the columns proportionally
                if (tableWidth > totalMinWidth) {
                    double scaleFactor = tableWidth / totalMinWidth;
                    idColumn.setPrefWidth(50 * scaleFactor);
                    vinColumn.setPrefWidth(150 * scaleFactor);
                    emailColumn.setPrefWidth(150 * scaleFactor);
                    makeColumn.setPrefWidth(100 * scaleFactor);
                    modelColumn.setPrefWidth(120 * scaleFactor);
                    yearColumn.setPrefWidth(60 * scaleFactor);
                    servicingHistoryColumn.setPrefWidth(200 * scaleFactor);
                    actionColumn.setPrefWidth(150 * scaleFactor);
                } else {
                    // If the table width is less than or equal to the total minimum width, use the minimum widths
                    idColumn.setPrefWidth(50);
                    vinColumn.setPrefWidth(150);
                    emailColumn.setPrefWidth(150);
                    makeColumn.setPrefWidth(100);
                    modelColumn.setPrefWidth(120);
                    yearColumn.setPrefWidth(60);
                    servicingHistoryColumn.setPrefWidth(200);
                    actionColumn.setPrefWidth(150);
                }
            }
        });

        // Make text wrap in longer columns
        vinColumn.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Text text = new Text(item);
                    text.setWrappingWidth(vinColumn.getWidth() - 10);
                    setGraphic(text);
                }
            }
        });

        servicingHistoryColumn.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Text text = new Text(item);
                    text.setWrappingWidth(servicingHistoryColumn.getWidth() - 10);
                    setGraphic(text);
                }
            }
        });
    }

    private void addColumnVisibilityContextMenu() {
        ContextMenu contextMenu = new ContextMenu();

        for (TableColumn<Vehicle, ?> column : vehicleTable.getColumns()) {
            CheckMenuItem menuItem = new CheckMenuItem(column.getText());
            menuItem.setSelected(column.isVisible());
            menuItem.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
                column.setVisible(isSelected);
                Platform.runLater(() -> vehicleTable.requestLayout());
            });
            contextMenu.getItems().add(menuItem);
        }

        vehicleTable.setContextMenu(contextMenu);
    }

    private Callback<TableColumn<Vehicle, Void>, TableCell<Vehicle, Void>> getActionButtonCellFactory() {
        return param -> new TableCell<>() {
            private final Button updateButton = new Button("Update");
            private final Button deleteButton = new Button("Delete");
            private final HBox hbox = new HBox(5, updateButton, deleteButton);

            {
                // Style buttons to be more compact
                updateButton.setStyle("-fx-font-size: 12px; -fx-padding: 2 5 2 5;");
                deleteButton.setStyle("-fx-font-size: 12px; -fx-padding: 2 5 2 5;");

                updateButton.setOnAction(event -> {
                    Vehicle vehicle = getTableView().getItems().get(getIndex());
                    handleUpdate(vehicle);
                });

                deleteButton.setOnAction(event -> {
                    Vehicle vehicle = getTableView().getItems().get(getIndex());
                    handleDelete(vehicle);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(hbox);
                }
            }
        };
    }

    @FXML
    private void handleSearch() {
        String searchTerm = searchField.getText().trim().toLowerCase();

        if (searchTerm.isEmpty()) {
            // If search field is empty, show all vehicles
            vehicleTable.setItems(vehicleData);
            return;
        }

        // Filter vehicles by VIN (case_insensitive)
        ObservableList<Vehicle> filteredList = vehicleData.filtered(vehicle ->
                vehicle.getVin().toLowerCase().contains(searchTerm)
        );

        vehicleTable.setItems(filteredList);

        if (filteredList.isEmpty()) {
            // Show alert if no vehicles found
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Results");
            alert.setHeaderText(null);
            alert.setContentText("No vehicles found with VIN containing: " + searchTerm);
            alert.showAndWait();
        }
    }

    private void handleUpdate(Vehicle vehicle) {
        System.out.println("Update vehicle: " + vehicle.getId());
    }

    private void handleDelete(Vehicle vehicle) {
        vehicleData.remove(vehicle);
        System.out.println("Delete vehicle: " + vehicle.getId());
    }

    // Right sidebar button handlers
    @FXML
    private void handleAddVehicle() {
        System.out.println("Showing add vehicle form");
    }

    @FXML
    private void handleRemoveVehicle() {
        Vehicle selectedVehicle = vehicleTable.getSelectionModel().getSelectedItem();
        if (selectedVehicle != null) {
            System.out.println("Removing vehicle: " + selectedVehicle.getId());
        } else {
            System.out.println("Please select a vehicle to remove");
        }
    }

    @FXML
    private void handleUpdateVehicle() {
        Vehicle selectedVehicle = vehicleTable.getSelectionModel().getSelectedItem();
        if (selectedVehicle != null) {
            System.out.println("Updating vehicle: " + selectedVehicle.getId());
        } else {
            System.out.println("Please select a vehicle to update");
        }
    }

    @FXML
    private void handleServicingDetails() {
        Vehicle selectedVehicle = vehicleTable.getSelectionModel().getSelectedItem();
        if (selectedVehicle != null) {
            System.out.println("Showing servicing details for vehicle: " + selectedVehicle.getId());
        } else {
            System.out.println("Please select a vehicle to view servicing details");
        }
    }

    // Navigation methods (same as CustomerController)
    @FXML
    private void handleHome() {
        System.out.println("Navigating to Home");
    }

    @FXML
    private void handleCustomerManagement() {
        System.out.println("Navigating to Customer Management");
    }

    @FXML
    private void handleVehicleManagement() {
        System.out.println("Navigating to Vehicle Management");
    }

    @FXML
    private void handleAppointments() {
        System.out.println("Navigating to Appointments");
    }

    @FXML
    private void handleServicing() {
        System.out.println("Navigating to Servicing");
    }

    @FXML
    private void handleLogout() {
        System.out.println("Logging out");
    }

    @FXML
    private void handleViewSearch(ActionEvent actionEvent) {
        // Implementation for view search if needed
    }

    @FXML
    private void handleViewVehicle() {
        Node mainVehicleView = null;
        mainVehicleView.setVisible(false);
        mainVehicleView.setManaged(false);
        viewVehicleForm.setVisible(true);
        viewVehicleForm.setManaged(true);
        System.out.println("Showing viewVehicleForm");
    }
}

class Vehicle {
    private final StringProperty id;
    private final StringProperty vin;
    private final StringProperty email;
    private final StringProperty make;
    private final StringProperty model;
    private final StringProperty year;
    private final StringProperty servicingHistory;

    public Vehicle(String id, String vin, String email, String make, String model,
                   String year, String servicingHistory) {
        this.id = new SimpleStringProperty(id);
        this.vin = new SimpleStringProperty(vin);
        this.email = new SimpleStringProperty(email);
        this.make = new SimpleStringProperty(make);
        this.model = new SimpleStringProperty(model);
        this.year = new SimpleStringProperty(year);
        this.servicingHistory = new SimpleStringProperty(servicingHistory);
    }

    // Getter methods
    public String getId() { return id.get(); }
    public String getVin() { return vin.get(); }
    public String getEmail() { return email.get(); }
    public String getMake() { return make.get(); }
    public String getModel() { return model.get(); }
    public String getYear() { return year.get(); }
    public String getServicingHistory() { return servicingHistory.get(); }

    // Property getters
    public StringProperty idProperty() { return id; }
    public StringProperty vinProperty() { return vin; }
    public StringProperty emailProperty() { return email; }
    public StringProperty makeProperty() { return make; }
    public StringProperty modelProperty() { return model; }
    public StringProperty yearProperty() { return year; }
    public StringProperty servicingHistoryProperty() { return servicingHistory; }

}