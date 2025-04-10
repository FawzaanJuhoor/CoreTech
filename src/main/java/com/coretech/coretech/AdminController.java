package com.coretech.coretech;
import Models.*;

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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Models.MonthlyServiceReport;


import java.io.IOException;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.WindowEvent;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;

public class AdminController extends BaseController {

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

    @FXML private VBox ancpInventoryMng;

    //    Monthly Report Servicing
    @FXML public TextField searchDateYearServicing;
    @FXML public Button btnGenerateServicing;
    @FXML private TableColumn<MonthlyServiceReport, String> custName;
    @FXML private TableColumn<MonthlyServiceReport, String> custVehicle;
    @FXML private TableColumn<MonthlyServiceReport, String> serviceType;
    @FXML private TableColumn<MonthlyServiceReport, Double> cost;
    @FXML private VBox ancpMonthlyReportServicing;
    @FXML private TableView<MonthlyServiceReport> InventoryMntlyRprttableView;    @FXML public Button btnGeneratePdfMonthlyReportServicing;

    //    Monthly Report Inventory
    @FXML private VBox ancpMonthlyReportInventory;
    @FXML private TableView<MonthlyServiceReport> ServicingMntlyRprttableView;

    //    Monthly Report Revenue Summary
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
        InventorytableView.setItems(inventoryList);

// Column bindings
        itemId.setCellValueFactory(cellData -> cellData.getValue().itemIdProperty());
        itemId.setCellFactory(TextFieldTableCell.forTableColumn());
        itemId.setOnEditCommit(event -> event.getRowValue().setItemId(event.getNewValue()));

        itemName.setCellValueFactory(cellData -> cellData.getValue().itemNameProperty());
        itemName.setCellFactory(TextFieldTableCell.forTableColumn());
        itemName.setOnEditCommit(event -> event.getRowValue().setItemName(event.getNewValue()));

        quantity.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        quantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        quantity.setOnEditCommit(event -> event.getRowValue().setQuantity(event.getNewValue()));

        price.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        price.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        price.setOnEditCommit(event -> event.getRowValue().setPrice(event.getNewValue()));

        stockLvl.setCellValueFactory(cellData -> cellData.getValue().stockLvlProperty());
        stockLvl.setCellFactory(TextFieldTableCell.forTableColumn());
        stockLvl.setOnEditCommit(event -> event.getRowValue().setStockLvl(event.getNewValue()));

        lstUpdateDate.setCellValueFactory(cellData -> cellData.getValue().lstUpdateDateProperty());
        lstUpdateDate.setCellFactory(TextFieldTableCell.forTableColumn());
        lstUpdateDate.setOnEditCommit(event -> event.getRowValue().setLstUpdateDate(event.getNewValue()));


//        For monthly report
        ObservableList<MonthlyServiceReport> report = AdminDAO.getMonthlyServiceReport();
        InventoryMntlyRprttableView.setItems(report);


        custName.setCellValueFactory(data -> data.getValue().customerNameProperty());
        custVehicle.setCellValueFactory(data -> data.getValue().vehicleProperty());
        serviceType.setCellValueFactory(data -> data.getValue().serviceTypeProperty());
        cost.setCellValueFactory(data -> data.getValue().costProperty().asObject());



    }


    /**
     * Shows the selected VBox panel and hides the others.
     */
    private void showPanel(VBox panelToShow) {
        VBox[] allPanels = {
                ancpDashboard, ancpAddSales, ancpRemoveSales, ancpUpdateSalesRep, ancpViewAllSalesRep,
                ancpInventoryMng, ancpMonthlyReportServicing, ancpMonthlyReportInventory, ancpMonthlyReportRevenueSummary, ancpRevenueTracking,
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
        else if (activePanel == ancpRevenueTracking) {
            btnRevenueTracking.setStyle(HIGHLIGHT_STYLE);}
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
        btnRevenueTracking.setStyle(defaultStyle);
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

    @FXML
    public void handleRevenueTracking(ActionEvent actionEvent) {
        showPanel(ancpRevenueTracking);
    }

    // Left-side section handler stubs (implement as needed)
    @FXML
    private void handleLogout() {
        logout(welcomeLabel); // Use common logout method from BaseController
    }

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

//        Add, update and delete in inventory





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



    //Add item to inventory
    public void handleAddItemtoInventory(ActionEvent actionEvent) {
        Inventory newItem = new Inventory("", "", 0, 0.0, "", "");
        inventoryList.add(newItem);
        InventorytableView.scrollTo(newItem);
    }


    public void handleDeleteItemFromInventory(ActionEvent actionEvent) {
    }

    public void handleUpdateItemToInventory(ActionEvent actionEvent) {
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
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            float yStart = 750;
            float margin = 50;
            float leading = 20;

            // Title
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
            contentStream.newLineAtOffset(margin, yStart);
            contentStream.showText("Monthly Servicing Report");
            contentStream.endText();

            float y = yStart - 30;

            // Table Headers
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.newLineAtOffset(margin, y);
            contentStream.showText(String.format("%-20s %-20s %-20s %-10s", "Customer", "Vehicle", "Service", "Cost"));
            contentStream.endText();
            y -= leading;

            // Loop through real TableView data
            ObservableList<MonthlyServiceReport> reportList = InventoryMntlyRprttableView.getItems();

            contentStream.setFont(PDType1Font.HELVETICA, 12);
            for (MonthlyServiceReport report : reportList) {
                String customer = report.getCustomerName();
                String vehicle = report.getVehicle();
                String service = report.getServiceType();
                String costStr = report.getCost() > 0 ? String.format("$%.2f", report.getCost()) : "";

                contentStream.beginText();
                contentStream.newLineAtOffset(margin, y);
                contentStream.showText(String.format("%-20s %-20s %-20s %-10s", customer, vehicle, service, costStr));
                contentStream.endText();
                y -= leading;

                // Start new page if needed
                if (y < 50) {
                    contentStream.close();
                    page = new PDPage();
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    y = yStart;
                }
            }

            contentStream.close();

            // Save Dialog
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
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            float yStart = 750;
            float margin = 50;
            float leading = 20;

            // Title
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
            contentStream.newLineAtOffset(margin, yStart);
            contentStream.showText("Monthly Revenue Summary");
            contentStream.endText();

            float y = yStart - 30;

            // Table Headers
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.newLineAtOffset(margin, y);
            contentStream.showText(String.format("%-20s %-20s %-20s %-10s", "Customer", "Vehicle", "Service", "Cost"));
            contentStream.endText();
            y -= leading;

            // Sample Hardcoded Rows
            String[][] rows = {
                    {"Alice Johnson", "Honda Civic", "Oil Change", "$80"},
                    {"Bob Singh", "Toyota Corolla", "Brake Repair", "$150"},
                    {"Ravi Kumar", "Hyundai Elantra", "Tire Rotation", "$50"},
                    {"Jessica Brown", "Ford Escape", "Engine Diagnostics", "$120"}
            };

            contentStream.setFont(PDType1Font.HELVETICA, 12);
            for (String[] row : rows) {
                contentStream.beginText();
                contentStream.newLineAtOffset(margin, y);
                contentStream.showText(String.format("%-20s %-20s %-20s %-10s", row[0], row[1], row[2], row[3]));
                contentStream.endText();
                y -= leading;
            }

            contentStream.close();

            // Save Dialog
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


    //    Monthly report of Revenue Summary
    public void handleGeneratePdfMonthlyReportRevenue(ActionEvent actionEvent) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            float yStart = 750;
            float margin = 50;
            float leading = 20;

            // Title
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
            contentStream.newLineAtOffset(margin, yStart);
            contentStream.showText("Monthly Revenue Summary");
            contentStream.endText();

            float y = yStart - 30;

            // Table Headers
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.newLineAtOffset(margin, y);
            contentStream.showText(String.format("%-20s %-20s %-20s %-10s", "Customer", "Vehicle", "Service", "Cost"));
            contentStream.endText();
            y -= leading;

            // Sample Hardcoded Rows
            String[][] rows = {
                    {"Alice Johnson", "Honda Civic", "Oil Change", "$80"},
                    {"Bob Singh", "Toyota Corolla", "Brake Repair", "$150"},
                    {"Ravi Kumar", "Hyundai Elantra", "Tire Rotation", "$50"},
                    {"Jessica Brown", "Ford Escape", "Engine Diagnostics", "$120"}
            };

            contentStream.setFont(PDType1Font.HELVETICA, 12);
            for (String[] row : rows) {
                contentStream.beginText();
                contentStream.newLineAtOffset(margin, y);
                contentStream.showText(String.format("%-20s %-20s %-20s %-10s", row[0], row[1], row[2], row[3]));
                contentStream.endText();
                y -= leading;
            }

            contentStream.close();

            // Save Dialog
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



}