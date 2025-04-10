package Models;

import javafx.beans.property.*;

public class Inventory {
    private final SimpleStringProperty itemId;
    private final SimpleStringProperty itemName;
    private final SimpleIntegerProperty quantity;
    private final SimpleDoubleProperty price;
    private final SimpleStringProperty stockLvl;
    private final SimpleStringProperty lstUpdateDate;

    public Inventory(String itemId, String itemName, int quantity, double price, String stockLvl, String lstUpdateDate) {
        this.itemId = new SimpleStringProperty(itemId);
        this.itemName = new SimpleStringProperty(itemName);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.price = new SimpleDoubleProperty(price);
        this.stockLvl = new SimpleStringProperty(stockLvl);
        this.lstUpdateDate = new SimpleStringProperty(lstUpdateDate);
    }

    // Getters and setters with property methods
    public String getItemId() { return itemId.get(); }
    public void setItemId(String id) { itemId.set(id); }
    public StringProperty itemIdProperty() { return itemId; }

    public String getItemName() { return itemName.get(); }
    public void setItemName(String name) { itemName.set(name); }
    public StringProperty itemNameProperty() { return itemName; }

    public int getQuantity() { return quantity.get(); }
    public void setQuantity(int quantity) { this.quantity.set(quantity); }
    public IntegerProperty quantityProperty() { return quantity; }

    public double getPrice() { return price.get(); }
    public void setPrice(double price) { this.price.set(price); }
    public DoubleProperty priceProperty() { return price; }

    public String getStockLvl() { return stockLvl.get(); }
    public void setStockLvl(String level) { stockLvl.set(level); }
    public StringProperty stockLvlProperty() { return stockLvl; }

    public String getLstUpdateDate() { return lstUpdateDate.get(); }
    public void setLstUpdateDate(String date) { lstUpdateDate.set(date); }
    public StringProperty lstUpdateDateProperty() { return lstUpdateDate; }
}

