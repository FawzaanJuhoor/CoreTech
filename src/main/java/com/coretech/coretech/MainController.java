package com.coretech.coretech;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MainController {

    @FXML
    private AnchorPane ancpAddSales;

    @FXML
    private AnchorPane ancpRemoveSales;

    @FXML
    private Button btnAddSalesRep;

    @FXML
    private Button btnRemoveSalesRep;

    @FXML
    public void initialize() {
        // Initially hide both panels
        showPanel(null);

        // Set button actions
        btnAddSalesRep.setOnAction(event -> showPanel(ancpAddSales));
        btnRemoveSalesRep.setOnAction(event -> showPanel(ancpRemoveSales));
    }

    /**
     * Shows the selected AnchorPane and hides others.
     */
    private void showPanel(AnchorPane panelToShow) {
        // Hide all panels first
        ancpAddSales.setVisible(false);
        ancpRemoveSales.setVisible(false);

        // Show only the selected panel if it's not null
        if (panelToShow != null) {
            panelToShow.setVisible(true);
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
}
