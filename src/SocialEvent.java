// This class represents a social event.
public class SocialEvent extends Event {
    // These fields are specific to social events.
    private final String dressCode;
    private final boolean refreshmentsProvided;

    // This constructor sets both common event data and social-event data.
    public SocialEvent(
            String eventName,
            SimpleDateTime startDateTime,
            SimpleDateTime endDateTime,
            int expectedAttendance,
            Venue venue,
            AcademicDepartment academicDepartment,
            String dressCode,
            boolean refreshmentsProvided
    ) {
        super(eventName, startDateTime, endDateTime, expectedAttendance, venue, academicDepartment);
        this.dressCode = dressCode;
        this.refreshmentsProvided = refreshmentsProvided;
    }

    // Returns the type of this event.
    @Override
    public String getEventType() {
        return "Social Event";
    }

    // Returns the special details of a social event.
    @Override
    public String getSpecificDetails() {
        return "Dress Code: " + dressCode + "\n"
                + "Refreshments Provided: " + (refreshmentsProvided ? "Yes" : "No");
    }
}
