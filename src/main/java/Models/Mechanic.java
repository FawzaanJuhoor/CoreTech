package Models;

public class Mechanic {
    private int mechanicId;
    private String mechanicName;
    private String expertise;

    public Mechanic(int mechanicId, String mechanicName, String expertise) {
        this.mechanicId = mechanicId;
        this.mechanicName = mechanicName;
        this.expertise = expertise;
    }

    public int getMechanicId() {
        return mechanicId;
    }

    @Override
    public String toString() {
        return mechanicName + " - " + expertise;
    }
}
