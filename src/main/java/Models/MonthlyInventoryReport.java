package Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MonthlyInventoryReport {
    private SimpleStringProperty itemName;
    private SimpleIntegerProperty quantityUsed;
    private SimpleIntegerProperty leftStock;

    public MonthlyInventoryReport(String itemName, int quantityUsed, int leftStock) {
        this.itemName = new SimpleStringProperty(itemName);
        this.quantityUsed = new SimpleIntegerProperty(quantityUsed);
        this.leftStock = new SimpleIntegerProperty(leftStock);
    }

    public String getItemName() {
        return itemName.get();
    }

    public SimpleStringProperty itemNameProperty() {
        return itemName;
    }

    public int getQuantityUsed() {
        return quantityUsed.get();
    }

    public SimpleIntegerProperty quantityUsedProperty() {
        return quantityUsed;
    }

    public int getLeftStock() {
        return leftStock.get();
    }

    public SimpleIntegerProperty leftStockProperty() {
        return leftStock;
    }
}
