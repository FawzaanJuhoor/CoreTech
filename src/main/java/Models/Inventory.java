package Models;

import javafx.beans.property.*;

import java.time.LocalDateTime;

public class Inventory {
    private IntegerProperty itemId;
    private StringProperty itemName;
    private IntegerProperty quantity;
    private DoubleProperty price;
    private IntegerProperty stockLvl;
    private ObjectProperty<LocalDateTime> lstUpdateDate;

    public Inventory(int itemId, String itemName, int quantity, double price, int minStockLevel, LocalDateTime updatedDate) {
        this.itemId = new SimpleIntegerProperty(itemId);
        this.itemName = new SimpleStringProperty(itemName);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.price = new SimpleDoubleProperty(price);
        this.stockLvl = new SimpleIntegerProperty(minStockLevel);
        this.lstUpdateDate = new SimpleObjectProperty<>(updatedDate);
    }

    // Getters and setters with property methods

    public int getItemId() {
        return itemId.get();
    }

    public void setItemId(int id) {
        this.itemId.set(id);
    }

    public IntegerProperty itemIdProperty() {
        return itemId;
    }

    public String getItemName() {
        return itemName.get();
    }

    public void setItemName(String name) {
        this.itemName.set(name);
    }

    public StringProperty itemNameProperty() {
        return itemName;
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public int getStockLvl() {
        return stockLvl.get();
    }

    public void setStockLvl(int level) {
        this.stockLvl.set(level);
    }

    public IntegerProperty stockLvlProperty() {
        return stockLvl;
    }

    public LocalDateTime getLstUpdateDate() {
        return lstUpdateDate.get();
    }

    public void setLstUpdateDate(LocalDateTime date) {
        this.lstUpdateDate.set(date);
    }

    public ObjectProperty<LocalDateTime> lstUpdateDateProperty() {
        return lstUpdateDate;
    }
}
