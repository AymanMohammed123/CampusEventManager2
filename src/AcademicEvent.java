// This class represents an academic event.
public class AcademicEvent extends Event {
    // These fields are specific to academic events.
    private final String topic;
    private final boolean certificateProvided;

    // This constructor sets both common event data and academic-event data.
    public AcademicEvent(
            String eventName,
            SimpleDateTime startDateTime,
            SimpleDateTime endDateTime,
            int expectedAttendance,
            Venue venue,
            AcademicDepartment academicDepartment,
            String topic,
            boolean certificateProvided
    ) {
        super(eventName, startDateTime, endDateTime, expectedAttendance, venue, academicDepartment);
        this.topic = topic;
        this.certificateProvided = certificateProvided;
    }

    // Returns the type of this event.
    @Override
    public String getEventType() {
        return "Academic Event";
    }

    // Returns the special details of an academic event.
    @Override
    public String getSpecificDetails() {
        return "Topic: " + topic + "\n"
                + "Certificates Provided: " + (certificateProvided ? "Yes" : "No");
    }
}
