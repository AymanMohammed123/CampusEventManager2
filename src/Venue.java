// This is the parent class for all venue types.
// It stores the common information shared by every venue.
public abstract class Venue {
    // Common venue fields.
    private final String venueName;
    private final int maximumCapacity;

    // This constructor sets the common venue data.
    protected Venue(String venueName, int maximumCapacity) {
        if (maximumCapacity <= 0) {
            throw new IllegalArgumentException("Maximum capacity must be greater than 0.");
        }

        this.venueName = venueName;
        this.maximumCapacity = maximumCapacity;
    }

    // Getter methods return the values of private fields.
    public String getVenueName() {
        return venueName;
    }

    public int getMaximumCapacity() {
        return maximumCapacity;
    }

    // This method creates one formatted block of text
    // to display all venue information.
    public String getDisplayDetails() {
        // Each venue subclass contributes its unique attributes through getSpecificDetails().
        return "Venue Type: " + getVenueType() + "\n"
                + "Venue Name: " + venueName + "\n"
                + "Maximum Capacity: " + maximumCapacity + "\n"
                + getSpecificDetails();
    }

    // Each child class returns its venue type name.
    public abstract String getVenueType();

    // Each child class returns its own special fields.
    public abstract String getSpecificDetails();
}
