package com.coretech.coretech;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainController {

    @FXML
    private VBox ancpAddSales;

    @FXML
    private VBox ancpRemoveSales;

    @FXML
    private VBox ancpUpdateSalesRep;

    @FXML
    private Button btnAddSalesRep;

    @FXML
    private Button btnRemoveSalesRep;

    @FXML
    private Button btnUpdateSalesRep;

    @FXML
    public void initialize() {
        // Initially hide both panels
        showPanel(null);

        // Set button actions
        btnAddSalesRep.setOnAction(event -> showPanel(ancpAddSales));
        btnRemoveSalesRep.setOnAction(event -> showPanel(ancpRemoveSales));
        btnUpdateSalesRep.setOnAction(event -> showPanel(ancpUpdateSalesRep));
    }

    /**
     * Shows the selected AnchorPane and hides others.
     */
    private void showPanel(VBox panelToShow) {
        // Hide all panels first
        ancpAddSales.setVisible(false);
        ancpAddSales.setManaged(false);
        ancpRemoveSales.setVisible(false);
        ancpRemoveSales.setManaged(false);
        ancpUpdateSalesRep.setVisible(false);
        ancpUpdateSalesRep.setManaged(false);

        // Show only the selected panel if it's not null
        if (panelToShow != null) {
            panelToShow.setVisible(true);
            panelToShow.setManaged(true);
        }
    }

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

    public void handleLogout(ActionEvent actionEvent) {
    }

    public void handleServicing(ActionEvent actionEvent) {
    }

    public void handleAppointments(ActionEvent actionEvent) {
    }

    public void handleVehicleManagement(ActionEvent actionEvent) {
    }

    public void handleCustomerManagement(ActionEvent actionEvent) {
    }

    public void handleHome(ActionEvent actionEvent) {
    }

    public void handleUpdateSalesUpdate(ActionEvent actionEvent) {
    }

    public void handleUpdateSalesCancle(ActionEvent actionEvent) {
    }
}
