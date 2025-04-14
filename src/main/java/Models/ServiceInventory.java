package Models;

public class ServiceInventory {
    private final int appointmentId;
    private final int itemId;
    private final int quantity;

    public ServiceInventory(int appointmentId, int itemId, int quantity) {
        this.appointmentId = appointmentId;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public int getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }
}
