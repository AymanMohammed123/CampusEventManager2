import java.util.ArrayList;

// This class stores and validates the campus event data.
// Main uses this class so the menu code does not need to manage the lists directly.
public class EventManager {
    // These lists keep all objects created while the program is running.
    private final ArrayList<Event> events;
    private final ArrayList<Venue> venues;
    private final ArrayList<AcademicDepartment> departments;

    // The constructor prepares empty lists for the three main parts of the system.
    public EventManager() {
        events = new ArrayList<>();
        venues = new ArrayList<>();
        departments = new ArrayList<>();
    }

    // Adds a new venue after Main has collected and validated its input.
    public void addVenue(Venue venue) {
        venues.add(venue);
    }

    // Returns all venues so Main can display them to the user.
    public ArrayList<Venue> getVenues() {
        return venues;
    }

    // Checks whether at least one venue exists before actions that require a venue.
    public boolean hasVenues() {
        return !venues.isEmpty();
    }

    // Returns the venue at the given zero-based index, or null when the index is invalid.
    public Venue getVenueByIndex(int index) {
        if (index < 0 || index >= venues.size()) {
            return null;
        }
        return venues.get(index);
    }

    // Adds a new academic department after Main creates the department object.
    public void addDepartment(AcademicDepartment department) {
        departments.add(department);
    }

    // Returns all departments so Main can display them to the user.
    public ArrayList<AcademicDepartment> getDepartments() {
        return departments;
    }

    // Checks whether at least one department exists before actions that require a department.
    public boolean hasDepartments() {
        return !departments.isEmpty();
    }

    // Returns the department at the given zero-based index, or null when the index is invalid.
    public AcademicDepartment getDepartmentByIndex(int index) {
        if (index < 0 || index >= departments.size()) {
            return null;
        }
        return departments.get(index);
    }

    // Adds a new event after all event validation has passed.
    public void addEvent(Event event) {
        events.add(event);
    }

    // Returns all events so Main can display the saved event list.
    public ArrayList<Event> getEvents() {
        return events;
    }

    // This method tells Main if the event list has at least one event.
    // It returns true when there are events, and false when the list is empty.
    public boolean hasEvents() {
        return !events.isEmpty();
    }

    // This method removes an event from the list.
    // The eventNumber is the number shown to the user, so 1 means the first event.
    public void cancelEvent(int eventNumber) {
        events.remove(eventNumber - 1);
    }

    // This method searches for an event by its exact name.
    // It uses lowercase copies so "Workshop" and "workshop" can still match.
    public Event searchEvent(String eventName) {
        String searchedName = eventName.toLowerCase();

        // Loop through every saved event and compare its name with the user's name.
        for (Event event : events) {
            String currentEventName = event.getEventName().toLowerCase();

            // equals() checks if both lowercase names are exactly the same.
            if (currentEventName.equals(searchedName)) {
                return event;
            }
        }

        // Return null when the loop finishes without finding a matching event.
        return null;
    }

    // An event is valid only when its start date/time is before its end date/time.
    public boolean isValidEventTime(SimpleDateTime startDateTime, SimpleDateTime endDateTime) {
        return startDateTime.isBefore(endDateTime);
    }

    // A venue can hold an event only when the expected attendance fits within its capacity.
    public boolean canVenueHoldEvent(Venue venue, int expectedAttendance) {
        return expectedAttendance <= venue.getMaximumCapacity();
    }

    // Checks whether the new event conflicts with an existing event in the same venue.
    public boolean hasVenueOverlap(Event newEvent) {
        for (Event existingEvent : events) {
            // Events in different venues do not conflict with each other.
            if (!existingEvent.getVenue().equals(newEvent.getVenue())) {
                continue;
            }

            // Two events overlap when the new event starts before the existing event ends
            // and the new event ends after the existing event starts.
            boolean overlaps = newEvent.getStartDateTime().isBefore(existingEvent.getEndDateTime())
                    && newEvent.getEndDateTime().isAfter(existingEvent.getStartDateTime());

            if (overlaps) {
                return true;
            }
        }
        return false;
    }
}
