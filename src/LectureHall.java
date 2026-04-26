// This class represents a lecture hall.
public class LectureHall extends Venue {
    // These fields are specific to lecture halls.
    private final int projectorCount;
    private final boolean smartBoardAvailable;

    // This constructor sets both common venue data and lecture-hall data.
    public LectureHall(String venueName, int maximumCapacity, int projectorCount, boolean smartBoardAvailable) {
        super(venueName, maximumCapacity);
        this.projectorCount = projectorCount;
        this.smartBoardAvailable = smartBoardAvailable;
    }

    // Returns the type of this venue.
    @Override
    public String getVenueType() {
        return "Lecture Hall";
    }

    // Returns the special details of a lecture hall.
    @Override
    public String getSpecificDetails() {
        return "Projector Count: " + projectorCount + "\n"
                + "Smart Board Available: " + (smartBoardAvailable ? "Yes" : "No");
    }
}
