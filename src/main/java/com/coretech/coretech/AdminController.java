package com.coretech.coretech;

import Models.Admin;
import db.AdminDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminController {

    @FXML public VBox ancpRevenueTracking;
    public TextField txtUsername;
    public TextField txtPhone;
    public TextField txtEmail;
    public PasswordField txtPassword;
    @FXML
    private ComboBox<String> comboRole;
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
            System.out.println("All fields must be filled.");
            return;
        }

        Admin admin = new Admin(username, phone, email, password, role);
        AdminDAO.insertAdmin(admin);
    }

    public void handleCancelAdd(ActionEvent actionEvent) {
    }
}
