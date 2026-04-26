// This is the parent class for all event types.
// It stores the common information shared by every event.
public abstract class Event {
    // Common event fields.
    private final String eventName;
    private final SimpleDateTime startDateTime;
    private final SimpleDateTime endDateTime;
    private final int expectedAttendance;
    private final Venue venue;
    private final AcademicDepartment academicDepartment;

    // This constructor is used by child classes to set the shared event data.
    protected Event(
            String eventName,
            SimpleDateTime startDateTime,
            SimpleDateTime endDateTime,
            int expectedAttendance,
            Venue venue,
            AcademicDepartment academicDepartment
    ) {
        this.eventName = eventName;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.expectedAttendance = expectedAttendance;
        this.venue = venue;
        this.academicDepartment = academicDepartment;
    }

    // Getter methods return the values of private fields.
    public String getEventName() {
        return eventName;
    }

    public SimpleDateTime getStartDateTime() {
        return startDateTime;
    }

    public SimpleDateTime getEndDateTime() {
        return endDateTime;
    }

    public int getExpectedAttendance() {
        return expectedAttendance;
    }

    public Venue getVenue() {
        return venue;
    }

    public AcademicDepartment getAcademicDepartment() {
        return academicDepartment;
    }

    // This method creates one formatted block of text
    // to display all event information.
    public String getDisplayDetails() {
        // Each subclass appends its own fields so the main menu can display all event types uniformly.
        return "Event Type: " + getEventType() + "\n"
                + "Event Name: " + eventName + "\n"
                + "Start Date/Time: " + startDateTime + "\n"
                + "End Date/Time: " + endDateTime + "\n"
                + "Expected Attendance: " + expectedAttendance + "\n"
                + "Venue Name: " + venue.getVenueName() + "\n"
                + "Department Name: " + academicDepartment.getDepartmentName() + "\n"
                + getSpecificDetails();
    }

    // Each child class returns its event type name.
    public abstract String getEventType();

    // Each child class returns its own special fields.
    public abstract String getSpecificDetails();
}
