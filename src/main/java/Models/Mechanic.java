package Models;

/**
 * Represents a mechanic in the system with their identification, name, and area of expertise.
 */
public class Mechanic {

    /** The unique identifier for the mechanic. */
    private int mechanicId;

    /** The name of the mechanic. */
    private String mechanicName;

    /** The area of expertise or specialization of the mechanic (e.g., engine repair, brakes). */
    private String expertise;

    /**
     * Constructs a new Mechanic object with the specified details.
     *
     * @param mechanicId the unique identifier for the mechanic
     * @param mechanicName the name of the mechanic
     * @param expertise the area of expertise of the mechanic
     */
    public Mechanic(int mechanicId, String mechanicName, String expertise) {
        this.mechanicId = mechanicId;
        this.mechanicName = mechanicName;
        this.expertise = expertise;
    }

    /**
     * Returns the mechanic's ID.
     *
     * @return the mechanic ID
     */
    public int getMechanicId() {
        return mechanicId;
    }

    /**
     * Returns a string representation of the Mechanic object, combining the name and expertise.
     *
     * @return a string in the format "mechanicName - expertise"
     */
    @Override
    public String toString() {
        return mechanicName + " - " + expertise;
    }
}
