package com.coretech.coretech;
import Models.*;

import db.AdminDAO;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Models.MonthlyServiceReport;


import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.time.LocalDate;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;

public class AdminController extends BaseController {

    public Button btnSearchInventoryMng;
    public TextField searchInventoryidName;
    public TextField searchViewAllSalesrep;
    public Button btnSearchViewAllSalesRep;

    @FXML private Label lblTotalEmployees;
    @FXML private Label lblTodayAppointments;

    @FXML private TextField txtItemId;
    @FXML private TextField txtItemName;
    @FXML private TextField txtQuantity;
    @FXML private TextField txtPrice;
    @FXML private TextField txtMinStock;
    //    Dashboard table
    @FXML
    private TableView<ServiceAppointment> mainDashBoardTable;
    @FXML
    private TableColumn<ServiceAppointment, Integer> APPOINTMENTID;
    @FXML
    private TableColumn<ServiceAppointment, Integer> VEHICLEID;
    @FXML
    private TableColumn<ServiceAppointment, Integer> MECHANICID;
    @FXML
    private TableColumn<ServiceAppointment, Integer> USERID;
    @FXML
    private TableColumn<ServiceAppointment, String> SERVICETYPE;
    @FXML
    private TableColumn<ServiceAppointment, LocalDate> SERVICEDATE;
    @FXML
    private TableColumn<ServiceAppointment, String> SERVICESTATUS;


    @FXML public VBox ancpRevenueTracking;
    @FXML public TextField txtUsername;
    @FXML public TextField txtPhone;
    @FXML public TextField txtEmail;
    @FXML public PasswordField txtPassword;

    @FXML public TextField txtUserID;
    @FXML public Button btnSearchId;
    @FXML public TextField txtRemoveSaleUserName;
    @FXML public TextField txtRemoveSalePhone;
    @FXML public TextField txtRemoveSaleEmail;
    @FXML public TextField txtRemoveSalePassword;
    public Label welcomeLabel;
    public Button logoutButton;


    @FXML private TableColumn<NewAdmin, Void> updateDelete;
    @FXML private ComboBox<String> comboRemoveSalesRole;
    @FXML public Button deleteButton;
    @FXML public Button cancelDeleteButton;

    @FXML public TextField searchUpdateSalesrep;
    @FXML public Button btnSearchUpdateSalesRep;
    @FXML public Label lblUpdateSalesUserName;
    @FXML public TextField txtUpdateSalesrepName;
    @FXML public Label lblUpdateSalesPhone;
    @FXML public TextField txtUpdateSalesPhone;
    @FXML public Label lblUpdateSalesRepEmail;
    @FXML public TextField txtUpdateSalesRepEmail;
    @FXML public Label lblUpdateSalesPassword;
    @FXML public PasswordField txtUpdateSalesPassword;
    @FXML public Label lblUpdateSalesRep;
    @FXML private ComboBox<String> comboUpdateSalesRole;
    @FXML public Button updateButton;
    @FXML public Button cancelUpdateButton;

    @FXML private ComboBox<String> comboRole;


    @FXML private TableView<NewAdmin> tableView;
    @FXML private TableColumn<NewAdmin, Integer> userId;
    @FXML private TableColumn<NewAdmin, String> userName;
    @FXML private TableColumn<NewAdmin, String> phoneNo;
    @FXML private TableColumn<NewAdmin, String> role;


//    @FXML private Button btnRevenueTracking;
    @FXML private Button btnInventoryMang;

    @FXML private VBox ancpViewAllSalesRep;
    @FXML private Button btnViewAllSalesRep;

    @FXML private VBox ancpInventoryMng;

    //    Monthly Report Servicing
    @FXML public TextField searchDateYearServicing;
    @FXML public Button btnGenerateServicing;
    @FXML private TableColumn<MonthlyServiceReport, String> custName;
    @FXML private TableColumn<MonthlyServiceReport, String> custVehicle;
    @FXML private TableColumn<MonthlyServiceReport, String> serviceType;
    @FXML private TableColumn<MonthlyServiceReport, Double> cost;
    @FXML private VBox ancpMonthlyReportServicing;
    @FXML private TableView<MonthlyServiceReport> InventoryMntlyRprttableView;
    @FXML public Button btnGeneratePdfMonthlyReportServicing;

    //    Monthly Report Inventory
    @FXML private VBox ancpMonthlyReportInventory;
    @FXML private TableView<MonthlyInventoryReport> ServicingMntlyRprttableView;
    @FXML private TableColumn<MonthlyInventoryReport, String> itemNameService;
    @FXML private TableColumn<MonthlyInventoryReport, Integer> quantityUsed;
    @FXML private TableColumn<MonthlyInventoryReport, Integer> leftStock;
    @FXML
    private TextField searchDateYearInventory;

    //    Monthly Report Revenue Summary
    @FXML private VBox ancpMonthlyReportRevenueSummary;
    @FXML private TableView<RevenueSummary> RevenueMntlyRprttableView;
    @FXML private TableColumn<RevenueSummary, Number> ttlRevenue;
    @FXML private TableColumn<RevenueSummary, Number> ttlInventoryCost;
    @FXML private TableColumn<RevenueSummary, Number> netProfit;

    @FXML private VBox ancpAddSales;
    @FXML private VBox ancpRemoveSales;
    @FXML private VBox ancpUpdateSalesRep;
    @FXML private VBox ancpDashboard;

    @FXML private Button btnAddSalesRep;
    @FXML private Button btnRemoveSalesRep;
    @FXML private Button btnUpdateSalesRep;
    @FXML private Button btnMonthlyReport;

    @FXML private Button btnServicing;
    @FXML private Button btnInventory;
    @FXML private Button btnRevenue;

    @FXML private Button btnServicingInventory;
    @FXML private Button btnInventoryInventory;
    @FXML private Button btnRevenueInventory;

    @FXML private Button btnServicingRevenue;
    @FXML private Button btnInventoryRevenue;
    @FXML private Button btnRevenueRevenue;

    //    Add item to inventory
    @FXML private TableView<Inventory> InventorytableView;
    @FXML private TableColumn<Inventory, String> itemId;
    @FXML private TableColumn<Inventory, String> itemName;
    @FXML private TableColumn<Inventory, Integer> quantity;
    @FXML private TableColumn<Inventory, Double> price;
    @FXML private TableColumn<Inventory, String> stockLvl;
    @FXML private TableColumn<Inventory, String> lstUpdateDate;

    private final ObservableList<Inventory> inventoryList = FXCollections.observableArrayList();



    @FXML
    public void initialize() {

        setWelcomeMessage(welcomeLabel); // Set welcome message from BaseController

        lblTotalEmployees.setText(String.valueOf(AdminDAO.getTotalEmployees()));
        lblTodayAppointments.setText(String.valueOf(AdminDAO.getTodayAppointmentsCount()));

        // Show dashboard initially
        showPanel(ancpDashboard);



        //        Dashoard table
        APPOINTMENTID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        VEHICLEID.setCellValueFactory(new PropertyValueFactory<>("vehicleID"));
        MECHANICID.setCellValueFactory(new PropertyValueFactory<>("mechanicID"));
        USERID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        SERVICETYPE.setCellValueFactory(new PropertyValueFactory<>("serviceType"));
        SERVICEDATE.setCellValueFactory(new PropertyValueFactory<>("serviceDate"));
        SERVICESTATUS.setCellValueFactory(new PropertyValueFactory<>("serviceStatus"));

        mainDashBoardTable.setItems(AdminDAO.getAllServiceAppointments());


        // Assign actions to buttons
        btnAddSalesRep.setOnAction(event -> showPanel(ancpAddSales));
        btnRemoveSalesRep.setOnAction(event -> showPanel(ancpRemoveSales));
        btnUpdateSalesRep.setOnAction(event -> showPanel(ancpUpdateSalesRep));
        btnViewAllSalesRep.setOnAction(event -> showPanel(ancpViewAllSalesRep));
        btnMonthlyReport.setOnAction(event -> showPanel(ancpMonthlyReportServicing));

        btnServicing.setOnAction(event -> showPanel(ancpMonthlyReportServicing));
        btnInventory.setOnAction(event -> showPanel(ancpMonthlyReportInventory));
        btnRevenue.setOnAction(event -> showPanel(ancpMonthlyReportRevenueSummary));

        btnServicingInventory.setOnAction(event -> showPanel(ancpMonthlyReportServicing));
        btnInventoryInventory.setOnAction(event -> showPanel(ancpMonthlyReportInventory));
        btnRevenueInventory.setOnAction(event -> showPanel(ancpMonthlyReportRevenueSummary));

        btnServicingRevenue.setOnAction(event -> showPanel(ancpMonthlyReportServicing));
        btnInventoryRevenue.setOnAction(event -> showPanel(ancpMonthlyReportInventory));
        btnRevenueRevenue.setOnAction(event -> showPanel(ancpMonthlyReportRevenueSummary));

//        btnRevenueTracking.setOnAction(event -> showPanel(ancpRevenueTracking));
        logoutButton.setOnAction(e -> handleLogout());


//        display all sales representative into table
        userId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        userName.setCellValueFactory(new PropertyValueFactory<>("username"));
        phoneNo.setCellValueFactory(new PropertyValueFactory<>("phone"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));

        ObservableList<NewAdmin> adminData = FXCollections.observableArrayList(AdminDAO.getAllAdminsForDisplay());
        tableView.setItems(adminData);

//        Add item into inventory
        // Inventory TableView setup
        InventorytableView.setEditable(true);
        InventorytableView.setItems(AdminDAO.getAllInventoryItems());



//        For monthly report
        ObservableList<MonthlyServiceReport> report = AdminDAO.getMonthlyServiceReport();
        InventoryMntlyRprttableView.setItems(report);


        custName.setCellValueFactory(data -> data.getValue().customerNameProperty());
        custVehicle.setCellValueFactory(data -> data.getValue().vehicleProperty());
        serviceType.setCellValueFactory(data -> data.getValue().serviceTypeProperty());
        cost.setCellValueFactory(data -> data.getValue().costProperty().asObject());

// for monthly report of inventory
        itemNameService.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        quantityUsed.setCellValueFactory(new PropertyValueFactory<>("quantityUsed"));
        leftStock.setCellValueFactory(new PropertyValueFactory<>("leftStock"));
        ObservableList<MonthlyInventoryReport> inventoryReport = AdminDAO.getMonthlyInventoryReport();
        ServicingMntlyRprttableView.setItems(inventoryReport);

//        For monthly report of revenue summary
        ttlRevenue.setCellValueFactory(data -> data.getValue().totalRevenueProperty());
        ttlInventoryCost.setCellValueFactory(data -> data.getValue().totalInventoryCostProperty());
        netProfit.setCellValueFactory(data -> data.getValue().netProfitProperty());
        ObservableList<RevenueSummary> revenueReport = AdminDAO.getRevenueSummaryReport();
        RevenueMntlyRprttableView.setItems(revenueReport);

//        Inventory Managment
        itemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        itemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        stockLvl.setCellValueFactory(new PropertyValueFactory<>("stockLvl")); // or "minStockLevel"
        lstUpdateDate.setCellValueFactory(new PropertyValueFactory<>("lstUpdateDate")); // or "updatedDate"


        // Populate text fields when a row is selected
        InventorytableView.setOnMouseClicked(event -> {
            Inventory selected = InventorytableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                txtItemId.setText(String.valueOf(selected.getItemId()));
                txtItemName.setText(selected.getItemName());
                txtQuantity.setText(String.valueOf(selected.getQuantity()));
                txtPrice.setText(String.valueOf(selected.getPrice()));
                txtMinStock.setText(String.valueOf(selected.getStockLvl()));
            }
        });

        searchInventoryidName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.trim().isEmpty()) {
                // If the search box is cleared, reload all inventory items
                InventorytableView.setItems(AdminDAO.getAllInventoryItems());
            } else {
                // Perform the search
                ObservableList<Inventory> allItems = AdminDAO.getAllInventoryItems();
                ObservableList<Inventory> filteredItems = FXCollections.observableArrayList();

                for (Inventory item : allItems) {
                    String itemIdStr = String.valueOf(item.getItemId());
                    String itemNameStr = item.getItemName().toLowerCase();

                    if (itemIdStr.contains(newValue) || itemNameStr.contains(newValue.toLowerCase())) {
                        filteredItems.add(item);
                    }
                }

                InventorytableView.setItems(filteredItems);
            }
        });


        searchViewAllSalesrep.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.trim().isEmpty()) {
                // Reload all data if search is cleared
                tableView.setItems(FXCollections.observableArrayList(AdminDAO.getAllAdminsForDisplay()));
            } else {
                ObservableList<NewAdmin> allUsers = FXCollections.observableArrayList(AdminDAO.getAllAdminsForDisplay());
                ObservableList<NewAdmin> filteredList = FXCollections.observableArrayList();

                for (NewAdmin admin : allUsers) {
                    if (admin.getUsername().toLowerCase().contains(newValue.toLowerCase())) {
                        filteredList.add(admin);
                    }
                }

                tableView.setItems(filteredList);
            }
        });





    }

    /**
     * Shows the selected VBox panel and hides the others.
     */
    private void showPanel(VBox panelToShow) {
        VBox[] allPanels = {
                ancpDashboard, ancpAddSales, ancpRemoveSales, ancpUpdateSalesRep, ancpViewAllSalesRep,
                ancpInventoryMng, ancpMonthlyReportServicing, ancpMonthlyReportInventory, ancpMonthlyReportRevenueSummary, //ancpRevenueTracking,
        };

        for (VBox panel : allPanels) {
            panel.setVisible(false);
            panel.setManaged(false);
        }

        if (panelToShow != null) {
            panelToShow.setVisible(true);
            panelToShow.setManaged(true);
        }

        highlightButton(panelToShow);
    }

    private void highlightButton(VBox activePanel) {
        // Reset all buttons to default style
        resetButtonStyles();

        // Highlight the active button based on the panel
        if (activePanel == ancpAddSales) {
            btnAddSalesRep.setStyle(HIGHLIGHT_STYLE);
        } else if (activePanel == ancpRemoveSales) {
            btnRemoveSalesRep.setStyle(HIGHLIGHT_STYLE);
        } else if (activePanel == ancpUpdateSalesRep) {
            btnUpdateSalesRep.setStyle(HIGHLIGHT_STYLE);
        } else if (activePanel == ancpViewAllSalesRep) {
            btnViewAllSalesRep.setStyle(HIGHLIGHT_STYLE);
        } else if (activePanel == ancpInventoryMng) {
            btnInventoryMang.setStyle(HIGHLIGHT_STYLE);
        }
//        else if (activePanel == ancpRevenueTracking) {
//            btnRevenueTracking.setStyle(HIGHLIGHT_STYLE);}
        else if (activePanel == ancpMonthlyReportServicing ||
                activePanel == ancpMonthlyReportInventory ||
                activePanel == ancpMonthlyReportRevenueSummary) {
            btnMonthlyReport.setStyle(HIGHLIGHT_STYLE);
        }
    }

    private void resetButtonStyles() {
        String defaultStyle = "-fx-border-color: #2293C3; -fx-text-fill: #2293c2; -fx-background-color: white; -fx-font-family: inder;";
        btnAddSalesRep.setStyle(defaultStyle);
        btnRemoveSalesRep.setStyle(defaultStyle);
        btnUpdateSalesRep.setStyle(defaultStyle);
        btnViewAllSalesRep.setStyle(defaultStyle);
        btnInventoryMang.setStyle(defaultStyle);
        btnMonthlyReport.setStyle(defaultStyle);
//        btnRevenueTracking.setStyle(defaultStyle);
    }

    // Highlighted button style
    private static final String HIGHLIGHT_STYLE = "-fx-background-color: #2293c2; -fx-text-fill: white; -fx-font-family: inder;";

    // Handler methods (if you use them in FXML)


    //    Add, update and delete item in inventory
    @FXML private VBox addItemPane;
    @FXML private VBox updateItemPane;
    @FXML private VBox removeItemPane;

    @FXML private Button btnAddItem;
    @FXML private Button btnUpdateItem;
    @FXML private Button btnDeleteItem;

    @FXML
    void handleAddSales(ActionEvent event) {
        showPanel(ancpAddSales);
    }

    @FXML
    void handleRemoveSales(ActionEvent event) {
        showPanel(ancpRemoveSales);
    }

    @FXML
    void handleUpdateSales(ActionEvent event) {
        showPanel(ancpUpdateSalesRep);
    }

    @FXML
    void handleViewAllSales(ActionEvent event) {
        showPanel(ancpViewAllSalesRep);
    }

    @FXML
    void handleInventoryMng(ActionEvent event) {
        showPanel(ancpInventoryMng);
    }

    @FXML
    void handleMonthlyReport(ActionEvent event) {
        showPanel(ancpMonthlyReportServicing);
    }

    @FXML
    void handleMonthlyReportServicing(ActionEvent event) {
        showPanel(ancpMonthlyReportServicing);
    }

    @FXML
    void handleMonthlyReportInventory(ActionEvent event) {
        showPanel(ancpMonthlyReportInventory);
    }

    @FXML
    void handleMonthlyReportRevenueSummary(ActionEvent event) {
        showPanel(ancpMonthlyReportRevenueSummary);
    }

//    @FXML
//    public void handleRevenueTracking(ActionEvent actionEvent) {
//        showPanel(ancpRevenueTracking);
//    }

    // Left-side section handler stubs (implement as needed)
    @FXML
    private void handleLogout() {
        logout(welcomeLabel); // Use common logout method from BaseController
    }

    @FXML
    void handleServicing(ActionEvent event) { }

    @FXML
    void handleAppointments(ActionEvent event) { try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainAppointmentManagement.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Appointment Management");
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }}


    @FXML
    void handleVehicleManagement(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainVehicleManagement.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Vehicle Management");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleCustomerManagement(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainCustomerManagement.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Customer Management");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleHome(ActionEvent event) {
        showPanel(ancpDashboard);
    }

    @FXML
    void handleUpdateSalesUpdate(ActionEvent event) { }

    @FXML
    void handleUpdateSalesCancle(ActionEvent event) { }

//        Add, update and delete in inventory



    private void refreshDashboardCounts() {
        int totalEmployees = AdminDAO.getTotalEmployees();
        int todayAppointments = AdminDAO.getTodayAppointmentsCount();

        lblTotalEmployees.setText(String.valueOf(totalEmployees));
        lblTodayAppointments.setText(String.valueOf(todayAppointments));
    }


    //Forms Buttons
    public void handleAdd(ActionEvent actionEvent) {
        String username = txtUsername.getText();
        String phone = txtPhone.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String role = comboRole.getValue();

        if (username.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty() || role == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation Error");
            alert.setHeaderText(null);
            alert.setContentText("All fields must be filled.");
            alert.showAndWait();
            return;
        }

        // Validate phone number (10 digits)
        if (!phone.matches("\\d{10}")) {
            showAlert("Invalid Phone", "Phone number must be exactly 10 digits.", Alert.AlertType.WARNING);
            return;
        }

        // Validate email
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            showAlert("Invalid Email", "Please enter a valid email address.", Alert.AlertType.WARNING);
            return;
        }

        // Validate password length (optional)
        if (password.length() < 6) {
            showAlert("Weak Password", "Password must be at least 6 characters long.", Alert.AlertType.WARNING);
            return;
        }

        Admin admin = new Admin(username, phone, email, password, role);
        boolean success = AdminDAO.insertAdmin(admin);

        if (success) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Person added successfully.");
            alert.showAndWait();

            // Optionally clear the form
            txtUsername.clear();
            txtPhone.clear();
            txtEmail.clear();
            txtPassword.clear();
            comboRole.setValue(null);

            // Refresh table data
            ObservableList<NewAdmin> updatedAdminData = FXCollections.observableArrayList(AdminDAO.getAllAdminsForDisplay());
            tableView.setItems(updatedAdminData);

            // Refresh table
            tableView.setItems(FXCollections.observableArrayList(AdminDAO.getAllAdminsForDisplay()));

            // 👇 Refresh dashboard summary
            refreshDashboardCounts();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to add sales representative. Please try again.");
            alert.showAndWait();
        }

    }

    public void handleCancelAdd(ActionEvent actionEvent) {
        clearAddForm();
    }

    private void clearAddForm() {
        txtUsername.clear();
        txtPhone.clear();
        txtEmail.clear();
        txtPassword.clear();
        comboRole.setValue(null);
    }


//Delete Page buttons

    public void handleDeleteSearch(ActionEvent actionEvent) {
        String username = txtUserID.getText().trim();

        if (username.isEmpty()) {
            showAlert("Missing Input", "Please enter a username to search.", Alert.AlertType.WARNING);
            return;
        }

        Admin foundAdmin = AdminDAO.getAdminByUsername(username);
        if (foundAdmin != null) {
            txtRemoveSaleUserName.setText(foundAdmin.getUsername());
            txtRemoveSalePhone.setText(foundAdmin.getPhone());
            txtRemoveSaleEmail.setText(foundAdmin.getEmail());
            txtRemoveSalePassword.setText(foundAdmin.getPassword());
            comboRemoveSalesRole.setValue(foundAdmin.getRole());
        } else {
            showAlert("User Not Found", "No user found with username: " + username, Alert.AlertType.INFORMATION);
        }
    }

    public void handledeletebtn(ActionEvent actionEvent) {
        String username = txtUserID.getText().trim();

        if (username.isEmpty()) {
            showAlert("Missing Input", "Please search and select a user to delete.", Alert.AlertType.WARNING);
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirm Delete");
        confirmation.setHeaderText("Are you sure you want to delete this user?");
        confirmation.setContentText("Username: " + username);

        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean deleted = AdminDAO.deleteAdminByUsername(username);

            if (deleted) {
                showAlert("Success", "User deleted successfully.", Alert.AlertType.INFORMATION);
                clearRemoveForm();

                // Refresh the TableView (if you want to reflect the change)
                ObservableList<NewAdmin> updatedAdminData = FXCollections.observableArrayList(AdminDAO.getAllAdminsForDisplay());
                tableView.setItems(updatedAdminData);

                // ✅ Optional: Clear form
                clearUpdateForm();

                // Refresh table
                tableView.setItems(FXCollections.observableArrayList(AdminDAO.getAllAdminsForDisplay()));

                // 👇 Refresh dashboard summary
                refreshDashboardCounts();

            } else {
                showAlert("Delete Failed", "Could not delete the user. Please try again.", Alert.AlertType.ERROR);
            }
        }
    }

    private void clearRemoveForm() {
        txtUserID.clear();
        txtRemoveSaleUserName.clear();
        txtRemoveSalePhone.clear();
        txtRemoveSaleEmail.clear();
        txtRemoveSalePassword.clear();
        comboRemoveSalesRole.setValue(null);
    }

    public void handlecancelDeleteButton(ActionEvent actionEvent) {
        clearRemoveForm();
    }



    //Update Person Page buttons
    public void handleUpdateSearchbtn(ActionEvent actionEvent) {
        String username = searchUpdateSalesrep.getText().trim();

        if (username.isEmpty()) {
            showAlert("Missing Input", "Please enter a username to search.", Alert.AlertType.WARNING);
            return;
        }

        Admin foundAdmin = AdminDAO.getAdminByUsername(username);
        if (foundAdmin != null) {
            txtUpdateSalesrepName.setText(foundAdmin.getUsername());
            txtUpdateSalesPhone.setText(foundAdmin.getPhone());
            txtUpdateSalesRepEmail.setText(foundAdmin.getEmail());
            txtUpdateSalesPassword.setText(foundAdmin.getPassword());
            comboUpdateSalesRole.setValue(foundAdmin.getRole());
        } else {
            showAlert("User Not Found", "No user found with username: " + username, Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    public void handleupdatebtn(ActionEvent event) {
        String username = txtUpdateSalesrepName.getText();
        String phone = txtUpdateSalesPhone.getText();
        String email = txtUpdateSalesRepEmail.getText();
        String password = txtUpdateSalesPassword.getText();
        String role = comboUpdateSalesRole.getValue();

        if (username.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty() || role == null) {
            showAlert("Validation Error", "All fields must be filled out.", Alert.AlertType.WARNING);
            return;
        }

        Admin admin = new Admin(username, phone, email, password, role);
        boolean updated = AdminDAO.updateAdminByUsername(admin);

        if (updated) {
            showAlert("Success", "Sales representative updated successfully!", Alert.AlertType.INFORMATION);
            // ✅ Refresh the TableView
            ObservableList<NewAdmin> updatedAdminData = FXCollections.observableArrayList(AdminDAO.getAllAdminsForDisplay());
            tableView.setItems(updatedAdminData);

            // ✅ Optional: Clear form
            clearUpdateForm();

            // Refresh table
            tableView.setItems(FXCollections.observableArrayList(AdminDAO.getAllAdminsForDisplay()));

            // 👇 Refresh dashboard summary
            refreshDashboardCounts();

        } else {
            showAlert("Update Failed", "Could not update. Check console for error.", Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearUpdateForm() {
        searchUpdateSalesrep.clear();
        txtUpdateSalesrepName.clear();
        txtUpdateSalesPhone.clear();
        txtUpdateSalesRepEmail.clear();
        txtUpdateSalesPassword.clear();
        comboUpdateSalesRole.setValue(null);
    }

    public void handleCancelUpdate(ActionEvent actionEvent) {
        clearUpdateForm();
    }



    //Add item to inventory
    public void handleAddItemtoInventory(ActionEvent actionEvent) {
        Inventory newItem = new Inventory(
                Integer.parseInt(txtItemId.getText()), // Add this TextField in FXML
                txtItemName.getText(),
                Integer.parseInt(txtQuantity.getText()),
                Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtMinStock.getText()),
                LocalDateTime.now()
        );

        AdminDAO.addInventoryItem(newItem);
        InventorytableView.getItems().add(newItem);

        showAlert("Success", "Item added to inventory successfully.");
    }


    public void handleDeleteItemFromInventory(ActionEvent actionEvent) {
        Inventory selected = InventorytableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            AdminDAO.deleteInventoryItem(selected.getItemId());
            InventorytableView.getItems().remove(selected);
            showAlert("Success", "Item deleted from inventory.");
        } else {
            showAlert("Error", "Please select an item to delete.");
        }
    }

    public void handleUpdateItemToInventory(ActionEvent actionEvent) {
        Inventory selected = InventorytableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setItemName(txtItemName.getText());
            selected.setQuantity(Integer.parseInt(txtQuantity.getText()));
            selected.setPrice(Double.parseDouble(txtPrice.getText()));
            selected.setStockLvl(Integer.parseInt(txtMinStock.getText()));
            selected.setLstUpdateDate(LocalDateTime.now());

            AdminDAO.updateInventoryItem(selected);
            InventorytableView.refresh();

            showAlert("Success", "Inventory item updated.");
        } else {
            showAlert("Error", "Please select an item to update.");
        }
    }




    //    add, update and delete item in inventory
    private void showInventoryPane(VBox paneToShow) {
        VBox[] inventoryPanes = { addItemPane, updateItemPane, removeItemPane };

        for (VBox pane : inventoryPanes) {
            pane.setVisible(false);
            pane.setManaged(false);
        }

        if (paneToShow != null) {
            paneToShow.setVisible(true);
            paneToShow.setManaged(true);
        }
    }


    //PDF generator part
    //    Monthly report of Servicing
    public void handleGeneratePdfMonthlyReportServicing(ActionEvent actionEvent) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.LETTER);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
            PDFont font = PDType1Font.HELVETICA;
            PDFont fontBold = PDType1Font.HELVETICA_BOLD;

            float margin = 50;
            float yStart = page.getMediaBox().getHeight() - margin;
            float y = yStart;
            float rowHeight = 20;
            float tableWidth = page.getMediaBox().getWidth() - 2 * margin;

            // ✅ Dynamic columns
            String[] headers = {"Customer", "Vehicle", "Service", "Cost"};
            int columnCount = headers.length;
            float columnWidth = tableWidth / columnCount;
            float[] columnWidths = new float[columnCount];
            Arrays.fill(columnWidths, columnWidth);

            // ✅ Insert logo top-left
            try {
                File logoFile = new File("src/main/resources/Images/BlackVersion.png");
                if (logoFile.exists()) {
                    PDImageXObject logo = PDImageXObject.createFromFileByContent(logoFile, document);
                    contentStream.drawImage(logo, margin, y - 50, 80, 40); // top-left
                }
            } catch (IOException ioEx) {
                System.out.println("Logo not loaded: " + ioEx.getMessage());
            }

            // ✅ Title next to logo
            contentStream.beginText();
            contentStream.setFont(fontBold, 18);
            contentStream.newLineAtOffset(margin + 100, y - 30); // aligned to right of logo
            contentStream.showText("Monthly Servicing Report");
            contentStream.endText();
            y -= 70;

            // ✅ Header Background
            contentStream.setNonStrokingColor(34, 147, 194);
            contentStream.addRect(margin, y - rowHeight, tableWidth, rowHeight);
            contentStream.fill();

            // ✅ Header Text (white)
            float x = margin;
            for (int i = 0; i < headers.length; i++) {
                contentStream.beginText();
                contentStream.setFont(fontBold, 12);
                contentStream.setNonStrokingColor(Color.WHITE);
                contentStream.newLineAtOffset(x + 5, y - 15);
                contentStream.showText(headers[i]);
                contentStream.endText();
                x += columnWidths[i];
            }

            y -= rowHeight;
            contentStream.setNonStrokingColor(Color.BLACK); // reset

            // ✅ Get data
            ObservableList<MonthlyServiceReport> reportList = InventoryMntlyRprttableView.getItems();

            for (MonthlyServiceReport report : reportList) {
                if (y < margin + rowHeight) {
                    contentStream.close();
                    page = new PDPage(PDRectangle.LETTER);
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    y = yStart - 50;

                    // Redraw header
                    contentStream.setNonStrokingColor(34, 147, 194);
                    contentStream.addRect(margin, y - rowHeight, tableWidth, rowHeight);
                    contentStream.fill();

                    x = margin;
                    for (int i = 0; i < headers.length; i++) {
                        contentStream.beginText();
                        contentStream.setFont(fontBold, 12);
                        contentStream.setNonStrokingColor(Color.WHITE);
                        contentStream.newLineAtOffset(x + 5, y - 15);
                        contentStream.showText(headers[i]);
                        contentStream.endText();
                        x += columnWidths[i];
                    }

                    y -= rowHeight;
                    contentStream.setNonStrokingColor(Color.BLACK);
                }

                // ✅ Data Row
                String[] row = {
                        report.getCustomerName(),
                        report.getVehicle(),
                        report.getServiceType(),
                        report.getCost() > 0 ? String.format("$%.2f", report.getCost()) : ""
                };

                x = margin;
                for (int i = 0; i < row.length; i++) {
                    contentStream.beginText();
                    contentStream.setFont(font, 12);
                    contentStream.newLineAtOffset(x + 5, y - 15);
                    contentStream.showText(row[i]);
                    contentStream.endText();
                    x += columnWidths[i];
                }

                y -= rowHeight;
            }

            // ✅ Draw table borders
            float tableBottomY = y;
            x = margin;

            for (float colWidth : columnWidths) {
                contentStream.moveTo(x, yStart - 70);
                contentStream.lineTo(x, tableBottomY);
                x += colWidth;
            }
            contentStream.moveTo(x, yStart - 70);
            contentStream.lineTo(x, tableBottomY);

            float currentY = yStart - 70;
            for (int i = 0; i <= reportList.size() + 1; i++) {
                contentStream.moveTo(margin, currentY);
                contentStream.lineTo(margin + tableWidth, currentY);
                currentY -= rowHeight;
            }

            // ✅ Footer (date + copyright)
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_OBLIQUE, 10);
            contentStream.newLineAtOffset(margin, 50);
            contentStream.showText("Generated on: " + java.time.LocalDate.now() + "  |  © CoreTech AutoCare");
            contentStream.endText();

            contentStream.stroke();
            contentStream.close();

            // ✅ Save dialog
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save PDF");
            fileChooser.setInitialFileName("MonthlyServicingReport.pdf");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
            File file = fileChooser.showSaveDialog(null);

            if (file != null) {
                document.save(file);
                showAlert("PDF Generated", "The monthly servicing report PDF was successfully created.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Could not generate PDF: " + e.getMessage());
        }
    }







    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    //    Monthly report of Inventory
    public void handleGeneratePdfMonthlyReportInventory(ActionEvent actionEvent) {
        ObservableList<MonthlyInventoryReport> reportData = ServicingMntlyRprttableView.getItems();

        if (reportData == null || reportData.isEmpty()) {
            showAlert("No Data", "No data available to export.");
            return;
        }

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.LETTER);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
            PDFont font = PDType1Font.HELVETICA;
            PDFont fontBold = PDType1Font.HELVETICA_BOLD;

            float margin = 50;
            float yStart = page.getMediaBox().getHeight() - margin;
            float y = yStart;
            float rowHeight = 20;
            float tableWidth = page.getMediaBox().getWidth() - 2 * margin;

            // ✅ Dynamic column setup
            String[] headers = {"Item Name", "Quantity Used", "Remaining Stock"};
            int columnCount = headers.length;
            float columnWidth = tableWidth / columnCount;
            float[] columnWidths = new float[columnCount];
            Arrays.fill(columnWidths, columnWidth);

            // ✅ Logo
            try {
                File logoFile = new File("src/main/resources/Images/BlackVersion.png");
                if (logoFile.exists()) {
                    PDImageXObject logo = PDImageXObject.createFromFileByContent(logoFile, document);
                    contentStream.drawImage(logo, margin, y - 50, 80, 40);
                }
            } catch (IOException ioEx) {
                System.out.println("Logo not loaded: " + ioEx.getMessage());
            }

            // ✅ Title
            contentStream.beginText();
            contentStream.setFont(fontBold, 18);
            contentStream.newLineAtOffset(margin + 100, y - 30);
            contentStream.showText("Monthly Inventory Report");
            contentStream.endText();
            y -= 70;

            // ✅ Header Row Background
            contentStream.setNonStrokingColor(34, 147, 194); // #2293c2
            contentStream.addRect(margin, y - rowHeight, tableWidth, rowHeight);
            contentStream.fill();

            // ✅ Header Text (white)
            float x = margin;
            for (int i = 0; i < headers.length; i++) {
                contentStream.beginText();
                contentStream.setFont(fontBold, 12);
                contentStream.setNonStrokingColor(Color.WHITE);
                contentStream.newLineAtOffset(x + 5, y - 15);
                contentStream.showText(headers[i]);
                contentStream.endText();
                x += columnWidths[i];
            }

            y -= rowHeight;
            contentStream.setNonStrokingColor(Color.BLACK); // reset

            // ✅ Data Rows
            for (MonthlyInventoryReport item : reportData) {
                if (y < margin + rowHeight) {
                    contentStream.close();
                    page = new PDPage(PDRectangle.LETTER);
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    y = yStart - 50;

                    // Redraw header
                    contentStream.setNonStrokingColor(34, 147, 194);
                    contentStream.addRect(margin, y - rowHeight, tableWidth, rowHeight);
                    contentStream.fill();

                    x = margin;
                    for (int i = 0; i < headers.length; i++) {
                        contentStream.beginText();
                        contentStream.setFont(fontBold, 12);
                        contentStream.setNonStrokingColor(Color.WHITE);
                        contentStream.newLineAtOffset(x + 5, y - 15);
                        contentStream.showText(headers[i]);
                        contentStream.endText();
                        x += columnWidths[i];
                    }

                    y -= rowHeight;
                    contentStream.setNonStrokingColor(Color.BLACK);
                }

                String[] row = {
                        item.getItemName(),
                        String.valueOf(item.getQuantityUsed()),
                        String.valueOf(item.getLeftStock())
                };

                x = margin;
                for (int i = 0; i < row.length; i++) {
                    contentStream.beginText();
                    contentStream.setFont(font, 12);
                    contentStream.newLineAtOffset(x + 5, y - 15);
                    contentStream.showText(row[i]);
                    contentStream.endText();
                    x += columnWidths[i];
                }

                y -= rowHeight;
            }

            // ✅ Draw Borders
            float tableBottomY = y;
            x = margin;

            // Vertical lines
            for (float colWidth : columnWidths) {
                contentStream.moveTo(x, yStart - 70);
                contentStream.lineTo(x, tableBottomY);
                x += colWidth;
            }
            contentStream.moveTo(x, yStart - 70);
            contentStream.lineTo(x, tableBottomY);

            // Horizontal lines
            float currentY = yStart - 70;
            for (int i = 0; i <= reportData.size() + 1; i++) {
                contentStream.moveTo(margin, currentY);
                contentStream.lineTo(margin + tableWidth, currentY);
                currentY -= rowHeight;
            }

            // ✅ Footer (date + copyright)
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_OBLIQUE, 10);
            contentStream.newLineAtOffset(margin, 50);
            contentStream.showText("Generated on: " + java.time.LocalDate.now() + "  |  © CoreTech AutoCare");
            contentStream.endText();

            contentStream.stroke();
            contentStream.close();

            // ✅ Save Dialog
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save PDF");
            fileChooser.setInitialFileName("MonthlyInventoryReport.pdf");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
            File file = fileChooser.showSaveDialog(null);

            if (file != null) {
                document.save(file);
                showAlert("PDF Generated", "The Monthly Inventory Report PDF was successfully created.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Could not generate PDF: " + e.getMessage());
        }
    }




    public void handleGeneratePdfMonthlyReportRevenue(ActionEvent actionEvent) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.LETTER);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
            PDFont font = PDType1Font.HELVETICA;
            PDFont fontBold = PDType1Font.HELVETICA_BOLD;

            float margin = 50;
            float yStart = page.getMediaBox().getHeight() - margin;
            float y = yStart;
            float rowHeight = 20;
            float tableWidth = page.getMediaBox().getWidth() - 2 * margin;

            // ✅ Columns setup
            String[] headers = {"Total Revenue", "Inventory Cost", "Net Profit"};
            int columnCount = headers.length;
            float columnWidth = tableWidth / columnCount;
            float[] columnWidths = new float[columnCount];
            Arrays.fill(columnWidths, columnWidth);

            // ✅ Logo
            try {
                File logoFile = new File("src/main/resources/Images/BlackVersion.png");
                if (logoFile.exists()) {
                    PDImageXObject logo = PDImageXObject.createFromFileByContent(logoFile, document);
                    contentStream.drawImage(logo, margin, y - 50, 80, 40);
                }
            } catch (IOException ioEx) {
                System.out.println("Logo not loaded: " + ioEx.getMessage());
            }

            // ✅ Title
            contentStream.beginText();
            contentStream.setFont(fontBold, 18);
            contentStream.newLineAtOffset(margin + 100, y - 30);
            contentStream.showText("Monthly Revenue Summary");
            contentStream.endText();
            y -= 70;

            // ✅ Header Row Background
            contentStream.setNonStrokingColor(34, 147, 194); // #2293c2
            contentStream.addRect(margin, y - rowHeight, tableWidth, rowHeight);
            contentStream.fill();

            // ✅ Header Text
            float x = margin;
            for (int i = 0; i < headers.length; i++) {
                contentStream.beginText();
                contentStream.setFont(fontBold, 12);
                contentStream.setNonStrokingColor(Color.WHITE);
                contentStream.newLineAtOffset(x + 5, y - 15);
                contentStream.showText(headers[i]);
                contentStream.endText();
                x += columnWidths[i];
            }

            y -= rowHeight;
            contentStream.setNonStrokingColor(Color.BLACK);

            // ✅ Get data
            ObservableList<RevenueSummary> revenueData = AdminDAO.getRevenueSummaryReport();

            // ✅ Data Rows
            for (RevenueSummary summary : revenueData) {
                if (y < margin + rowHeight) {
                    contentStream.close();
                    page = new PDPage(PDRectangle.LETTER);
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    y = yStart - 50;

                    // Redraw headers
                    contentStream.setNonStrokingColor(34, 147, 194);
                    contentStream.addRect(margin, y - rowHeight, tableWidth, rowHeight);
                    contentStream.fill();

                    x = margin;
                    for (int i = 0; i < headers.length; i++) {
                        contentStream.beginText();
                        contentStream.setFont(fontBold, 12);
                        contentStream.setNonStrokingColor(Color.WHITE);
                        contentStream.newLineAtOffset(x + 5, y - 15);
                        contentStream.showText(headers[i]);
                        contentStream.endText();
                        x += columnWidths[i];
                    }

                    y -= rowHeight;
                    contentStream.setNonStrokingColor(Color.BLACK);
                }

                String[] row = {
                        String.format("$%.2f", summary.getTotalRevenue()),
                        String.format("$%.2f", summary.getTotalInventoryCost()),
                        String.format("$%.2f", summary.getNetProfit())
                };

                x = margin;
                for (int i = 0; i < row.length; i++) {
                    contentStream.beginText();
                    contentStream.setFont(font, 12);
                    contentStream.newLineAtOffset(x + 5, y - 15);
                    contentStream.showText(row[i]);
                    contentStream.endText();
                    x += columnWidths[i];
                }

                y -= rowHeight;
            }

            // ✅ Draw Table Grid
            float tableBottomY = y;
            x = margin;

            for (float colWidth : columnWidths) {
                contentStream.moveTo(x, yStart - 70);
                contentStream.lineTo(x, tableBottomY);
                x += colWidth;
            }
            contentStream.moveTo(x, yStart - 70);
            contentStream.lineTo(x, tableBottomY);

            float currentY = yStart - 70;
            for (int i = 0; i <= revenueData.size() + 1; i++) {
                contentStream.moveTo(margin, currentY);
                contentStream.lineTo(margin + tableWidth, currentY);
                currentY -= rowHeight;
            }

            contentStream.stroke();

            // ✅ Footer (date)
            // ✅ Footer (date + copyright)
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_OBLIQUE, 10);
            contentStream.newLineAtOffset(margin, 50);
            contentStream.showText("Generated on: " + java.time.LocalDate.now() + "  |  © CoreTech AutoCare");
            contentStream.endText();


            contentStream.close();

            // ✅ Save Dialog
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save PDF");
            fileChooser.setInitialFileName("MonthlyRevenueSummary.pdf");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
            File file = fileChooser.showSaveDialog(null);

            if (file != null) {
                document.save(file);
                showAlert("PDF Generated", "The monthly Revenue Summary report PDF was successfully created.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Could not generate PDF: " + e.getMessage());
        }
    }





    public ComboBox<String> getComboRemoveSalesRole() {
        return comboRemoveSalesRole;
    }

    public void setComboRemoveSalesRole(ComboBox<String> comboRemoveSalesRole) {
        this.comboRemoveSalesRole = comboRemoveSalesRole;
    }


    public void handleSearchInventoryMng(ActionEvent actionEvent) {
        String keyword = searchInventoryidName.getText().trim();

        if (keyword.isEmpty()) {
            InventorytableView.setItems(AdminDAO.getAllInventoryItems()); // reload all if blank
            return;
        }

        ObservableList<Inventory> allItems = AdminDAO.getAllInventoryItems();
        ObservableList<Inventory> filteredItems = FXCollections.observableArrayList();

        for (Inventory item : allItems) {
            String itemIdStr = String.valueOf(item.getItemId());
            String itemNameStr = item.getItemName().toLowerCase();

            if (itemIdStr.contains(keyword) || itemNameStr.contains(keyword.toLowerCase())) {
                filteredItems.add(item);
            }
        }

        InventorytableView.setItems(filteredItems);
    }

    public void handleSearchViewAllSalesRep(ActionEvent actionEvent) {
        String keyword = searchViewAllSalesrep.getText().trim().toLowerCase();

        if (keyword.isEmpty()) {
            // Reload all data if search is cleared
            tableView.setItems(FXCollections.observableArrayList(AdminDAO.getAllAdminsForDisplay()));
            return;
        }

        ObservableList<NewAdmin> allUsers = FXCollections.observableArrayList(AdminDAO.getAllAdminsForDisplay());
        ObservableList<NewAdmin> filteredList = FXCollections.observableArrayList();

        for (NewAdmin admin : allUsers) {
            if (admin.getUsername().toLowerCase().contains(keyword)) {
                filteredList.add(admin);
            }
        }

        tableView.setItems(filteredList);
    }
}