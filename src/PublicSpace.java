// This class represents a public space.
public class PublicSpace extends Venue {
    // These fields are specific to public spaces.
    private final boolean openAir;
    private final boolean electricityAccess;

    // This constructor sets both common venue data and public-space data.
    public PublicSpace(String venueName, int maximumCapacity, boolean openAir, boolean electricityAccess) {
        super(venueName, maximumCapacity);
        this.openAir = openAir;
        this.electricityAccess = electricityAccess;
    }

    // Returns the type of this venue.
    @Override
    public String getVenueType() {
        return "Public Space";
    }

    // Returns the special details of a public space.
    @Override
    public String getSpecificDetails() {
        return "Open Air: " + (openAir ? "Yes" : "No") + "\n"
                + "Electricity Access: " + (electricityAccess ? "Yes" : "No");
    }
}
