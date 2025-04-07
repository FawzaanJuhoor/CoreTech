package com.coretech.coretech;

import Models.Admin;
import Models.NewAdmin;
import db.AdminDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class AdminController {

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
    @FXML public TextField txtUpdateSalesPassword;
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


    @FXML private Button btnRevenueTracking;
    @FXML private Button btnInventoryMang;

    @FXML private VBox ancpViewAllSalesRep;
    @FXML private Button btnViewAllSalesRep;

    @FXML private TableView<?> InventorytableView;
    @FXML private VBox ancpInventoryMng;

    @FXML private VBox ancpMonthlyReportServicing;
    @FXML private TableView<?> InventoryMntlyRprttableView;

    @FXML private VBox ancpMonthlyReportInventory;
    @FXML private TableView<?> ServicingMntlyRprttableView;

    @FXML private VBox ancpMonthlyReportRevenueSummary;
    @FXML private TableView<?> RevenueMntlyRprttableView;

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

    @FXML
    public void initialize() {
        // Show dashboard initially
        showPanel(ancpDashboard);

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

        btnRevenueTracking.setOnAction(event -> showPanel(ancpRevenueTracking));



//        display all sales representative into table
        userId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        userName.setCellValueFactory(new PropertyValueFactory<>("username"));
        phoneNo.setCellValueFactory(new PropertyValueFactory<>("phone"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));

        ObservableList<NewAdmin> adminData = FXCollections.observableArrayList(AdminDAO.getAllAdminsForDisplay());
        tableView.setItems(adminData);


    }

    /**
     * Shows the selected VBox panel and hides the others.
     */
    private void showPanel(VBox panelToShow) {
        VBox[] allPanels = {
                ancpDashboard, ancpAddSales, ancpRemoveSales, ancpUpdateSalesRep, ancpViewAllSalesRep,
                ancpInventoryMng, ancpMonthlyReportServicing, ancpMonthlyReportInventory, ancpMonthlyReportRevenueSummary, ancpRevenueTracking
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
        } else if (activePanel == ancpRevenueTracking) {
            btnRevenueTracking.setStyle(HIGHLIGHT_STYLE);
        }else if (activePanel == ancpMonthlyReportServicing ||
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
        btnRevenueTracking.setStyle(defaultStyle);
    }

    // Highlighted button style
    private static final String HIGHLIGHT_STYLE = "-fx-background-color: #2293c2; -fx-text-fill: white; -fx-font-family: inder;";

    // Handler methods (if you use them in FXML)

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

    @FXML
    public void handleRevenueTracking(ActionEvent actionEvent) {
        showPanel(ancpRevenueTracking);
    }

    // Left-side section handler stubs (implement as needed)
    @FXML
    void handleLogout(ActionEvent event) { }

    @FXML
    void handleServicing(ActionEvent event) { }

    @FXML
    void handleAppointments(ActionEvent event) { }

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


    
    
    
    

    public void handleDeleteItemFromInventory(ActionEvent actionEvent) {
    }

    public void handleUpdateItemToInventory(ActionEvent actionEvent) {
    }

    public void handleAddItemtoInventory(ActionEvent actionEvent) {
    }


    public void handleGeneratePdfMonthlyReportServicing(ActionEvent actionEvent) {
    }

    public void handleGeneratePdfMonthlyReportInventory(ActionEvent actionEvent) {
    }

    public void handleGeneratePdfMonthlyReportRevenue(ActionEvent actionEvent) {
    }


    public ComboBox<String> getComboRemoveSalesRole() {
        return comboRemoveSalesRole;
    }

    public void setComboRemoveSalesRole(ComboBox<String> comboRemoveSalesRole) {
        this.comboRemoveSalesRole = comboRemoveSalesRole;
    }


}
