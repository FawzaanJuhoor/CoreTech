package com.coretech.coretech;

import db.CustomerDAO;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*; // Updated import to include ScrollPane from javafx.scene.control
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.util.List;

public class CustomerController {
    public TableView viewCustomerTable;

    public TableColumn viewIdColumn;
    public TableColumn viewNameColumn;
    public TableColumn viewPhoneColumn;
    public TableColumn viewEmailColumn;
    public TableColumn viewAddressColumn;
    public TableColumn viewActionColumn;
    @FXML
    private ScrollPane scrollPane; // This will now match the type from javafx.scene.control.ScrollPane

    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;

    @FXML
    private TableView<Customer> customerTable;

    @FXML
    private TableColumn<Customer, String> idColumn;

    @FXML
    private TableColumn<Customer, String> nameColumn;

    @FXML
    private TableColumn<Customer, String> phoneColumn;

    @FXML
    private TableColumn<Customer, String> emailColumn;

    @FXML
    private TableColumn<Customer, String> addressColumn;

    @FXML
    private TableColumn<Customer, Void> actionColumn;

    @FXML
    private Button addCustomerButton;

    @FXML
    private Button updateCustomerButton;

    @FXML
    private Button viewCustomerButton;

    @FXML
    private Button deleteCustomerButton;

    private ObservableList<Customer> customerData = FXCollections.observableArrayList();

    private CustomerDAO customerDAO = new CustomerDAO();

    @FXML
    private void initialize() {
        // Bind columns to model properties
        idColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("emailID"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

//        loadCustomerData();

//        // Set up column value factories using lambdas
//        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
//        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
//        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
//        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
//        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
//
//        // Configure table properties for responsiveness
        configureTableResponsiveness();
//
//        // Add test data
//        customerData.addAll(
//                new Customer("1", "John Smith", "555-1234", "john@example.com", "123 Main St"),
//                new Customer("2", "Sarah Johnson", "555-5678", "sarah@example.com", "456 Oak Ave"),
//                new Customer("3", "Michael Brown", "555-9012", "michael@example.com", "789 Pine Rd")
//        );
//
//        // Load data
//        customerTable.setItems(customerData);
//        System.out.println("Data loaded: " + customerData.size() + " items");
//        customerData.forEach(c -> System.out.println(
//                "ID: " + c.getId() +
//                        ", Name: " + c.getName() +
//                        ", Phone: " + c.getPhone()
//        ));

        // Set up action column
        actionColumn.setCellFactory(getActionButtonCellFactory());

        // Optional: Add context menu for column visibility control
        addColumnVisibilityContextMenu();
    }

//    private void loadCustomerData() {
//        List<Customer> customerList = customerDAO.getAllCustomers();
//        ObservableList<Customer> observableList = FXCollections.observableArrayList(customerList);
//        customerTable.setItems(observableList);
//    }

    private void configureTableResponsiveness() {
        // Make columns resizable
        customerTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Bind table width to scrollpane viewport width minus a small margin
        customerTable.prefWidthProperty().bind(
                scrollPane.widthProperty().subtract(20) // 20px margin for scrollbar
        );

        // Set minimum widths for columns (smaller than in FXML for mobile)
        idColumn.setMinWidth(50);
        nameColumn.setMinWidth(80);
        phoneColumn.setMinWidth(90);
        emailColumn.setMinWidth(120);
        addressColumn.setMinWidth(150);
        actionColumn.setMinWidth(120);

        // Make text wrap in address and email columns
        emailColumn.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Text text = new Text(item);
                    text.setWrappingWidth(emailColumn.getWidth() - 10);
                    setGraphic(text);
                }
            }
        });

        addressColumn.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Text text = new Text(item);
                    text.setWrappingWidth(addressColumn.getWidth() - 10);
                    setGraphic(text);
                }
            }
        });

        // Listen for width changes and adjust column sizes
        customerTable.widthProperty().addListener((obs, oldVal, newVal) -> {
            double totalWidth = newVal.doubleValue();
            if (totalWidth > 0) {
                // Distribute width proportionally
                idColumn.setPrefWidth(totalWidth * 0.1);
                nameColumn.setPrefWidth(totalWidth * 0.15);
                phoneColumn.setPrefWidth(totalWidth * 0.15);
                emailColumn.setPrefWidth(totalWidth * 0.25);
                addressColumn.setPrefWidth(totalWidth * 0.25);
                actionColumn.setPrefWidth(totalWidth * 0.1);
            }
        });
    }

    private void addColumnVisibilityContextMenu() {
        ContextMenu contextMenu = new ContextMenu();

        for (TableColumn<Customer, ?> column : customerTable.getColumns()) {
            CheckMenuItem menuItem = new CheckMenuItem(column.getText());
            menuItem.setSelected(column.isVisible());
            menuItem.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
                column.setVisible(isSelected);
                // Adjust other column widths when one is hidden/shown
                Platform.runLater(() -> {
                    customerTable.requestLayout();
                });
            });
            contextMenu.getItems().add(menuItem);
        }

        customerTable.setContextMenu(contextMenu);
    }

    private Callback<TableColumn<Customer, Void>, TableCell<Customer, Void>> getActionButtonCellFactory() {
        return param -> new TableCell<>() {
            private final Button updateButton = new Button("Update");
            private final Button deleteButton = new Button("Delete");
            private final HBox hbox = new HBox(5, updateButton, deleteButton); // Reduced spacing

            {
                // Style buttons to be more compact
                updateButton.setStyle("-fx-font-size: 12px; -fx-padding: 2 5 2 5;");
                deleteButton.setStyle("-fx-font-size: 12px; -fx-padding: 2 5 2 5;");

                updateButton.setOnAction(event -> {
                    Customer customer = getTableView().getItems().get(getIndex());
                    handleUpdate(customer);
                });

                deleteButton.setOnAction(event -> {
                    Customer customer = getTableView().getItems().get(getIndex());
                    handleDelete(customer);
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

//    @FXML
//    private void handleSearch() {
//        String searchId = searchField.getText().trim();
//        if (!searchId.isEmpty()) {
//            customerData.clear();
//            // Replace with your actual database/service call
//            customerData.add(new Customer(
//                    searchId,
//                    "John Doe",
//                    "123-456-7890",
//                    "john@example.com",
//                    "123 Main St"
//            ));
//        }
//    }

    private void handleUpdate(Customer customer) {
        System.out.println("Update customer: " + customer.getId());
    }

    private void handleDelete(Customer customer) {
        customerData.remove(customer);
        System.out.println("Delete customer: " + customer.getId());
    }

    @FXML
    private void showAddCustomerForm() {
        System.out.println("Showing add customer form");
    }

    @FXML
    private void showUpdateCustomerForm() {
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            System.out.println("Showing update form for customer: " + selectedCustomer.getId());
        } else {
            System.out.println("Please select a customer to update");
        }
    }

    @FXML
    private void showViewCustomerForm() {
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            System.out.println("Showing view details for customer: " + selectedCustomer.getId());
        } else {
            System.out.println("Please select a customer to view");
        }
    }

    @FXML
    private void showDeleteCustomerForm() {
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            System.out.println("Showing delete confirmation for customer: " + selectedCustomer.getId());
        } else {
            System.out.println("Please select a customer to delete");
        }
    }

    // Placeholder methods for sidebar navigation
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

    public void handleViewSearch(ActionEvent actionEvent) {
    }
}

class Customer {
    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty phone;
    private final StringProperty email;
    private final StringProperty address;

    public Customer(String id, String name, String phone, String email, String address) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
        this.address = new SimpleStringProperty(address);
    }

    // Getter methods must exactly match what PropertyValueFactory expects
    public String getId() { return id.get(); }
    public String getName() { return name.get(); }
    public String getPhone() { return phone.get(); }
    public String getEmail() { return email.get(); }
    public String getAddress() { return address.get(); }

    // Property getters
    public StringProperty idProperty() { return id; }
    public StringProperty nameProperty() { return name; }
    public StringProperty phoneProperty() { return phone; }
    public StringProperty emailProperty() { return email; }
    public StringProperty addressProperty() { return address; }
}