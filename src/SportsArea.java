// This class represents a sports venue.
public class SportsArea extends Venue {
    // These fields are specific to sports areas.
    private final String supportedSportType;
    private final boolean indoor;

    // This constructor sets both common venue data and sports-area data.
    public SportsArea(String venueName, int maximumCapacity, String supportedSportType, boolean indoor) {
        super(venueName, maximumCapacity);
        this.supportedSportType = supportedSportType;
        this.indoor = indoor;
    }

    // Returns the type of this venue.
    @Override
    public String getVenueType() {
        return "Sports Area";
    }

    // Returns the special details of a sports area.
    @Override
    public String getSpecificDetails() {
        return "Supported Sport Type: " + supportedSportType + "\n"
                + "Indoor Facility: " + (indoor ? "Yes" : "No");
    }
}
