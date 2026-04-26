// This class represents a conference hall.
public class ConferenceHall extends Venue {
    // These fields are specific to conference halls.
    private final boolean translationSystemAvailable;
    private final int breakoutRoomCount;

    // This constructor sets both common venue data and conference-hall data.
    public ConferenceHall(
            String venueName,
            int maximumCapacity,
            boolean translationSystemAvailable,
            int breakoutRoomCount
    ) {
        super(venueName, maximumCapacity);
        this.translationSystemAvailable = translationSystemAvailable;
        this.breakoutRoomCount = breakoutRoomCount;
    }

    // Returns the type of this venue.
    @Override
    public String getVenueType() {
        return "Conference Hall";
    }

    // Returns the special details of a conference hall.
    @Override
    public String getSpecificDetails() {
        return "Translation System Available: " + (translationSystemAvailable ? "Yes" : "No") + "\n"
                + "Breakout Room Count: " + breakoutRoomCount;
    }
}
