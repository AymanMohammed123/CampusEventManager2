// This class represents a religious event.
public class ReligiousEvent extends Event {
    // These fields are specific to religious events.
    private final String speakerName;
    private final boolean separateSeating;

    // This constructor sets both common event data and religious-event data.
    public ReligiousEvent(
            String eventName,
            SimpleDateTime startDateTime,
            SimpleDateTime endDateTime,
            int expectedAttendance,
            Venue venue,
            AcademicDepartment academicDepartment,
            String speakerName,
            boolean separateSeating
    ) {
        super(eventName, startDateTime, endDateTime, expectedAttendance, venue, academicDepartment);
        this.speakerName = speakerName;
        this.separateSeating = separateSeating;
    }

    // Returns the type of this event.
    @Override
    public String getEventType() {
        return "Religious Event";
    }

    // Returns the special details of a religious event.
    @Override
    public String getSpecificDetails() {
        return "Speaker Name: " + speakerName + "\n"
                + "Separate Seating: " + (separateSeating ? "Yes" : "No");
    }
}
