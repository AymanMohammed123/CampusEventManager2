// This class represents an event related to sports.
public class SportsEvent extends Event {
    // These fields are specific to sports events.
    private final String sportCategory;
    private final String teamFormat;

    // This constructor sets both common event data and sports-specific data.
    public SportsEvent(
            String eventName,
            SimpleDateTime startDateTime,
            SimpleDateTime endDateTime,
            int expectedAttendance,
            Venue venue,
            AcademicDepartment academicDepartment,
            String sportCategory,
            String teamFormat
    ) {
        super(eventName, startDateTime, endDateTime, expectedAttendance, venue, academicDepartment);
        this.sportCategory = sportCategory;
        this.teamFormat = teamFormat;
    }

    // Returns the type of this event.
    @Override
    public String getEventType() {
        return "Sports Event";
    }

    // Returns the special details of a sports event.
    @Override
    public String getSpecificDetails() {
        return "Sport Category (Male/Female): " + sportCategory + "\n"
                + "Team Format: " + teamFormat;
    }
}
